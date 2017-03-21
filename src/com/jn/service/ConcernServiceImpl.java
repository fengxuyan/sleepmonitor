package com.jn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jn.Utils.EmailChange;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.UserTable;
import com.jn.dao.ConcernDao;

@Service("ConcernService")
public class ConcernServiceImpl implements ConcernService {

	@Autowired
	private ConcernDao concernDao;
	
	public ConcernDao getConcernDao() {
		return concernDao;
	}

	public void setConcernDao(ConcernDao concernDao) {
		this.concernDao = concernDao;
	}
	
	@Override
	public JSONObject apply(HttpServletRequest request) {
		JSONObject data = new JSONObject();
		int count = 0;
		String myEmail = request.getParameter("myEmail");
		String otherEmail = request.getParameter("otherEmail");
		String first = request.getParameter("firstName");
		String middle = request.getParameter("middleName");
		String last = request.getParameter("lastName");
		String userTable = UserTable.getTableName(myEmail);
		String myAttentionTable = "GZ"+new EmailChange().emailChange(myEmail);
		if (myEmail.equals(otherEmail)) {
			data.put("Code", ServiceErrorCode.PARAM_REPEAT);
			data.put("Message", "Do not Try to Concern Yourself!");
		}else{
			//从我的关注表里查询对应邮箱是否已经存在防止重复关注
			List list = concernDao.query(myAttentionTable, otherEmail);
			if (list.size() == 0) {
				//从我的待处理关注表里查询对应邮箱是否已经存在防止重复申请
				String qqAttentionTable = "QQ"+new EmailChange().emailChange(otherEmail);
				List list1 = concernDao.query(qqAttentionTable, myEmail);
				if(list1.size() == 0){
					count = concernDao.apply(myEmail, otherEmail, userTable, first,
							middle, last);
					if (count == 0) {
						data.put("Code", ServiceErrorCode.APPLY_ERROR);
						data.put("Message", "Error in Apply.Please Try Again!");
					} else {
						data.put("Code", ServiceErrorCode.APPLY_SUCCESS);
						data.put("Message", "Successfully Apply!");
					}
				}else{
					data.put("Code", ServiceErrorCode.EMAIL_EXIST);
					data.put("Message", "You have applied.Please Do not Repeat apply!");
				}
			} else {
				data.put("Code", ServiceErrorCode.EMAIL_EXIST);
				data.put("Message", "The User has been Concerned!");
			}
		}
		return data;
	}
	
	@Override
	public JSONObject query(String Email) {
		JSONObject data = new JSONObject();
		String tableName = UserTable.getTableName(Email);
		List list = null;
		StringBuffer name = new StringBuffer("");
		list = concernDao.query(tableName,Email);
		if(list==null){
			data.put("Code", ServiceErrorCode.SEARCH_ERROR);
			data.put("Message", "Error in Search.Please Try Again!");
		}else if(list.size()==0){
			data.put("Code", ServiceErrorCode.SEARCH_NO_RESULT);
			data.put("Message", "User is not Exist!");
		}else if(list.size()==1){
			Object names[] = (Object[]) list.get(0);
			for(int i=0;i<3;i++){
				if(i==0)
					name.append(names[0]);
				else if(i==1){
					if(names[1]==null||names[1]=="")
						name.append(" ");
					else
						name.append(" "+names[1]+" ");
				}else{
					name.append(names[2]);
				}
			}
			data.put("Code", ServiceErrorCode.STATS_OK);
			data.put("Message", "Successfully Search!");
			data.put("data", name.toString());
			data.put("Email", names[3]);
			data.put("Image", names[4]);
		}
		return data;
	}
	
	@Override
	public JSONObject agreeApply(HttpServletRequest request){
		JSONObject data = new JSONObject();
		String myEmail = request.getParameter("myEmail");
		String otherEmail = request.getParameter("otherEmail");
		String first = request.getParameter("firstName");
		String middle = request.getParameter("middleName");
		String last = request.getParameter("lastName");
		String otherName = request.getParameter("otherName");
		try {
			concernDao.agreeApply(myEmail, otherEmail, first, middle, last,otherName);
			data.put("Code", ServiceErrorCode.DEAL_SUCCESS);
			data.put("Message", "Successfully to Deal the Apply!");
		} catch (Exception e) {
			data.put("Code", ServiceErrorCode.DEAL_ERROR);
			data.put("Message", "Failed to Deal the Apply.Please Try Again!");
		}
		return data;
	}
	
	@Override
	public JSONObject disAgreeApply(HttpServletRequest request){
		JSONObject data = new JSONObject();
		String myEmail = request.getParameter("myEmail");
		String otherEmail = request.getParameter("otherEmail");
		try {
			concernDao.disAgreeApply(myEmail, otherEmail);
			data.put("Code", ServiceErrorCode.DEAL_SUCCESS);
			data.put("Message", "Successfully to Deal the Apply!");
		} catch (Exception e) {
			data.put("Code", ServiceErrorCode.DEAL_ERROR);
			data.put("Message", "Failed to Deal the Apply.Please Try Again!");
		}
		return data;
	}
	
	
	@Override
	public JSONObject cancelConcern(HttpServletRequest request){
		JSONObject data = new JSONObject();
		String myEmail = request.getParameter("myEmail");
		String otherEmail = request.getParameter("otherEmail");
		String flog = request.getParameter("flog");
		try {
			concernDao.cancelConcern(myEmail, otherEmail, flog);
			data.put("Code", ServiceErrorCode.CANCEL_SUCCESS);
			data.put("Message", "Successfully to Cancel the Concern!");
		} catch (Exception e) {
			data.put("Code", ServiceErrorCode.CANCEL_ERROR);
			data.put("Message", "Failed to Cancel the Concern.Please Try Again!");
		}
		return data;
	}
	
	@Override
	public JSONObject getConcern(HttpServletRequest request){
		String Email = request.getParameter("Email");
		return concernDao.getConcern(Email);
	}

}
