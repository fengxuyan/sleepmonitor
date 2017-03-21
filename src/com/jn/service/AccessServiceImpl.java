package com.jn.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jn.Utils.EmailChange;
import com.jn.Utils.SaveHead;
import com.jn.Utils.SendEmailUtil;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.UserTable;
import com.jn.dao.AccessDao;
import com.jn.domain.Customer;
@Service("AccessService")
public class AccessServiceImpl implements AccessService {
	@Autowired
	private AccessDao accessDao;
	
	public AccessDao getAccessDao() {
		return accessDao;
	}

	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	
	@Override
	public JSONObject login(String Email,String password){
		JSONObject data = new JSONObject();
		JSONObject userInfo = new JSONObject();
		String tableName = UserTable.getTableName(Email);
		data =  accessDao.login(Email,tableName,password);
		List list = new ArrayList();
		JSONArray json = new JSONArray();
		if((Integer)data.get("Code")==1){
			json = (JSONArray)data.get("data");
			list = (List) json.get(0);
			String type1Ip = null;
			String type2Ip = null;
			String type3Ip = null;
			String sn3 = null;
			String type = null;
			if(list.get(5)==""||"null".equals(list.get(5).toString())){
				type1Ip = "";
			}else{
				sn3 = ((String)list.get(5)).substring(0,3);
				type = ((String)list.get(5)).substring(3,4);
				type1Ip = accessDao.getIp(sn3, type);
				while("fail".equals(type1Ip)){
					type1Ip = accessDao.getIp(sn3, type);
				}
			}
			if(list.get(7)==""||"null".equals(list.get(7).toString())){
				type2Ip = "";
			}else{
				sn3 = ((String)list.get(7)).substring(0,3);
				type = ((String)list.get(7)).substring(3,4);
				type1Ip = accessDao.getIp(sn3, type);
				while("fail".equals(type1Ip)){
					type1Ip = accessDao.getIp(sn3, type);
				}
			}
			if(list.get(9)==""||"null".equals(list.get(9).toString())){
				type3Ip = "";
			}else{
				sn3 = ((String)list.get(9)).substring(0,3);
				type = ((String)list.get(9)).substring(3,4);
				type1Ip = accessDao.getIp(sn3, type);
				while("fail".equals(type1Ip)){
					type1Ip = accessDao.getIp(sn3, type);
				}
			}
			userInfo.put("Email",getInfo((Object) list.get(0)));
			userInfo.put("firstName",getInfo((Object) list.get(1)));
			userInfo.put("middleName",getInfo((Object) list.get(2)));
			userInfo.put("lastName",getInfo((Object) list.get(3)));
			userInfo.put("image",getInfo((Object) list.get(4)));
			userInfo.put("type1sn",getInfo((Object) list.get(5)));
			userInfo.put("type1Ip",type1Ip);
			userInfo.put("level1",getInfo((Object) list.get(6)));
			userInfo.put("deadline1",getInfo((Object) list.get(11)));
			userInfo.put("type2sn",getInfo((Object) list.get(7)));
			userInfo.put("type2Ip",type2Ip);
			userInfo.put("level2",getInfo((Object) list.get(8)));
			userInfo.put("deadline2",getInfo((Object) list.get(12)));
			userInfo.put("type3sn",getInfo((Object) list.get(9)));
			userInfo.put("type3Ip",type3Ip);
			userInfo.put("level3",getInfo((Object) list.get(10)));
			userInfo.put("deadline3",getInfo((Object) list.get(13)));
			userInfo.put("phoneNumber",getInfo((Object) list.get(14)));
			userInfo.put("gender",getInfo((Object) list.get(15)));
			userInfo.put("yearOfBirth",getInfo((Object) list.get(16)));
			userInfo.put("address",getInfo((Object) list.get(17)));
			userInfo.put("password",getInfo((Object) list.get(18)));
			data.put("data", userInfo);
		}
		return data;
	}
	
	/**
	 * 将JSONNull转换为“”
	 * @return
	 */
	private String getInfo(Object json){
		if("null".equals(json.toString()))
			return "";
		else{
			return json.toString();
		}
	}

	@Override
	public JSONObject saveUser(Customer customer) throws Exception{
		JSONObject data = new JSONObject();
		String tableName = UserTable.getTableName(customer.getEmail());
		String info = null;
		try{
			info = accessDao.searchEmail(customer.getEmail(), tableName);
			if("exist".equals(info)){
				data.put("Code", ServiceErrorCode.EMAIL_EXIST);
				data.put("Message","Email is already Exist!");
			}else{
				data = accessDao.saveUser(customer,tableName);
			}
		}catch(Exception e){
			data.clear();
			data.put("Code", ServiceErrorCode.STATS_ERROR);
			data.put("Message", "Error in Server.Please Try Again!");
		}
		return data;
	}

	@Override
	public JSONObject getPassword(String Email) {
		JSONObject data = new JSONObject();
		String tableName = UserTable.getTableName(Email);
		String info = null;
		try {
			info = accessDao.getPassword(Email, tableName);
			if(info!=null){
				boolean flog = new SendEmailUtil().send(info, Email);
				if(flog){
					data.put("Code",ServiceErrorCode.SEND_SUCCESS);
					data.put("Message", "Password has Send Successfully!");
				}else{
					data.put("Code",ServiceErrorCode.SEND_FAIL);
					data.put("Message", "Failed to Send Pasword.Please Try Again!");
				}
				
			}else{
				data.put("Code",ServiceErrorCode.EMAIL_NO_EXIST);
				data.put("Message", "Email is not Exist!");
			}
		} catch (Exception e) {
			data.clear();
			data.put("Code", ServiceErrorCode.STATS_ERROR);
			data.put("Message", "Error in Server.Please Try Again!");
		}
		return data;
	}
	
