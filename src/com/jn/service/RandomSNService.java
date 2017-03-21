package com.jn.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public interface RandomSNService {

	public static List<String> snAll = new ArrayList<String>();
	
	/**
	 * SN���
	 * @param number
	 * @param type
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public JSONObject saveSN(int number,String type,String level);
	
	/**
	 * ��SNд�뵽txt�ļ���
	 * @param list
	 * @return
	 */
	public boolean writeSn(List<String> list,String fileName);
	
	/**
	 * ������������</br>
	 * �ҵ���Ӧtype��level�µ��ӷ�����IP
	 * @param type
	 * @param level
	 * @return
	 */
	public JSONObject getIp(String type,String level);
	
	/**
	 * �ӷ���������</br>
	 * ����list�е�SN�������ݱ�
	 * @param list
	 * @return
	 */
	public String createTable(List<String> list);
}
