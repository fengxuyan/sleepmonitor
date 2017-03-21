package com.jn.service;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface ProductService {

	/**
	 * ����Email��SN��Ӳ�Ʒ
	 * @param Email
	 * @param SN
	 * @return
	 */
	public JSONObject addProduct(HttpServletRequest request);
	
	/**
	 * ����Email��SNɾ����Ʒ
	 * @param request
	 * @return
	 */
	public JSONObject deleteProduct(HttpServletRequest request);
	
	/**
	 * �ӷ���������</br>
	 * �����豸������ת�Ƶ����豸
	 * @param request
	 * @return
	 */
	public String transData(HttpServletRequest request);
	
	/**
	 * �ӷ���������</br>
	 * �����豸������ת�Ƶ����豸
	 * @param request
	 * @return
	 */
	public String deleteData(HttpServletRequest request);
	
	/**
	 * �ӷ���������</br>
	 * �����豸����һ�������ϵ�����ת�Ƶ����豸�ķ�������
	 * @param request
	 * @return
	 */
	public String getDataFromOld(HttpServletRequest request);
	
	/**
	 * �ӷ���������</br>
	 * �����豸���ڷ������ϵ�����ɾ��
	 * @param request
	 * @return
	 */
	public String deleteOldData(HttpServletRequest request);
	
}
