package com.jn.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jn.Utils.CreateTable;
import com.jn.Utils.EmailChange;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.ServiceResult;
import com.jn.Utils.YearPlus;
import com.jn.domain.Customer;
import com.jn.domain.SNInfo;

@Repository("AccessDao")  
public class AccessDaoImpl implements AccessDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public JSONObject login(String Email,String tableName,String password){
		JSONObject data = new JSONObject();
		String sql1 = "select * from "+tableName+" where Email = ?";
		String sql2 = "select * from "+tableName+" where Email = ? and hpassword = ?";
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		List list1 = null;
		List list2 = null;
		try {
			list1 = ((SQLQuery) session.createSQLQuery(sql1).setString(0, Email)).list();
			if(list1.size()==0){
				data.put("Code", ServiceErrorCode.EMAIL_NO_EXIST);
				data.put("Message", "The Email is not Exist!");
			}else{
				list2 = ((SQLQuery) session.createSQLQuery(sql2).setString(0, Email)
						.setString(1, password)).addScalar("EMAIL").addScalar("FIRSTNAME")
						.addScalar("MIDDLENAME").addScalar("LASTNAME").addScalar("IMAGE")
						.addScalar("TYPE1SN").addScalar("GRADE1").addScalar("TYPE2SN")
						.addScalar("GRADE2").addScalar("TYPE3SN").addScalar("GRADE3")
						.addScalar("DEADLINE1").addScalar("DEADLINE2").addScalar("DEADLINE3")
						.addScalar("PHONENUMBER").addScalar("GENDER").addScalar("YEAROFBIRTH")
						.addScalar("ADDRESS").addScalar("HPASSWORD").list();
				ts.commit();
				if(list2.size()==0){
					data.put("Code", ServiceErrorCode.USER_ERROR);
					data.put("Message", "Error in Email or Password!");
				}else{
					data.put("Code", ServiceErrorCode.STATS_OK);
					data.put("Message", "Successfully login!");
					data.put("data", list2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			data.clear();
			data.put("Code", ServiceErrorCode.STATS_ERROR);
			data.put("Message", "Error in Server.Please try Again!");
		} finally{
			session.close();
		}
		return data;
	}

	@Override
	public String getIp(String sn3,String type){
		String tableName = "type"+type;
		String sql = "select ip from "+tableName+" where sn3=?";
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		List list =null;
		String flog = null;
		try{
			list = session.createSQLQuery(sql).setString(0, sn3).list();
			ts.commit();
			flog = list.get(0)==""?"":(String)list.get(0);
		}catch(Exception e){
			ts.rollback();
			flog = "fail";
		}finally{
			session.close();
		}
		return flog;
	}
	
	@Override
	public JSONObject saveUser(Customer cus,String tableName) throws Exception {
		JSONObject data = new JSONObject();
		String sql = "insert into "+tableName+" (Email,hpassword,firstName,middleName,lastName,yearOfBirth,"+
				 "gender,phoneNumber,address,image) values (?,?,?,?,?,?,?,?,?,?)";
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		SQLQuery sqlQuery = null;
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql).setString(0, cus.getEmail()).setString(1, cus.getPassword()).
											  setString(2, cus.getFirstName()).setString(3, cus.getMiddleName()).
											  setString(4, cus.getLastName()).setString(5, cus.getYearOfBirth()).
											  setString(6,cus.getGender()).setString(7, cus.getPhoneNumber()).
											  setString(8,cus.getAddress()). setString(9,cus.getImage());
			sqlQuery.executeUpdate();
			//创建我的关注表
			CreateTable.createAttentionTable("GZ"+new EmailChange().emailChange(cus.getEmail()));
			//创建关注我的表
			CreateTable.createAttentionTable("BGZ"+new EmailChange().emailChange(cus.getEmail()));
			//创建申请关注表
			CreateTable.createAttentionTable("QQ"+new EmailChange().emailChange(cus.getEmail()));
			ts.commit();
			data.put("Code",ServiceErrorCode.STATS_OK);
			data.put("Message","Successfully Register!");
		}catch(Exception e){
			data.put("Code",ServiceErrorCode.REGISTER_ERROR);
			data.put("Message","Failed to Register.Please Try Again!");
			ts.rollback();
		}finally{
			session.close();
		}
		return data;
	}
	
