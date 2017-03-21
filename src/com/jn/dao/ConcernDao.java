package com.jn.dao;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * �����ע Dao��
 * @author Administrator
 *
 */
public interface ConcernDao {

	/**
	 * ���������ע
	 * @param Email
	 * @param first
	 * @param middle
	 * @param last
	 * @return
	 */
	public int apply(String myEmail, String otherEmail,String userTable,String first,String middle,String last);
	
	/**
	 * ����tableName,Email�����Ƿ����Ҫ��ע���û�
	 * @param tableName
	 * @param Email
	 * @return
	 */
	public List query(String tableName,String Email);
	
	/**
	 * ����tableName,Email��ѯ�Ƿ��Ѿ���������У���ֹ�ظ��ύ����
	 * @param tableName
	 * @param Email
	 * @return
	 */
	public List queryQQ(String tableName,String Email);
	
	/**
	 * ͬ������
	 * @param Email
	 * @param deal
	 * @return
	 */
	public int agreeApply(String myEmail,String otherEmail,String first,String middle,String last,String otherName) throws Exception;
	
	/**
	 * ��ͬ������
	 * @param myEmail
	 * @param otherEmail
	 * @return
	 */
	public int disAgreeApply(String myEmail,String otherEmail) throws Exception;
	
	
	/**
	 * ȡ����ע
	 * @param myEmail
	 * @param otherEmail
	 * @param flog
	 * @return
	 * @throws Exception
	 */
	public int cancelConcern(String myEmail,String otherEmail,String flog) throws Exception;
	
	/**
	 * ���������עҳ����Ϣ
	 * @param Email
	 * @return
	 */
	public JSONObject getConcern(String Email);
}
