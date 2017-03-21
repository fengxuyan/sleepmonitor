package com.jn.dao;

import net.sf.json.JSONObject;

import com.jn.Utils.ServiceResult;
import com.jn.domain.Customer;
import com.jn.domain.SNInfo;

public interface AccessDao {
	
	/**
	 * 用户登录
	 * @param Email
	 * @param password
	 * @return
	 */
	public JSONObject login(String Email,String tableName,String password);
	/**
	 * 用户注册
	 * @param customer
	 * @return
	 */
	public JSONObject saveUser(Customer customer,String tableName) throws Exception;
	
	/**
	 * 获取密码
	 * @param customer
	 * @return
	 */
	public String getPassword(String Email,String tableName) throws Exception;
	
	/**
	 * 查找对应的Email是否已存于用户表中
	 * @param Email
	 * @param tableName
	 * @return
	 */
	public String searchEmail(String Email,String tableName) throws Exception;
	
	
	/**
	 * 修改用户信息
	 * @param cus
	 * @return
	 */
	public JSONObject modify(String tableName,Customer customer);
	
	/**
	 * 根据SN的前3位及设备类型找到对应的服务器IP
	 * @param sn3
	 * @param type
	 * @return
	 */
	public String getIp(String sn3,String type);
	
	/**
	 * 根据Email获取image里的URL
	 * @param Email
	 * @return
	 */
	public String getImage(String Email,String tableName);
	
	/**
	 * 
	 * @param Email
	 * @param type
	 * @return
	 */
	public JSONObject pay(String Email,String type,String tableName);
	
	
	
	/**
	 * 获取对应用户的信息
	 * @param Email
	 * @return
	 */
	//public Customer getUserInfo(String Email);
	
	/**
	 * 修改用户信息
	 * @param cus
	 * @return
	 */
	//public ServiceResult modifyInfo(Customer cus);
	
	/**
	 * 根据输入信息查找是否存在对应的信息
	 * 存在返回true，否则返回false
	 * @param info
	 * @return
	 */
	//public SNInfo queryInfo(String tableName,String column,String info);
	
	/**
	 * 删除对应表的的数据
	 * @param tableName
	 * @return
	 */
	//public int deleteData(String tableName);
	
	/**
	 * 根据SN来更新Email
	 * @param sn
	 * @param Email
	 * @return
	 * @throws Exception
	 */
	//public int updateSN(String sn,String Email) throws Exception;
	
	/**
	 * 在指定表中查找对应的字段
	 * @param tableName
	 * @param info
	 * @param column
	 * @return
	 */
	//public SNInfo queryInfo(String tableName,String info,String column);
}
