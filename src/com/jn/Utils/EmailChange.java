package com.jn.Utils;
/**
 * 处理邮箱，将邮箱中的@转换为#, .转换为$,
 * @author Administrator
 *
 */
public class EmailChange {

	public String emailChange(String Email){
		StringBuffer s = new StringBuffer("");
		String[] se = Email.split("");
		for(int i = 1;i< se.length;i++){
			if(se[i].equals("."))
				s.append("$");
			else if(se[i].equals("@"))
				s.append("#");
				else s.append(se[i]);
				
		}
		return s.toString();
	}
	private boolean testNumber(String s){
		if(s.equals(String.valueOf(0))||s.equals(String.valueOf(1))||s.equals(String.valueOf(2))||s.equals(String.valueOf(3))||
				s.equals(String.valueOf(4))||s.equals(String.valueOf(5))||s.equals(String.valueOf(6))||s.equals(String.valueOf(7))
				||s.equals(String.valueOf(8))||s.equals(String.valueOf(9)))
		return true;
	return false;
	}
}
