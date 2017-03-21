package com.jn.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface RandomSNDao {

	/**
	 * ���sn�Ƿ��ظ�
	 * @param sn
	 * @return
	 */
	public Map checkSN(String[] sn);
	
	/**
	 * ����SN���
	 * @param sn
	 * @return
	 */
	public JSONObject saveSN(String[] sn,String type,String level); 
	
	/**
	 * �ӷ���������</br>
	 * ���ݴ�����list����SN����ʵʱ���ݱ�˯�߱����
	 * @param list
	 * @return
	 */
	public String createDataTable(List<String> list);
	
	/**
	 * ������������</br>
	 * �ҵ���Ӧtype��level�µ��ӷ�����IP
	 * @param type
	 * @param level
	 * @return
	 */
	public JSONObject getIp(String type,String level);
	
}
