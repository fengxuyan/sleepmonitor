package com.jn.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.ServiceException;
import com.jn.Utils.ServiceResult;
import com.jn.dao.DataUploadDao;
import com.jn.domain.CustomerData;

@Service("DataUploadService")
public class DataUploadServiceImpl implements DataUploadService {

	private File file;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	private final static String HEADURL = "Heads";   //文件所存目录
	@Autowired
	private ServiceResult ret;
	@Autowired
	private CustomerData customerData;
	@Autowired
	private DataUploadDao dataUploadDao;
	@Override
	public ServiceResult dataUpload(HttpServletRequest request,File file) {
		Map map = new HashMap();
		if(!request.getContentType().contains("multipart/form-data")){
			ret.setResult("Code", ServiceErrorCode.EMAIL_EXIST);
			ret.setResult("Message", "Request Type Error");
			return ret;
		}else{
			try{
				InputStream in = new FileInputStream(file);
				ServletContext s1=ServletActionContext.getServletContext();
				String dir = s1.getRealPath("/")+HEADURL;
				File fileLocation = new File(dir); 
				SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");//设置日期格式
				String fileName = request.getParameter("Email");
				if(!fileLocation.exists()){  
					boolean isCreated  = fileLocation.mkdir();  
					if(!isCreated) {  
						ret.setResult("Code", ServiceErrorCode.EMAIL_EXIST);
						ret.setResult("Message", "Failed to Create Catalog in the Server");
						return ret;
					}
				}
					File uploadFile = new File(dir, df.format(new Date())+fileName+".txt");   
					OutputStream out = new FileOutputStream(uploadFile); 
					int dataSize= in.available();
					byte[] buffer = new byte[dataSize];   
					int length; 
					while ((length = in.read(buffer)) > 0) {   
						out.write(buffer, 0, length); 
					}  
					map = dealData(buffer);
					ret = dataUploadDao.DataUpload(map);
					in.close();   
					out.close();  
					
				}catch(IOException e){
					e.printStackTrace();
					ret.setResult("Code", ServiceErrorCode.EMAIL_NO_EXIST);
					ret.setResult("Message", "Error in Read the Data File");
				}catch(ServiceException e){
					e.printStackTrace();
					ret.setResult("Code", ServiceErrorCode.EMAIL_EXIST);
					ret.setResult("Message", "Error in Analytical Data");
				}catch(Exception e){
					e.printStackTrace();
					ret.setResult("Code", ServiceErrorCode.STATS_ERROR);
					ret.setResult("Message", "Unknown Error");
				}
				return ret;
			}
	}
	/**
	 * 将字节数组保存在Map中，map中存放两个参数Email:Email,
	 * 			DataList:用户数据的list集合，一个customerData对象代表一条数据
	 * @param b
	 * @return
	 * @throws ServiceException
	 * @throws Exception
	 */
	private Map dealData(byte[] b) throws ServiceException,Exception{
		Map map = new HashMap();
		Map dataMap = new HashMap();
		String sData = arrayToString(b);
		StringBuffer s = new StringBuffer("");
		StringBuffer s1 = new StringBuffer("");
		StringBuffer s2 = new StringBuffer("");
		int count = 0;
		boolean boo1 = false;
		int point = 0;
		int[] location = {0,0};
		CustomerData[] cus = new CustomerData[(b.length/34+2)];
		//处理空数据的情况
		map.put("Count", count);
		for(int i=0;i<b.length;){
			s.append((char)b[i++]);
			if((char)b[i-1]=='@')
				boo1 = true;
			if(boo1&&b[i-1]==48){
				location[point++]=i;
				if(point==2)
					point = 0;
			}
			if(boo1&&((location[0]-location[1]==-1)||(location[0]-location[1]==1))){
				map.put("Email", sData.substring(0, i-2));
				while(i<b.length){
					if(sData.substring(i,i+10).equals("1900.01.01")){
						map.put("DataArray", cus);
						map.put("Count", count);
						return map;
					}
					else{
						try{
							cus[count] = new CustomerData();
							cus[count].setHupDate(sData.substring(i,i+10));
							i+=10;
							cus[count].setUpTime(sData.substring(i,i+8));
							i+=8;
							cus[count].setHeartbeat(Integer.parseInt(sData.substring(i,i+3)));
							i+=3;
							cus[count].setHmovement(Integer.parseInt(sData.substring(i,i+3)));
							i+=3;
							cus[count].setRespiration(Integer.parseInt(sData.substring(i,i+3)));
							i+=3;
							if(sData.charAt(i)=='-'){
								double dte = -Double.parseDouble(sData.substring(i+1,i+7));
								cus[count].setTemperature(dte);
								i+=7;
							}else{
								cus[count].setTemperature(Double.parseDouble(sData.substring(i,i+7)));
								i+=7;
							}
							count++;
						}catch(NumberFormatException e){
							throw new ServiceException("类型转换异常");
						}catch(ClassCastException e){
							throw new ServiceException("类型转换异常");
						}catch(IndexOutOfBoundsException e){
							throw new ServiceException("长度越界，数据错误");
						}catch(Exception e){
							throw e;
						}
					}
				}
				map.put("DataMap", map);
			}
		}
		
		return null;
	}
	/**
	 * 将数组转化为String字符串
	 * @return
	 */
	private String arrayToString(byte[] b){
		StringBuffer ss = new StringBuffer("");
		for(int i = 0;i<b.length;i++)
			ss.append((char)b[i]);
		return ss.toString();
	}

}
