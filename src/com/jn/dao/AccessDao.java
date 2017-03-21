package com.jn.dao;

import net.sf.json.JSONObject;

import com.jn.Utils.ServiceResult;
import com.jn.domain.Customer;
import com.jn.domain.SNInfo;

public interface AccessDao {
	
	/**
	 * �û���¼
	 * @param Email
	 * @param password
	 * @return
	 */
	public JSONObject login(String Email,String tableName,String password);
	/**
	 * �û�ע��
	 * @param customer
	 * @return
	 */
	public JSONObject saveUser(Customer customer,String tableName) throws Exception;
	
	/**
	 * ��ȡ����
	 * @param customer
	 * @return
	 */
	public String getPassword(String Email,String tableName) throws Exception;
	
	/**
	 * ���Ҷ�Ӧ��Email�Ƿ��Ѵ����û�����
	 * @param Email
	 * @param tableName
	 * @return
	 */
	public String searchEmail(String Email,String tableName) throws Exception;
	
	
	/**
	 * �޸��û���Ϣ
	 * @param cus
	 * @return
	 */
	public JSONObject modify(String tableName,Customer customer);
	
	/**
	 * ����SN��ǰ3λ���豸�����ҵ���Ӧ�ķ�����IP
	 * @param sn3
	 * @param type
	 * @return
	 */
	public String getIp(String sn3,String type);
	
	/**
	 * ����Email��ȡimage���URL
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
	 * ��ȡ��Ӧ�û�����Ϣ
	 * @param Email
	 * @return
	 */
	//public Customer getUserInfo(String Email);
	
	/**
	 * �޸��û���Ϣ
	 * @param cus
	 * @return
	 */
	//public ServiceResult modifyInfo(Customer cus);
	
	/**
	 * ����������Ϣ�����Ƿ���ڶ�Ӧ����Ϣ
	 * ���ڷ���true�����򷵻�false
	 * @param info
	 * @return
	 */
	//public SNInfo queryInfo(String tableName,String column,String info);
	
	/**
	 * ɾ����Ӧ��ĵ�����
	 * @param tableName
	 * @return
	 */
	//public int deleteData(String tableName);
	
	/**
	 * ����SN������Email
	 * @param sn
	 * @param Email
	 * @return
	 * @throws Exception
	 */
	//public int updateSN(String sn,String Email) throws Exception;
	
	/**
	 * ��ָ�����в��Ҷ�Ӧ���ֶ�
	 * @param tableName
	 * @param info
	 * @param column
	 * @return
	 */
	//public SNInfo queryInfo(String tableName,String info,String column);
}
