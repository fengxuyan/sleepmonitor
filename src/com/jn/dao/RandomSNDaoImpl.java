package com.jn.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jn.Utils.CreateTable;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.ServiceResult;
import com.jn.domain.SNInfo;

@Repository("RandomSNDao")
public class RandomSNDaoImpl implements RandomSNDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private ServiceResult ret;
	
	public ServiceResult getRet() {
		return ret;
	}

	public void setRet(ServiceResult ret) {
		this.ret = ret;
	}
	
	@Override
	public JSONObject getIp(String type,String level){
		String sql = "select IP from TYPE"+type+" where sn3="+level;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		JSONObject data = new JSONObject();
		List list = null;
		try{
			list = session.createSQLQuery(sql).list();
			tr.commit();
			if(list.size()==0){
				data.put("Code", 0);
				data.put("Message", "没有对应的IP");
			}else{
				data.put("Code", 1);
				//data.put("Message", (String)list.get(0));
				data.put("Message", (String)list.get(0));
			}
		}catch(Exception e){
			tr.rollback();
			data.put("Message", "服务器端错误");
			data.put("Code", 0);
		}
		return data;
	}
	
	
	@Override
	public Map checkSN(String[] sn) {
		Map map = new HashMap();
		String[] reSn = null;
		String sql = "select * from t_SNInfo where sn = ?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		for(int i=0,j=0;i<sn.length;i++){
			List sList = ((SQLQuery) session.createSQLQuery(sql).setString(0, sn[i])).addEntity(SNInfo.class).list();
			if(sList.size()!=0){
				map.put(j++,i);
			}
		}
		tr.commit();
		session.close();
		return map;
	}
	
	@Override
	public JSONObject saveSN(String[] sn,String type,String level){
		List<String> snList = new ArrayList<String>();
		JSONObject data = new JSONObject();
		String tableName = "TYPE"+type+"snt"+level;
		int count = 0;//统计表中已存在的SN值
		String sql1 = "select * from "+tableName+" where sn = ?";
		String sql2 = "insert into "+tableName+"(sn) values (?)";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		SQLQuery sqlQuery = null;
		try{
			for(int i = 0;i<sn.length;i++){
				List list1 = ((SQLQuery) session.createSQLQuery(sql1).setString(0, sn[i])).list();
				if(list1.size()!=0){
					count++;
					break;
				}
				sqlQuery = (SQLQuery) session.createSQLQuery(sql2).setString(0, sn[i]);
				sqlQuery.executeUpdate();
				snList.add(sn[i]);
				/*//根据SN创建实时数据表，一个SN对应一张实时表
				new CreateTable().createRealTable("real"+sn[i]);
				//根据SN创建睡眠报告表，一个SN对应一张报告表
				new CreateTable().createReportTable("report"+sn[i]);*/
				if(i%20==0){
					session.flush();
					session.clear();
				}
			}
			data.put("count", count);
			data.put("snList", snList);
			tr.commit();
		}catch(Exception e){
			e.printStackTrace();
			data.put("count", -1);
			tr.rollback();
		}finally{
			session.close();
		}
		return data;
	}
	
	
	@Override
	public String createDataTable(List<String> list){
		String flog =null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			for(String sn:list){
				CreateTable.createRealTable("real" + sn);
				CreateTable.createReportTable("report" + sn);
			}
			tr.commit();
			flog = "success";
		} catch (Exception e) {
			tr.rollback();
			flog = "fail";
		} finally{
			session.close();
		}
		return flog;
	}
	
}
