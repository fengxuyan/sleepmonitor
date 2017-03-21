package com.jn.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jn.Utils.CreateTable;
import com.jn.Utils.UserTable;

@Repository("ProductDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String existOrNot(String sn){
		String sn3 = sn.substring(0, 3);
		String type = sn.substring(3,4);
		String tableName = "type"+type+"snt"+sn3;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String sql = "select * from "+tableName+" where sn=?";
		List list = null;
		String ret = null;
		try{
			list = session.createSQLQuery(sql).setString(0, sn).list();
			tr.commit();
			if(list.size()==0)
				ret = "0";
			else
				ret = "1";
		}catch(Exception e){
			tr.rollback();
			ret = "2";
		}finally{
			session.close();
		}
		return ret;
	}
	
	@Override
	public String getIp(String sn3,String type){
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String tableName = "type"+type;
		String sql = "select ip from "+tableName+" where sn3=?";
		List list =null;
		String Ip = null;
		try{
			list = session.createSQLQuery(sql).setString(0, sn3).list();
			tr.commit();
			if(list.size()==0)
				Ip = "noip";
			else
				Ip = (String)list.get(0);
		}catch(Exception e){
			tr.rollback();
			Ip = "error";
		}finally{
			session.close();
		}
		return Ip;
	}
	
	@Override
	public String getSn(String Email,String type){
		String sn = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String tableName = UserTable.getTableName(Email);
		String column = "type"+type+"sn";
		String sql = "select "+column+" from "+tableName+" where Email=?";
		List list =null;
		try{
			list = session.createSQLQuery(sql).setString(0, Email).list();
			sn = (String)list.get(0);
			tr.commit();
		}catch(Exception e){
			tr.rollback();
			sn = null;
		}finally{
			session.close();
		}
		return sn;
	}
	
	@Override
	public String updateSn(String Email,String oldSn,String newSn) throws Exception{
		String tableName = UserTable.getTableName(Email);
		String column = "type"+oldSn.substring(3,4)+"sn";
		String oldTableName = "type"+oldSn.substring(3,4)+"snt"+oldSn.substring(0,3);
		String newTableName = "type"+newSn.substring(3,4)+"snt"+oldSn.substring(0,3);
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		//用户信息表中 先把旧typesn清空 然后添加新typesn 
		//然后在snt表中先把旧typesn清空 然后把新typesn绑定Email
		String sql1 = "update "+tableName+" set "+column+" = ? where Email=?";
		String sql2 = "update "+oldTableName+" set Email = '' where sn=?";
		String sql3 = "update "+newTableName+" set Email = ? where sn=?";
		SQLQuery sqlQuery = null;
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql1).setString(0, newSn).setString(1, Email);
			sqlQuery.executeUpdate();
			sqlQuery = (SQLQuery) session.createSQLQuery(sql2).setString(0, oldSn);
			sqlQuery.executeUpdate();
			sqlQuery = (SQLQuery) session.createSQLQuery(sql3).setString(0, Email).setString(1, newSn);
			sqlQuery.executeUpdate();
			tr.commit();
		}catch(Exception e){
			tr.rollback();
			throw e;
		}finally{
			session.close();
		}
		return "success";
	}
	
	@Override
	public String getEmail(String sn){
		String existEmail = null;
		String sn3 = sn.substring(0, 3);
		String type = sn.substring(3,4);
		String tableName = "type"+type+"snt"+sn3;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String sql = "select Email from "+tableName+" where sn=?";
		List list =null;
		try{
			list = session.createSQLQuery(sql).setString(0, sn).list();
			existEmail = (String)list.get(0);
			tr.commit();
		}catch(Exception e){
			tr.rollback();
			existEmail = null;
		}finally{
			session.close();
		}
		return existEmail;
	}
	
	@Override
	public String deleteInfo(String Email,String sn){
		String flog = null;
		String user_Table = UserTable.getTableName(Email);
		String sn3 = sn.substring(0, 3);
		String type = sn.substring(3,4);
		String user_column = "type"+type+"sn";
		String snt_Table = "type"+type+"snt"+sn3;
		String sql1 = "update "+user_Table+" set "+user_column+"='' where Email=?";
		String sql2 = "update "+snt_Table+" set Email='' where sn=?";
		SQLQuery sqlQuery = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql1).setString(0, Email);
			sqlQuery.executeUpdate();
			sqlQuery = (SQLQuery) session.createSQLQuery(sql2).setString(0, sn);
			sqlQuery.executeUpdate();
			flog = "1";
			tr.commit();
		}catch(Exception e){
			flog = "0";
			tr.rollback();
		}finally{
			session.close();
		}
		return flog;
	}
	
	@Override
	public String updateInfo(String Email,String sn){
		String flog = "fail";
		String user_Table = UserTable.getTableName(Email);
		String sn3 = sn.substring(0, 3);
		String type = sn.substring(3,4);
		String user_column = "type"+type+"sn";
		String snt_Table = "type"+type+"snt"+sn3;
		String sql1 = "update "+user_Table+" set "+user_column+"=? where Email=?";
		String sql2 = "update "+snt_Table+" set Email=? where sn=?";
		SQLQuery sqlQuery = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql1).setString(0, sn).setString(1, Email);
			sqlQuery.executeUpdate();
			sqlQuery = (SQLQuery) session.createSQLQuery(sql2).setString(0, Email).setString(1, sn);
			sqlQuery.executeUpdate();
			tr.commit();
			flog = "success";
		}catch(Exception e){
			tr.rollback();
		}finally{
			session.close();
		}
		return flog;
	}
	
	@Override
	public String updateInfo(String Email,String sn,String d_flog){
		String flog = "fail";
		String user_Table = UserTable.getTableName(Email);
		String sn3 = sn.substring(0, 3);
		String type = sn.substring(3,4);
		String user_column = "type"+type+"sn";
		String snt_Table = "type"+type+"snt"+sn3;
		String sql1 = "update "+user_Table+" set "+user_column+"=? where Email=?";
		String sql2 = "update "+snt_Table+" set Email=? where sn=?";
		SQLQuery sqlQuery = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql1).setString(0, "").setString(1, Email);
			sqlQuery.executeUpdate();
			sqlQuery = (SQLQuery) session.createSQLQuery(sql2).setString(0, "").setString(1, sn);
			sqlQuery.executeUpdate();
			tr.commit();
			flog = "success";
		}catch(Exception e){
			tr.rollback();
		}finally{
			session.close();
		}
		return flog;
	}
	
	@Override
	public String transData(String newSN,String oldSN,String newRealTable,String newReportTable,String oldRealTable,String oldReportTable){
		String flog = "fail";
		try{
			CreateTable.deleteTable(newRealTable, newReportTable);
			CreateTable.renameTable(oldRealTable, newRealTable);
			CreateTable.renameTable(oldReportTable, newReportTable);
			CreateTable.createRealTable(oldRealTable);
			CreateTable.createReportTable(oldReportTable);
			flog = "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return flog;
	}
	
	@Override
	public String deleteData(String sn){
		String flog = "fail";
		String sql1 = "delete from real"+sn;
		String sql2 = "delete from report"+sn;
		SQLQuery sqlQuery = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			sqlQuery = session.createSQLQuery(sql1);
			sqlQuery.executeUpdate();
			sqlQuery = session.createSQLQuery(sql2);
			sqlQuery.executeUpdate();
			tr.commit();
			flog = "success";
		}catch(Exception e){
			tr.rollback();
		}finally{
			session.close();
		}
		return flog;
	}
	
	@Override
	public String getDataFromOld(String newSN,String oldSN,String newRealTable,String newReportTable,String oldRealTable,String oldReportTable){
		String flog = "fail";
		String sql1 = "insert into "+newRealTable+" select * from oldRealTable@changeData";
		String sql2 = "insert into "+newReportTable+" select * from oldReportTable@changeData";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		SQLQuery sqlQuery = null;
		try{
			sqlQuery = session.createSQLQuery(sql1);
			sqlQuery.executeUpdate();
			sqlQuery = session.createSQLQuery(sql2);
			sqlQuery.executeUpdate();
			tr.commit();
			flog = "success";
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		return flog;
	}
	
	@Override
	public String deleteOldData(String oldSN,String oldRealTable,String oldReportTable){
		String flog = "fail";
		String sql1 = "delete from "+oldRealTable;
		String sql2 = "delete from "+oldReportTable;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		SQLQuery sqlQuery = null;
		try{
			sqlQuery = session.createSQLQuery(sql1);
			sqlQuery.executeUpdate();
			sqlQuery = session.createSQLQuery(sql2);
			sqlQuery.executeUpdate();
			tr.commit();
			flog = "success";
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		return flog;
	}
	
}
