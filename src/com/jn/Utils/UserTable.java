package com.jn.Utils;

/**
 * ��������ȷ����Ӧ���û���Ϣ��
 * @author Administrator
 *
 */
public class UserTable {

	public static String getTableName(String Email){
		StringBuffer s = new StringBuffer("");
		char ss = Email.charAt(0);
		if((ss>='a'&&ss<='z')||(ss>='A'&&ss<='Z')){
			s.append(ss);
			s.append("UI");
			return s.toString();
		}else if(ss>='0'&&ss<='9'){
			s.append('H');
			s.append(ss);
			s.append("UI");
			return s.toString();
		}else{
			s.append("H#UI");
			return s.toString();
		}
			
	}
	
}
