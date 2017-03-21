package com.jn.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public interface RandomSNService {

	public static List<String> snAll = new ArrayList<String>();
	
	/**
	 * SN入库
	 * @param number
	 * @param type
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public JSONObject saveSN(int number,String type,String level);
	
	/**
	 * 将SN写入到txt文件中
	 * @param list
	 * @return
	 */
	public boolean writeSn(List<String> list,String fileName);
	
	/**
	 * 主服务器程序</br>
	 * 找到对应type，level下的子服务器IP
	 * @param type
	 * @param level
	 * @return
	 */
	public JSONObject getIp(String type,String level);
	
	/**
	 * 子服务器程序</br>
	 * 根据list中的SN创建数据表
	 * @param list
	 * @return
	 */
	public String createTable(List<String> list);
}
