package com.jn.service;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

/**
 * 处理关注 Service层
 * @author Administrator
 *
 */
public interface ConcernService {

	/**
	 * 记录申请关注，等待处理
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
	 * 同意申请
	 * @param Email
	 * @return
	 */
	public JSONObject agreeApply(HttpServletRequest request);
	
	/**
	 * 不同意申请
	 * @param Email
	 * @return
	 */
	public JSONObject disAgreeApply(HttpServletRequest request);
	
	/**
	 * 取消关注
	 * @param request
	 * @return
	 */
	public JSONObject cancelConcern(HttpServletRequest request);
	
	/**
	 * 查询亲情关注页面的相关信息
	 * @param Email
	 * @return
	 */
	public JSONObject getConcern(HttpServletRequest request);
}
