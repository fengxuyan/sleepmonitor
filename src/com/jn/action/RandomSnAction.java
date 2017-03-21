package com.jn.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jn.Utils.RequestUtils;
import com.jn.Utils.ServiceResult;
import com.jn.service.RandomSNService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 生成sn
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
@ParentPackage("data-package")
@Namespace(value="/random")
public class RandomSnAction extends ActionSupport{
	
	private int number;
	private String type;
	private String level;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Autowired
	private RandomSNService snService;
	
	@Autowired
	private ServiceResult ret;

	public ServiceResult getRet() {
		return ret;
	}

	public void setRet(ServiceResult ret) {
		this.ret = ret;
	}
	
	private JSONObject data = new JSONObject();

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	private String ip;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 根据SN找到对应的子服务器IP,并将SN存入SNT表中</br>
	 * 然后发送将SN发送到子服务器，子服务器上创建对应的实时信息表
	 * @return
	 */
	@Action(value="getSN",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="fail",type="json",params={"root","data"})})
	public String getSN(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = snService.getIp(type, level);
		if((Integer)data.get("Code")==1){
			ip = (String)data.get("Message");
			data.clear();
			data = snService.saveSN(number, type, level);
			if((Integer)data.get("Code")==1){
				//String url = "http://"+ip+"/SleepMonitor/random/createTable.action";
				String url = "http://118.178.181.188:8080/SleepMonitor/random/createTable.action";
				List list = snService.snAll;
				StringBuffer params = new StringBuffer("");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1)
						params.append("sn"+i+"="+(String)list.get(i));
					else{
						params.append("sn"+i+"="+(String)list.get(i)+"&");
					}
				}
				String ret = "fail";
				try {
					while(!"\"success\"".equals(ret)){
						ret = RequestUtils.sendPost(url, params.toString());
						ret.charAt(0);
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");//设置日期格式
					String fileName = df.format(new Date())+".txt";
					boolean flog = false;
					while(!flog){
						flog = snService.writeSn((List)data.get("SN"),fileName);
					}
					data.put("Message", "http://"+ip+"/SleepMonitor/SNFile/"+fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "success";
		}else{
			return "fail";
		}
		
	}
	
	
	private String returnRet;
	
	public String getReturnRet() {
		return returnRet;
	}

	public void setReturnRet(String returnRet) {
		this.returnRet = returnRet;
	}

	@Action(value="createTable",
			results={@Result(name="success",type="json",params={"root","returnRet"})})
	public String createTable(){
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean flog = true;
		int i = 0;
		List<String> list = new ArrayList<String>();
		while(flog){
			if(request.getParameter("sn"+i)==null){
				i++;
				break;
			}
			else{
				list.add((String)request.getParameter("sn"+i++));
				i++;
			}
		}
		returnRet = snService.createTable(list);
		
		return "success";
	}
}