	@Override
	public JSONObject modify(Customer customer,File head){
		JSONObject data = new JSONObject();
		try{
			String tableName = UserTable.getTableName(customer.getEmail());
			String URL = null;
			if(head!=null)
				URL = new SaveHead().saveHead(customer.getEmail(), head);
			else
				URL = accessDao.getImage(customer.getEmail(), tableName);
			customer.setImage(URL);
			data = accessDao.modify(tableName,customer);
		}catch(Exception e){
			data.put("Code", ServiceErrorCode.HEAD_ERROR);
			data.put("Message", "Error while saving image.Please Try Again!");
		}
		return data;
	}
	
	@Override
	public JSONObject pay(String type,String Email){
		String tableName = UserTable.getTableName(Email);
		return accessDao.pay(Email, type, tableName);
	}
	
	
	
	/*
	public Customer getUserInfo(String Email){
		Customer cus = accessDao.getUserInfo(Email);
		if(cus.getSn()!=null&&!("".equals(cus.getSn()))){
			String[] sn = cus.getSn().split("0010A0");
			cus.setSn(sn[1]);
		}
		return cus;
	}
	@Override
	public ServiceResult modifyInfo(Customer customer,String flog) throws Exception{
		SNInfo snInfo = null;
		int count = 0;
		ServiceResult ret = new ServiceResult();
		snInfo = accessDao.queryInfo("t_SNInfo","Email",customer.getEmail());
		if(customer.getSn()==null||("".equals(customer.getSn()))){
			//snInfo = accessDao.queryInfo("t_SNInfo","Email",customer.getEmail());
			//该用户已绑定有产品，现在解绑
			if(snInfo!=null){
				customer.setGrade(0);//原来绑定有产品，现在解绑，等级设为0
				//修改信息并清除原来产品信息
				if("clear".equals(flog)){
					count = accessDao.deleteData(customer.getSn());
					if(count != 0){
						return accessDao.modifyInfo(customer);
					}else{
						ret.setResult("Code", ServiceErrorCode.STATS_ERROR);
						ret.setResult("Message", "Failed to clear your data.Please try again.");
						return ret;
					}
				}
				//修改信息但保留原来产品信息
				else{
					count = accessDao.updateSN(snInfo.getSn(), null);
					if(count != 0){
						return accessDao.modifyInfo(customer);
					}else{
						ret.setResult("Code", ServiceErrorCode.STATS_ERROR);
						ret.setResult("Message", "Failed to modify your Info.");
						return ret;
					}
				}
			}else{
				return accessDao.modifyInfo(customer);
			}
		}
		//用户输入了SN，重新绑定，或者是新绑定
		else{
			//SN先拼接上0010A0
			customer.setSn("0010A0"+customer.getSn());
			customer.setGrade(1);
			//查询SN是否存在
			SNInfo sn = accessDao.queryInfo("t_SNInfo","sn",customer.getSn());
			//SN存在
			if(sn!=null){
				//若该用户对应的SN为空，则是新绑定
				if(snInfo==null){
					if(sn.getEmail()==null||!"".equals(sn.getEmail())){
						accessDao.updateSN(customer.getSn(), customer.getEmail());
						return accessDao.modifyInfo(customer);
					}else{
						ret.setResult("Code", ServiceErrorCode.SN_EMAIL_EXIST);
						ret.setResult("Message", "The SN has attached a Product.");
						return ret;
					}
					
				}
				//当前SN和SN表该用户的SN相同，则为修改信息
				else if(customer.getSn().equals(snInfo.getSn()))
						return accessDao.modifyInfo(customer);
					else {
						//清除原信息并重新绑定
						if("clear".equals(flog)){
							//清楚原SN对应的数据
							count = accessDao.deleteData(snInfo.getSn());
							//解除绑定
							int count1 = accessDao.updateSN(snInfo.getSn(), null);
							if(count != 0 && count1!=0){
								//根据当前用户输入的SN绑定Email
								accessDao.updateSN(customer.getSn(), customer.getEmail());
								return accessDao.modifyInfo(customer);
							}else{
								ret.setResult("Code", ServiceErrorCode.STATS_ERROR);
								ret.setResult("Message", "Failed to clear your data or Unattach.Please try again.");
								return ret;
							}
						}
						//重新绑定，但不清除原信息
						else{
							//解除绑定
							int count1 = accessDao.updateSN(snInfo.getSn(), null);
							if(count1!=0){
								//根据当前用户输入的SN绑定Email
								accessDao.updateSN(customer.getSn(), customer.getEmail());
								return accessDao.modifyInfo(customer);
							}else{
								ret.setResult("Code", ServiceErrorCode.STATS_ERROR);
								ret.setResult("Message", "Failed to clear your data or Unattach.Please try again.");
								return ret;
							}
						}
					}
			}else{
				//SN不存在
				ret.setResult("Code", ServiceErrorCode.SN_NOT_EXIST);
				ret.setResult("Message", "The SN is not Exist");
				return ret;
			}
		}
	}*/
}

