package com.jn.service;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface ProductService {

	/**
	 * 根据Email和SN添加产品
	 * @param Email
	 * @param SN
	 * @return
	 */
	public JSONObject addProduct(HttpServletRequest request);
	
	/**
	 * 根据Email和SN删除产品
	 * @param request
	 * @return
	 */
	public JSONObject deleteProduct(HttpServletRequest request);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备的数据转移到新设备
	 * @param request
	 * @return
	 */
	public String transData(HttpServletRequest request);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备的数据转移到新设备
	 * @param request
	 * @return
	 */
	public String deleteData(HttpServletRequest request);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备在另一服务器上的数据转移到新设备的服务器上
	 * @param request
	 * @return
	 */
	public String getDataFromOld(HttpServletRequest request);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备所在服务器上的数据删除
	 * @param request
	 * @return
	 */
	public String deleteOldData(HttpServletRequest request);
	
}
