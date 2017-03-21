package com.jn.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.jn.domain.Customer;

public interface AccessService {
	/**
	 * 用户登录
	 * @param customer
	 * @return
	 */
	public JSONObject login(String Email,String password);
	
	/**
	 * 保存用户
	 * @param customer
	 * @return
	 */
	public JSONObject saveUser(Customer customer) throws Exception;
	
	/**
	 * 获取密码
	 * @param customer
	 * @return
	 */
	public JSONObject getPassword(String Email);
	
	/**
	 * 修改用户信息
	 * @param cus
	 * @return
	 */
	public JSONObject modify(Customer customer,File head);
	
	/**
	 * 支付完成修改用户信息
	 * @param email
	 * @return
	 */
	public JSONObject pay(String type,String Email);
	
}
