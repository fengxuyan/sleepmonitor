package com.jn.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface RandomSNDao {

	/**
	 * 检查sn是否重复
	 * @param sn
	 * @return
	 */
	public Map checkSN(String[] sn);
	
	/**
	 * 保存SN入库
	 * @param sn
	 * @return
	 */
	public JSONObject saveSN(String[] sn,String type,String level); 
	
	/**
	 * 子服务器程序</br>
	 * 根据储存在list中是SN创建实时数据表及睡眠报告表
	 * @param list
	 * @return
	 */
	public String createDataTable(List<String> list);
	
	/**
	 * 主服务器程序</br>
	 * 找到对应type，level下的子服务器IP
	 * @param type
	 * @param level
	 * @return
	 */
	public JSONObject getIp(String type,String level);
	
}
