package com.jn.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jn.Utils.EmailChange;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.UserTable;
import com.jn.domain.Customer;

@Repository("ConcernDao")
public class ConcernDaoImpl implements ConcernDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int apply(String myEmail, String otherEmail,String userTable,String first,String middle,String last) {
		JSONObject data = new JSONObject();
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		SQLQuery sqlQuery = null;
		int count = 0;
		//对方的请求关注表
		String qq_table = "QQ"+new EmailChange().emailChange(otherEmail);
		String sql = "insert into "+qq_table+"(Email,firstName,middleName,lastName) values(?,?,?,?)";
		sqlQuery = (SQLQuery) session.createSQLQuery(sql).setString(0, myEmail).setString(1, first).
						setString(2, middle==null?"":middle).setString(3, last);
		try{
			count = sqlQuery.executeUpdate();
			tr.commit();
		}catch(Exception e){
			count = 0;
			tr.rollback();
		}finally{
			session.close();
		}
		return count;
	}

	@Override
	public List query(String tableName,String Email) {
		JSONObject data = new JSONObject();
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String sql = "select * from "+tableName+" where Email=?";
		List list = null;
		try{
			list = ((SQLQuery) session.createSQLQuery(sql).setString(0, Email))
						.addScalar("FIRSTNAME", Hibernate.STRING).addScalar("MIDDLENAME", Hibernate.STRING)
						.addScalar("LASTNAME", Hibernate.STRING).addScalar("EMAIL", Hibernate.STRING)
						.addScalar("image", Hibernate.STRING).list();
			tr.commit();
		}catch(Exception e){
			tr.rollback();
		}finally{
			session.close();
		}
		return list;
	}
	
	@Override
	public List queryQQ(String tableName,String Email) {
		JSONObject data = new JSONObject();
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String sql = "select * from "+tableName+" where Email=?";
		List list = null;
		try{
			list = ((SQLQuery) session.createSQLQuery(sql).setString(0, Email))
						.addScalar("FIRSTNAME", Hibernate.STRING).addScalar("MIDDLENAME", Hibernate.STRING)
						.addScalar("LASTNAME", Hibernate.STRING).addScalar("EMAIL", Hibernate.STRING).list();
			tr.commit();
		}catch(Exception e){
			tr.rollback();
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public int agreeApply(String myEmail,String otherEmail,String first,String middle,String last,String otherName) throws Exception{
		JSONObject data = new JSONObject();
		String[] names = otherName.split(" ");
		String ofirst = null;
		String omiddle = null;
		String olast = null;
		if(names.length==3){
			ofirst = names[0];
			omiddle = names[1];
			olast = names[2];
		}else{
			ofirst = names[0];
			olast = names[1];
			omiddle="";
		}
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		//发出申请的人的关注表！！！
		String my_tablename = "GZ"+new EmailChange().emailChange(otherEmail);
		//对当前用户来说更新被关注表
		String tablename_me = "BGZ"+new EmailChange().emailChange(myEmail);
		//我的申请表
		String qq_table = "QQ"+new EmailChange().emailChange(myEmail);
		String sql1 = "insert into "+my_tablename+" (Email,firstName,middleName,lastName)values(?,?,?,?)";
		String sql2 = "insert into "+tablename_me+" (Email,firstName,middleName,lastName)values(?,?,?,?)";
		String sql3 = "delete from "+qq_table+" where Email=?";
		String myImage = null;
		String otherImage = null;
		SQLQuery sqlQuery1 = null;
		SQLQuery sqlQuery2 = null;
		SQLQuery sqlQuery3 = null;
		try{
			sqlQuery1 = (SQLQuery) session.createSQLQuery(sql1).setString(0, myEmail).setString(1, first)
											.setString(2, middle==null?"":middle).setString(3,last);
			sqlQuery1.executeUpdate();
			sqlQuery2 = (SQLQuery) session.createSQLQuery(sql2).setString(0, otherEmail).setString(1, ofirst)
						.setString(2, omiddle).setString(3,olast);
			sqlQuery2.executeUpdate();
			sqlQuery3 = (SQLQuery) session.createSQLQuery(sql3).setString(0, otherEmail);
			sqlQuery3.executeUpdate();
			tr.commit();
		}catch(Exception e){
			tr.rollback();
			throw e;
		}finally{
			session.close();
		}
		return 0;
	}
	
	@Override
	public int disAgreeApply(String myEmail,String otherEmail) throws Exception{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String qq_table = "QQ"+new EmailChange().emailChange(myEmail);
		String sql = "delete from "+qq_table+" where Email=?";
		SQLQuery sqlQuery = null;
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql).setString(0, otherEmail);
			sqlQuery.executeUpdate();
			tr.commit();
		}catch(Exception e){
			tr.rollback();
			throw e;
		}finally{
			session.close();
		}
		return 0;
	}
	
	@Override
	public int cancelConcern(String myEmail,String otherEmail,String flog) throws Exception{
		JSONObject data = new JSONObject();
		String gz_name = null;
		String bgz_name = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int count = 0;
		//flog=true 清除我关注的人
		if ("true".equals(flog)) {
			gz_name = "GZ"+new EmailChange().emailChange(myEmail);
			bgz_name = "BGZ"+new EmailChange().emailChange(otherEmail);
			String sql1 ="delete from " +gz_name+" where Email=?";
			String sql2 ="delete from " +bgz_name+" where Email=?";
			SQLQuery sqlQuery1 = null;
			SQLQuery sqlQuery2 = null;
			try {
				sqlQuery1 = (SQLQuery) session.createSQLQuery(sql1).setString(0, otherEmail);
				sqlQuery1.executeUpdate();
				sqlQuery2 = (SQLQuery) session.createSQLQuery(sql2).setString(0, myEmail);
				sqlQuery2.executeUpdate();
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				throw e;
			} finally {
				session.close();
			}
		} else {
			//清除关注我的人
			gz_name = "GZ"+new EmailChange().emailChange(otherEmail);
			bgz_name = "BGZ"+new EmailChange().emailChange(myEmail);
			String sql1 ="delete from " +gz_name+" where Email=?";
			String sql2 ="delete from " +bgz_name+" where Email=?";
			SQLQuery sqlQuery1 = null;
			SQLQuery sqlQuery2 = null;
			try {
				sqlQuery1 = (SQLQuery) session.createSQLQuery(sql1).setString(0, myEmail);
				sqlQuery1.executeUpdate();
				sqlQuery2 = (SQLQuery) session.createSQLQuery(sql2).setString(0, otherEmail);
				sqlQuery2.executeUpdate();
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				throw e;
			} finally {
				session.close();
			}
		}
	return 0;
	}
	
	@Override
	public JSONObject getConcern(String Email){
		JSONObject data = new JSONObject();
		JSONObject json = new JSONObject();
		String my_table = "GZ"+new EmailChange().emailChange(Email);
		String table_me = "BGZ"+new EmailChange().emailChange(Email);
		String qq_table = "QQ"+new EmailChange().emailChange(Email);
		String selectMy = "select * from " + my_table;
		String selectMe = "select * from " + table_me;
		String selectQQ = "select * from " + qq_table;
		JSONArray myConcern = new JSONArray();
		JSONArray concernMe = new JSONArray();
		JSONArray applyConcern = new JSONArray();
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			//查找我关注的人
			List list = session.createSQLQuery(selectMy).addScalar("FIRSTNAME", Hibernate.STRING)
							.addScalar("MIDDLENAME", Hibernate.STRING).addScalar("LASTNAME", Hibernate.STRING)
							.addScalar("EMAIL", Hibernate.STRING).list();
			if(list.size()!=0){
				for(int i=0;i<list.size();i++){
					Object[] info = (Object[]) list.get(i);
					myConcern.add(i, getInfo(info));
				}
				data.put("myConcern", myConcern);
			}else{
				data.put("myConcern", "");
			}
			//查找关注我的人
			List list1 = session.createSQLQuery(selectMe).addScalar("FIRSTNAME", Hibernate.STRING)
					.addScalar("MIDDLENAME", Hibernate.STRING).addScalar("LASTNAME", Hibernate.STRING)
					.addScalar("EMAIL", Hibernate.STRING).list();
			if(list1.size()!=0){
				for(int i=0;i<list1.size();i++){
					Object[] info = (Object[]) list1.get(i);
					concernMe.add(i, getInfo(info));
				}
				data.put("concernMe", concernMe);
			}else{
				data.put("concernMe", "");
			}
			
			//查找申请关注我的人
			List list2 = session.createSQLQuery(selectQQ).addScalar("FIRSTNAME", Hibernate.STRING)
					.addScalar("MIDDLENAME", Hibernate.STRING).addScalar("LASTNAME", Hibernate.STRING)
					.addScalar("EMAIL", Hibernate.STRING).list();
			if(list2.size()!=0){
				for(int i=0;i<list2.size();i++){
					Object[] info = (Object[]) list2.get(i);
					applyConcern.add(i, getApplyInfo(info));
				}
				data.put("applyConcern", applyConcern);
			}else{
				data.put("applyConcern", "");
			}
			json.put("Code", ServiceErrorCode.STATS_OK);
			json.put("Message","Successfully get Concern Info!");
			json.put("data", data);
			tr.commit();
		}catch(Exception e){
			json.clear();
			json.put("Code", ServiceErrorCode.CONCERN_GET_ERROR);
			json.put("Message","Failed to get Concern Info");
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		return json;
	}
	
	/**
	 * 把用户信息拼接成Email:,Name:,Image:样式
	 * @param info
	 * @return
	 */
	private JSONObject getInfo(Object[] info){
		StringBuffer name = new StringBuffer("");
		JSONObject userInfo = new JSONObject();
		String image = null;
		for(int i=0;i<3;i++){
			if(i==0){
				name.append(info[i].toString()+" ");
			}else if(i==1){
				if(info[i]==null)
					name.append("");
				else
					name.append(info[i].toString()+" ");
			}else{
				name.append(info[i].toString());
			}
		}
		String Email = info[3].toString();
		image = getImage(Email);
		userInfo.put("Name", name.toString());
		userInfo.put("Email", Email);
		userInfo.put("Image", image);
		return userInfo;
	}
	
	/**
	 * 根据用户的Email查询image信息
	 * @param Email
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String getImage(String Email){
		String tableName = UserTable.getTableName(Email);
		List list = null;
		String sql = "select image from "+tableName+" where Email=?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			list = ((SQLQuery)session.createSQLQuery(sql).setString(0, Email)).addScalar("IMAGE", Hibernate.STRING).list();
			tr.commit();
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		return (String)list.get(0);
	}
	
	/**
	 * 把用户信息拼接成Email:,Name: image:样式
	 * @param info
	 * @return
	 */
	private JSONObject getApplyInfo(Object[] info){
		StringBuffer name = new StringBuffer("");
		JSONObject userInfo = new JSONObject();
		for(int i=0;i<3;i++){
			if(i==0){
				name.append(info[i].toString()+" ");
			}else if(i==1){
				if(info[i]==null)
					name.append("");
				else
					name.append(info[i].toString()+" ");
			}else{
				name.append(info[i].toString());
			}
		}
		String Email = info[3].toString();
		userInfo.put("Name", name.toString());
		userInfo.put("Email", Email);
		String info_table = new UserTable().getTableName(Email);
		String selectImage = "select image from "+info_table + " where Email=?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List list = null;
		try{
			list = ((SQLQuery) session.createSQLQuery(selectImage).setString(0, Email))
						.addScalar("image", Hibernate.STRING).list();
			tr.commit();
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		userInfo.put("Image", (String)list.get(0));
		return userInfo;
	}
}
