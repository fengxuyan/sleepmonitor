package com.jn.dao;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface ProductDao {

	
	/**
	 * �ж�SN�Ƿ�Ϸ�</br>
	 * ����0 �޶�ӦSN(���Ϸ�),1 ����(�Ϸ�),2 ��ѯʱ����
	 * @param sn
	 * @return 
	 */
	public String existOrNot(String sn);
	
	/**
	 * ����SNǰ3λ���Ҷ�Ӧ��IP���ӷ�����
	 * @param sn3
	 * @return
	 */
	public String getIp(String sn3,String type);
	
	/**
	 * ���Ҹ��û��Ƿ����Ѿ����뵱ǰ����SN��ͬ���͵Ĳ�Ʒ</br>
	 * ����null\�������޶�Ӧ��SN</br>
	 * ���򷵻��Ѵ��ڵ�SN
	 * @param Email
	 * @param type
	 * @return
	 */
	public String getSn(String Email,String type);
	
	/**
	 * �û�������Ӧ�Ĳ�Ʒ
	 * @param Email
	 * @param oldSn
	 * @param newSn
	 * @return
	 */
	public String updateSn(String Email,String oldSn,String newSn) throws Exception;
	
	/**
	 * �жϵ�ǰSN�Ƿ��Ѱ��û�</br>
	 * ����null/����,δ��Email</br>
	 * ���򷵻��Ѱ�Email
	 * @param sn
	 * @return
	 */
	public String getEmail(String sn);
	
	/**
	 * �ӷ���������</br>
	 * �����豸������ת�Ƶ����豸
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
	 * �ӷ���������</br>
	 * ɾ���豸����
	 * @param request
	 * @return
	 */
	public String deleteData(String sn);
	
	/**
	 * �ӷ���������</br>
	 * �����豸����һ�������ϵ�����ת�Ƶ����豸�ķ�������
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
	 * �ӷ���������</br>
	 * �����豸���ڷ������ϵ�����ɾ��
	 * @param oldSN
	 * @param oldRealTable
	 * @param oldReportTable
	 * @return
	 */
	public String deleteOldData(String oldSN,String oldRealTable,String oldReportTable);
	
	/**
	 * ������������</br>
	 * ����û���Ϣ���е�SN��Ϣ</br>
	 * ���SN���еİ���Ϣ</br>
	 * ����1������ɹ����������ʧ��
	 * @param Email
	 * @param sn
	 * @return
	 */
	public String deleteInfo(String Email,String sn);
	
	/**
	 * ��������������ӵ��豸
	 * @param Email
	 * @param sn
	 * @return
	 */
	public String updateInfo(String Email,String sn);
	
	/**
	 * ɾ���豸����������������Ϣ
	 * @param Email
	 * @param sn
	 * @param d_flog
	 * @return
	 */
	public String updateInfo(String Email,String sn,String d_flog);
	
}