	@Override
	public String getImage(String Email,String tableName){
		String sql = "select image from "+tableName+" where Email = ?";
		List list = null;
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try{
			list = ((SQLQuery) session.createSQLQuery(sql).setString(0, Email)).list();
			ts.commit();
		}catch(Exception e){
			ts.rollback();
		}finally{
			session.close();
		}
		return (String)list.get(0);
	}
	
	@Override
	public String searchEmail(String Email,String tableName) throws Exception{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		String sql1 = "select * from "+tableName+" where Email = ?";
		List list = null;
		try{
			list = ((SQLQuery) session.createSQLQuery(sql1).setString(0, Email)).list();
			ts.commit();
			if(list.size()>0)
				return "exist";
			else
				return "notExist";
		}catch(Exception e){
			ts.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public String getPassword(String Email,String tableName) throws Exception {
		String sql = "select hpassword from "+tableName+" where Email = ?";
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		List pList = null;
		try{
			pList = ((SQLQuery)session.createSQLQuery(sql).setString(0, Email)).addScalar("HPASSWORD", Hibernate.STRING).list();
			ts.commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		if(pList.size()>0){
			return (String)pList.get(0);
		}else{
			return null;
		}
		
	}
	
	@Override
	public JSONObject modify(String tableName,Customer cus){
		JSONObject data = new JSONObject();
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String sql = "update "+tableName+" set firstName=?,middleName=?,lastName=?,yearOfBirth=?,"+
					 "gender=?,phoneNumber=?,address=?,hpassword=?,image=? where Email=?";
		int count = 0;
		try{
			SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).setString(0, cus.getFirstName()).setString(1, cus.getMiddleName()).
									setString(2, cus.getLastName()).setString(3, cus.getYearOfBirth()).setString(4,cus.getGender()).
									setString(5, cus.getPhoneNumber()).setString(6, cus.getAddress()).setString(7,cus.getPassword()).
									setString(8, cus.getImage()).setString(9, cus.getEmail());
			count = sqlQuery.executeUpdate();
			if(count==0){
				data.put("Code",ServiceErrorCode.MODIFY_ERROR);
				data.put("Message","Failed to Modify Your Infor.Please Try Again!");
			}else{
				data.put("Code",ServiceErrorCode.MODIFY_SUCCESS);
				data.put("Message","Successfully modify!");
			}
			tr.commit();
		}catch(Exception e){
			data.clear();
			data.put("Code",ServiceErrorCode.MODIFY_ERROR);
			data.put("Message","Failed to Modify Your Infor.Please Try Again!");
			tr.rollback();
		}finally{
			session.close();
		}
		return data;
	}
	
	@Override
	public JSONObject pay(String Email,String type,String tableName){
		JSONObject data = new JSONObject();
		String sql = null;
		String deadline = YearPlus.year();
		if("1".equals(type)){
			sql = "update "+tableName+" set grade1=1,deadline1=? where Email=?";
		}else if("2".equals(type)){
			sql = "update "+tableName+" set grade2=1,deadline2=? where Email=?";
		}else if("3".equals(type)){
			sql = "update "+tableName+" set grade3=1,deadline3=? where Email=?";
		}
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		SQLQuery sqlQuery = null;
		try{
			sqlQuery = (SQLQuery) session.createSQLQuery(sql).setString(0, deadline).setString(1, Email);
			sqlQuery.executeUpdate();
			tr.commit();
			data.put("Code",ServiceErrorCode.STATS_OK);
			data.put("Message","Successfully Update Your Infor!");
		}catch(Exception e){
			tr.rollback();
			data.clear();
			data.put("Code",ServiceErrorCode.UPDATE_ERROR);
			data.put("Message","Failed Update Your Infor.Please Try Again!");
		}finally{
			session.close();
		}
		return data;
	}
	/*private String createTable(String tableName) throws SQLException{
		String sql = "create table "+tableName+"( hupDate varchar2(10),upTime varchar2(8),"
					+"heartbeat number(3),hmovement number(3),respiration number(3),"
					+"temperature number(3,3),extend1 number(3),extend2 number(3))";
		Connection c = null;
		PreparedStatement ps = null;
		boolean rs = false;
		try {
			c = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			ps = c.prepareStatement(sql);
	        rs = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally{
			c.close();
			ps.close();
		}
		
		return null;
	}
	*/
	/*@Override
	public Customer getUserInfo(String Email){
		String sql = "select * from t_customer where Email = ?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List user = ((SQLQuery) session.createSQLQuery(sql).setString(0, Email)).addEntity(Customer.class).list();
		return (Customer)user.get(0);
	}
	
	@Override
	public ServiceResult modifyInfo(Customer cus){
		ServiceResult ret = new ServiceResult();
		ret.setResult("Code", ServiceErrorCode.STATS_OK);
		ret.setResult("Message", "Your Info has been modified.");
		int i = 0;
		String sql = "update t_customer set firstName = ?,middleName=?,lastName=?,"
					 +"age=?,gender=?,phone=?,sn=?,password=? where Email =?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			SQLQuery sq = ((SQLQuery) session.createSQLQuery(sql)
									.setString(0, cus.getFirstName()).setString(1, cus.getMiddleName())
									.setString(2, cus.getLastName()).setInteger(3, cus.getAge())
									.setString(4, cus.getGender()).setString(5, cus.getPhone())
									.setString(6, cus.getSn()).setString(7, cus.getPassword())
									.setString(8, cus.getEmail()));
									//.addEntity(Customer.class).list();
			i =sq.executeUpdate();
			tr.commit();
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		if(i==0){
			ret.setResult("Code", ServiceErrorCode.STATS_ERROR);
			ret.setResult("Message", "Failed to modify your Info.Please try again.");
		}
		return ret;
	}
	
	@Override
	public SNInfo queryInfo(String tableName,String column,String info){
		String sql = "select * from "+tableName+" where "+column+" = ?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List user = ((SQLQuery) session.createSQLQuery(sql).setString(0, info)).addEntity(SNInfo.class).list();
		if(user.size()>0)
			return (SNInfo)user.get(0);
		else 
			return null;
	}
	
	@Override
	public int deleteData(String tableName){
		int count = 0;
		String sql = "delete from "+tableName;
		String sql1 = "select * from ?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			SQLQuery sq = (SQLQuery) session.createSQLQuery(sql);
			List sn = ((SQLQuery) session.createSQLQuery(sql).setString(0, tableName)).addEntity(SNInfo.class).list();
			if(sn!=null)
				count =sq.executeUpdate();
			else 
				count =1;
			tr.commit();
		}catch(Exception e){
			tr.rollback();
		}finally{
			session.close();
		}
		return count;
	}
	
	@Override
	public int updateSN(String sn,String Email) throws Exception{
		int count = 0;
		String sql = "update t_SNInfo set Email = ? where sn=?";
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try{
			SQLQuery sq = ((SQLQuery) session.createSQLQuery(sql)
					.setString(0, Email).setString(1, sn));
			count =sq.executeUpdate();
			tr.commit();
		}catch(Exception e){
			tr.rollback();
			throw e;
		}finally{
			session.close();
		}
		return count;
	}*/
	
	
	/*@Override
	public SNInfo deleteInfo(String tableName){
		String sql = "delete from "+tableName;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List user = ((SQLQuery) session.createSQLQuery(sql).setString(0, info)).addEntity(SNInfo.class).list();
		if(user.size()>0)
			return (SNInfo)user.get(0);
		else 
			return null;
	}*/
}
