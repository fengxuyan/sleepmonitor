package com.jn.dao;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface ProductDao {

	
	/**
	 * 判断SN是否合法</br>
	 * 返回0 无对应SN(不合法),1 存在(合法),2 查询时出错
	 * @param sn
	 * @return 
	 */
	public String existOrNot(String sn);
	
	/**
	 * 根据SN前3位查找对应的IP即子服务器
	 * @param sn3
	 * @return
	 */
	public String getIp(String sn3,String type);
	
	/**
	 * 查找该用户是否有已经有与当前输入SN相同类型的产品</br>
	 * 返回null\“”，无对应的SN</br>
	 * 否则返回已存在的SN
	 * @param Email
	 * @param type
	 * @return
	 */
	public String getSn(String Email,String type);
	
	/**
	 * 用户更换对应的产品
	 * @param Email
	 * @param oldSn
	 * @param newSn
	 * @return
	 */
	public String updateSn(String Email,String oldSn,String newSn) throws Exception;
	
	/**
	 * 判断当前SN是否已绑定用户</br>
	 * 返回null/“”,未绑定Email</br>
	 * 否则返回已绑定Email
	 * @param sn
	 * @return
	 */
	public String getEmail(String sn);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备的数据转移到新设备
	 * @param newSN
	 * @param oldSN
	 * @param newRealTable
	 * @param newReportTable
	 * @param oldRealTable
	 * @param oldReportTable
	 * @return
	 */
	public String transData(String newSN,String oldSN,String newRealTable,String newReportTable,String oldRealTable,String oldReportTable);
	
	/**
	 * 子服务器程序</br>
	 * 删除设备数据
	 * @param request
	 * @return
	 */
	public String deleteData(String sn);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备在另一服务器上的数据转移到新设备的服务器上
	 * @param newSN
	 * @param oldSN
	 * @param newRealTable
	 * @param newReportTable
	 * @param oldRealTable
	 * @param oldReportTable
	 * @return
	 */
	public String getDataFromOld(String newSN,String oldSN,String newRealTable,String newReportTable,String oldRealTable,String oldReportTable);
	
	/**
	 * 子服务器程序</br>
	 * 将旧设备所在服务器上的数据删除
	 * @param oldSN
	 * @param oldRealTable
	 * @param oldReportTable
	 * @return
	 */
	public String deleteOldData(String oldSN,String oldRealTable,String oldReportTable);
	
	/**
	 * 主服务器操作</br>
	 * 清除用户信息表中的SN信息</br>
	 * 清除SN表中的绑定信息</br>
	 * 返回1，清除成功，否则清除失败
	 * @param Email
	 * @param sn
	 * @return
	 */
	public String deleteInfo(String Email,String sn);
	
	/**
	 * 主服务器更新添加的设备
	 * @param Email
	 * @param sn
	 * @return
	 */
	public String updateInfo(String Email,String sn);
	
	/**
	 * 删除设备，主服务器更新信息
	 * @param Email
	 * @param sn
	 * @param d_flog
	 * @return
	 */
	public String updateInfo(String Email,String sn,String d_flog);
	
}
