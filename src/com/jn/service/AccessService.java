package com.jn.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.jn.domain.Customer;

public interface AccessService {
	/**
	 * �û���¼
	 * @param customer
	 * @return
	 */
	public JSONObject login(String Email,String password);
	
	/**
	 * �����û�
	 * @param customer
	 * @return
	 */
	public JSONObject saveUser(Customer customer) throws Exception;
	
	/**
	 * ��ȡ����
	 * @param customer
	 * @return
	 */
	public JSONObject getPassword(String Email);
	
	/**
	 * �޸��û���Ϣ
	 * @param cus
	 * @return
	 */
	public JSONObject modify(Customer customer,File head);
	
	/**
	 * ֧������޸��û���Ϣ
	 * @param email
	 * @return
	 */
	public JSONObject pay(String type,String Email);
	
}
