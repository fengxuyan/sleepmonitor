package com.jn.dao;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * 处理关注 Dao层
 * @author Administrator
 *
 */
public interface ConcernDao {

	/**
	 * 处理申请关注
	 * @param Email
	 * @param first
	 * @param middle
	 * @param last
	 * @return
	 */
	public int apply(String myEmail, String otherEmail,String userTable,String first,String middle,String last);
	
	/**
	 * 根据tableName,Email查找是否存在要关注的用户
	 * @param tableName
	 * @param Email
	 * @return
	 */
	public List query(String tableName,String Email);
	
	/**
	 * 根据tableName,Email查询是否已经在申请表中，防止重复提交申请
	 * @param tableName
	 * @param Email
	 * @return
	 */
	public List queryQQ(String tableName,String Email);
	
	/**
	 * 同意申请
	 * @param Email
	 * @param deal
	 * @return
	 */
	public int agreeApply(String myEmail,String otherEmail,String first,String middle,String last,String otherName) throws Exception;
	
	/**
	 * 不同意申请
	 * @param myEmail
	 * @param otherEmail
	 * @return
	 */
	public int disAgreeApply(String myEmail,String otherEmail) throws Exception;
	
	
	/**
	 * 取消关注
	 * @param myEmail
	 * @param otherEmail
	 * @param flog
	 * @return
	 * @throws Exception
	 */
	public int cancelConcern(String myEmail,String otherEmail,String flog) throws Exception;
	
	/**
	 * 查找亲情关注页面信息
	 * @param Email
	 * @return
	 */
	public JSONObject getConcern(String Email);
}
