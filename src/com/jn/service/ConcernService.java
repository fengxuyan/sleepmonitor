package com.jn.service;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

/**
 * �����ע Service��
 * @author Administrator
 *
 */
public interface ConcernService {

	/**
	 * ��¼�����ע���ȴ�����
	 * @param Email
	 * @param first
	 * @param middle
	 * @param last
	 * @param flog
	 * @return
	 */
	public JSONObject apply(HttpServletRequest request);
	
	/**
	 * @param Email
	 * @return
	 */
	public JSONObject query(String Email);
	
	/**
	 * ͬ������
	 * @param Email
	 * @return
	 */
	public JSONObject agreeApply(HttpServletRequest request);
	
	/**
	 * ��ͬ������
	 * @param Email
	 * @return
	 */
	public JSONObject disAgreeApply(HttpServletRequest request);
	
	/**
	 * ȡ����ע
	 * @param request
	 * @return
	 */
	public JSONObject cancelConcern(HttpServletRequest request);
	
	/**
	 * ��ѯ�����עҳ��������Ϣ
	 * @param Email
	 * @return
	 */
	public JSONObject getConcern(HttpServletRequest request);
}
