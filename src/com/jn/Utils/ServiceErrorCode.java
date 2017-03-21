package com.jn.Utils;
/**
 * ������
 * @author Administrator
 *
 */
public class ServiceErrorCode {
	public static final int STATS_ERROR=0;//δ֪����
	public static final int STATS_OK=1; //�����ɹ�
	
	public static final int USER_ERROR=1000;//������������
	public static final int EMAIL_NO_EXIST=1001;//���䲻����
	public static final int EMAIL_EXIST=1002;//�����Ѵ���
	public static final int REGISTER_ERROR=1013;//���û�������
	public static final int HEAD_ERROR=1004;//����ͷ��ʧ��
	public static final int MODIFY_ERROR=1005;//�޸���Ϣʧ��
	public static final int MODIFY_SUCCESS=1006;//�޸���Ϣ�ɹ�
	public static final int SEND_SUCCESS=1007;//���뷢�ͳɹ�
	public static final int SEND_FAIL=1008;//���뷢��ʧ��
	public static final int SEARCH_ERROR=1009;//����ʧ��
	public static final int SEARCH_NO_RESULT=1010;//���û�������
	public static final int SEARCH_RESULT=1011;//���ҳɹ�
	
	public static final int APPLY_ERROR=1012;//����ʧ��
	public static final int APPLY_SUCCESS=1013;//����ɹ�
	public static final int DEAL_ERROR=1014;//���봦��ʧ��
	public static final int DEAL_SUCCESS=1015;//���봦��ɹ�
	public static final int CANCEL_ERROR=1016;//ȡ����עʧ��
	public static final int CANCEL_SUCCESS=1017;//ȡ����ע�ɹ�
	
	public static final int SN_NULL=1018;//SNΪ��
	public static final int SN_LENGTH_ERROR=1019;//SNλ������
	public static final int SN_ERROR=1020;//�޶�ӦSN
	public static final int SN_IP_ERROR=1021;//SN�޶�Ӧ��IP
	public static final int SN_IP_SUCCESS=1022;//SN�ж�Ӧ��IP
	public static final int SN_ATTACH=1023;//SN�Ѱ��û�
	public static final int ADD_SUCCESS=1024;//�󶨳ɹ�
	public static final int ADD_ERROR=1025;//��ʧ��
	public static final int DELETE_SUCCESS=1026;//ɾ���ɹ�
	public static final int DELETE_ERROR=1027;//ɾ��ʧ��
	public static final int MATCH_ERROR=1028;//SN���û���ƥ��
	
	public static final int PARAM_REPEAT=1029;//�����ظ�
	public static final int UPDATE_ERROR=1030;//������Ϣʧ��
	
	
	
	
	
	public static final int REQUEST_TYPE=2000;//�������ʹ���
	public static final int REQUEST_NULL=2001;//�������Ϊ��
	
	
	public static final int CATALOG_CREATE_ERROR=3000;//�������޷�����ָ��·������Ŀ¼
	public static final int FILE_IO_ERROR=3001;//��������ȡ�����ļ�����
	public static final int DATA_SAVE_ERROR=3002;//�������������ݳ���
	public static final int DATA_READ=3003;//��������ȡ���ݳ���   ��ʱδʹ��
	
	public static final int SN_SAVE=3004;//����������SN����
	public static final int INFO_MODIFY_ERROR=3005;//�޸��û���Ϣʧ��
	public static final int APPLY_DEAL_ERROR=3008;//����������ִ���
	public static final int APPLY_DEAL_SUCCESS=3009;//�ѳɹ���������
	public static final int APPLY_AGREE=3010;//����ͨ��
	public static final int APPLY_DISAGREE=3011;//�ܾ�����
	public static final int CONCERN_CONCEL_SUCCESS=3012;//ȡ����ע�ɹ�
	public static final int CONCERN_CONCEL_ERROR=3013;//ȡ����עʧ��
	public static final int CONCERN_GET_ERROR=3014;//��ȡ��ע��Ϣʧ��
	
	
	
}
