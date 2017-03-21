package com.jn.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 将用户头像保存在服务器端</br>然后返回URL
 * @author Administrator
 *
 */
public class SaveHead extends ActionSupport {

	public String saveHead(String Email,File file) throws Exception{
		OutputStream out = null;
		InputStream in = null;
		String URL = null;
		try {
			int s = (int)(Math.random()*1000);
			in = new FileInputStream(file);
			ServletContext s1 = ServletActionContext.getServletContext();
			String fileURL = s1.getRealPath("/") + "Heads\\"+Email+s+".jpg";
			File file1 = new File(fileURL);
			if(file1.exists()){
				file1.delete();
			}
			out = new FileOutputStream(file1);
			byte[] buffer = new byte[1024];
			int length; 
			while ((length = in.read(buffer)) > 0) {   
				out.write(buffer, 0, length); 
			}
			URL = "/SleepMonitor/Heads/"+Email+s+".jpg";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return URL;
	}
	
}
