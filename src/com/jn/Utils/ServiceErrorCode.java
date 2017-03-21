package com.jn.Utils;
/**
 * 错误码
 * @author Administrator
 *
 */
public class ServiceErrorCode {
	public static final int STATS_ERROR=0;//未知错误
	public static final int STATS_OK=1; //操作成功
	
	public static final int USER_ERROR=1000;//邮箱或密码错误
	public static final int EMAIL_NO_EXIST=1001;//邮箱不存在
	public static final int EMAIL_EXIST=1002;//邮箱已存在
	public static final int REGISTER_ERROR=1013;//该用户不存在
	public static final int HEAD_ERROR=1004;//保存头像失败
	public static final int MODIFY_ERROR=1005;//修改信息失败
	public static final int MODIFY_SUCCESS=1006;//修改信息成功
	public static final int SEND_SUCCESS=1007;//密码发送成功
	public static final int SEND_FAIL=1008;//密码发送失败
	public static final int SEARCH_ERROR=1009;//查找失败
	public static final int SEARCH_NO_RESULT=1010;//该用户不存在
	public static final int SEARCH_RESULT=1011;//查找成功
	
	public static final int APPLY_ERROR=1012;//申请失败
	public static final int APPLY_SUCCESS=1013;//申请成功
	public static final int DEAL_ERROR=1014;//申请处理失败
	public static final int DEAL_SUCCESS=1015;//申请处理成功
	public static final int CANCEL_ERROR=1016;//取消关注失败
	public static final int CANCEL_SUCCESS=1017;//取消关注成功
	
	public static final int SN_NULL=1018;//SN为空
	public static final int SN_LENGTH_ERROR=1019;//SN位数错误
	public static final int SN_ERROR=1020;//无对应SN
	public static final int SN_IP_ERROR=1021;//SN无对应的IP
	public static final int SN_IP_SUCCESS=1022;//SN有对应的IP
	public static final int SN_ATTACH=1023;//SN已绑定用户
	public static final int ADD_SUCCESS=1024;//绑定成功
	public static final int ADD_ERROR=1025;//绑定失败
	public static final int DELETE_SUCCESS=1026;//删除成功
	public static final int DELETE_ERROR=1027;//删除失败
	public static final int MATCH_ERROR=1028;//SN和用户不匹配
	
	public static final int PARAM_REPEAT=1029;//参数重复
	public static final int UPDATE_ERROR=1030;//更新信息失败
	
	
	
	
	
	public static final int REQUEST_TYPE=2000;//请求类型错误
	public static final int REQUEST_NULL=2001;//请求参数为空
	
	
	public static final int CATALOG_CREATE_ERROR=3000;//服务器无法按照指定路径创建目录
	public static final int FILE_IO_ERROR=3001;//服务器读取数据文件出错
	public static final int DATA_SAVE_ERROR=3002;//服务器解析数据出错
	public static final int DATA_READ=3003;//服务器读取数据出错   暂时未使用
	
	public static final int SN_SAVE=3004;//服务器保存SN出错
	public static final int INFO_MODIFY_ERROR=3005;//修改用户信息失败
	public static final int APPLY_DEAL_ERROR=3008;//处理申请出现错误
	public static final int APPLY_DEAL_SUCCESS=3009;//已成功处理请求
	public static final int APPLY_AGREE=3010;//申请通过
	public static final int APPLY_DISAGREE=3011;//拒绝申请
	public static final int CONCERN_CONCEL_SUCCESS=3012;//取消关注成功
	public static final int CONCERN_CONCEL_ERROR=3013;//取消关注失败
	public static final int CONCERN_GET_ERROR=3014;//获取关注信息失败
	
	
	
}
