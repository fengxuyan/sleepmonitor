package com.jn.Utils;
/**
 * Éú³ÉËæ»úSN
 * @author Administrator
 *
 */
public class RandomSN {
	
	private char[] SN = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g',
						 'h','i','j','k','l','m','n','o','p','q','r','s','t',
						 'u','v','w','x','y','z'};
	public String[] randomSN(int number){
		String sn[]=new String[number];
		StringBuffer s = new StringBuffer("00010A0");
		for(int j = 0;j<number;j++){
			for(int i = 0;i<8;i++){
				s.append(SN[(int)(Math.random()*35)]);
			}
			sn[j] = s.toString();
		}
		
		return sn;
	}
	private String[] checkSn(String[] sn){
		for(int i=0;i<sn.length;i++){
			
		}
		
		return sn;
	}
}
