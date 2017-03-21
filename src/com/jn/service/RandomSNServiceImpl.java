package com.jn.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.ServiceResult;
import com.jn.dao.RandomSNDao;

@Repository("RandomSNService")
public class RandomSNServiceImpl implements RandomSNService {
	
	@Autowired
	private RandomSNDao randomSNDao;
	
	@Autowired
	private ServiceResult ret;
	
	public static final String SNSAVEURL = "SNFile";
	
	private char[] SN = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g',
			 'h','i','j','k','l','m','n','o','p','q','r','s','t',
			 'u','v','w','x','y','z'};
	
	public JSONObject getIp(String type,String level){
		return randomSNDao.getIp(type, level);
	}
	
	
	/**
	 * 获取无重复的SN,个数为number
	 * @param number
	 * @return
	 */
	private String[] randomSN(int number,String level){
		Map map = new HashMap();
		String[] sn = new String[number];
		String[] reSn = null;
		for(int j = 0;j<number;j++){
			StringBuffer s = new StringBuffer(level+"10A0");
			for(int i = 0;i<8;i++){
				s.append(SN[(int)(Math.random()*35)]);
			}
			sn[j] = s.toString();
		}
		return sn;
	}
	@Override
	public JSONObject saveSN(int number,String type,String level){
		JSONObject data = new JSONObject();
		try {
			save_SN(number, type, level);
			data.put("Code", ServiceErrorCode.STATS_OK);
			data.put("SN", snAll);
		} catch (Exception e) {
			data.put("Code", ServiceErrorCode.STATS_ERROR);
			data.put("Message", "Error");
		}
		return data;
	}
	
	@Override
	public String createTable(List<String> list){
		return randomSNDao.createDataTable(list);
	}
	
	
	private void save_SN(int number,String type,String level) throws Exception{
		JSONObject data = new JSONObject();
		String[] sn = new String[number];
		sn = randomSN(number,level);
		data = randomSNDao.saveSN(sn,type,level);
		if((Integer)data.get("count")==-1){
			throw new Exception();
		}else{
			List<String> snList = (List) data.get("snList");
			if(snList!=null)
				for(String li:snList)
					snAll.add(li);
			if((Integer) data.get("count")!=0)
				saveSN((Integer)data.get("count"),type,level);
		}
		
	}
	
	@Override
	public boolean writeSn(List<String> list,String fileName){
		boolean flog = true;
		ServletContext s1=ServletActionContext.getServletContext();
		String dir = s1.getRealPath("/")+SNSAVEURL;
		File snFile = new File(dir, fileName);  
		OutputStream out = null;
		try {
			out = new FileOutputStream(snFile);
			byte[] bytes = new byte[512];
			int length;
			for(String sn:list){
				sn+="\r\n";
				bytes = sn.getBytes();
				length = sn.length();
				out.write(bytes, 0, length);
			}
		} catch (Exception e) {
			flog = false;
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				flog = false;
				e.printStackTrace();
			}
		}
		return flog;
	}
}
