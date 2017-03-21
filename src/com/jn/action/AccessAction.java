package com.jn.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.jn.Utils.SendEmailUtil;
import com.jn.Utils.ServiceErrorCode;
import com.jn.domain.Customer;
import com.jn.service.AccessService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope("prototype")
@ParentPackage("data-package")
@Namespace(value="/login")
public class AccessAction extends ActionSupport implements ModelDriven<Customer>{
	
	@Autowired
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	@Autowired
	private AccessService accessService;
	
	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	@Autowired
	private SendEmailUtil sendEmailUtil;
	
	//返回的JSON数据
	private JSONObject data = new JSONObject();

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
	
	private File head = null;
	
	public File getHead() {
		return head;
	}

	public void setHead(File head) {
		this.head = head;
	}

	/**
	 * 登录验证
	 * @return
	 */
	@Action(value="login",
			results={@Result(name="success",type="json",params={"root","data"}),  
					 @Result(name="fail",type="json",params={"root","data"}),
					 @Result(name="input",type="json",params={"root","data"})}
			)
	public String loginVerifiCation(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String Email = request.getParameter("Email");
		String password = request.getParameter("password");
		String yzm1 = request.getParameter("yzm");
		//若验证码有内容，则为pc端，否则是APP端
		if(yzm1!=null&&!"".equals(yzm1)){
			String yzm2 = (String) ActionContext.getContext().getSession().get("rand");
			if(yzm1.equals(yzm2))
				data = accessService.login(Email,password);
			else{
				data.put("Code", ServiceErrorCode.STATS_ERROR);
				data.put("Message", "Error in VerifiCation Code!");
			}
		}else{
			data = accessService.login(Email,password);
		}
		return "success";
	}
	/**
	 * 注册
	 * @return
	 * @throws Exception 
	 */
	@Action(value="register",
			results={@Result(name="success",type="json",params={"root","data"})}
			) 
	public String register() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		customer.setImage("/SleepMonitor/Heads/head_test.jpg");
		data = accessService.saveUser(customer);
		return "success";
	}
	/**
	 * 发送密码
	 * @return
	 */
	@Action(value="forget",
			results={@Result(name="success",type="json",params={"root","data"})}
			)
	public String sendPassword(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String Email = request.getParameter("Email");
		data = accessService.getPassword(Email);
		return "success";
	}
	
	/**
	 * 修改信息
	 */
	@Action(value="modify",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="input",type="json",params={"root","data"})})
	public String modify(){
		if(customer.getEmail()==null){
			data.put("Code", ServiceErrorCode.EMAIL_NO_EXIST);
			data.put("Message", "Email Should not Be Empty!");
		}else{
			data = accessService.modify(customer,head);
		}
		return "success";
	}
	
	/**
	 * 
	 * @return
	 */
	@Action(value="pay",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="input",type="json",params={"root","data"})})
	public String pay(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//type:1  床垫，type:2  枕头，type:3  手环
		String type = request.getParameter("type");
		String money = request.getParameter("money");
		String Email = request.getParameter("Email");
		data = accessService.pay(type,Email);
		return "success";
	}
	
	
	
	/*private JSONObject gpassword;
	

	public JSONObject getGpassword() {
		return gpassword;
	}

	public void setGpassword(JSONObject gpassword) {
		this.gpassword = gpassword;
	}
	@Action(value="login",
			results={@Result(name="success",type="json",params={"root","gpassword"}),  
					 @Result(name="fail",type="json",params={"root","gpassword"}),
					 @Result(name="input",type="json",params={"root","gpassword"})}
			)
	public String loginverifiCation(){
		JSONObject pa = new JSONObject();
		Customer existCustomer = accessService.loginVerification(customer);
		json = JSONObject.fromObject(existCustomer);
		String s = (String) ActionContext.getContext().getSession().get("rand");
		if(verification!=null){
			if(!verification.equals(s)){
				this.addActionError("Error in Verification Code!");
				this.setEmail(customer.getEmail());
				this.setRet("Error in Verification Code!");
				pa.put("Code", ServiceErrorCode.STATS_ERROR);
				pa.put("Message", "Error in Verification Code!");
				this.setGpassword(pa);
				return "fail";
			}
		}else{
			this.addActionError("Verification Code should not Be Null!");
			this.setEmail(customer.getEmail());
			this.setRet("Verification Code should not Be Null!");
			pa.put("Code", ServiceErrorCode.STATS_ERROR);
			pa.put("Message", "Verification Code should not Be Null!");
			this.setGpassword(pa);
			return "fail";
		}
			
		if(existCustomer == null){
			this.addActionError("Error in Email or Password!");
			this.setEmail(customer.getEmail());
			this.setRet("Error in Email or Password!");
			pa.put("Code", ServiceErrorCode.USER_ERROR);
			pa.put("Message", "Error in Email or Password!");
			this.setGpassword(pa);
			return "fail";
		}
		else{
			if(existCustomer.getMiddleName()!=null){
				this.setUser(existCustomer.getFirstName()+"%20"+existCustomer.getMiddleName()+"%20"+existCustomer.getLastName());
				pa.put("Code", ServiceErrorCode.STATS_OK);
				pa.put("Message", "SUCCESS!");
				pa.put("User", this.getUser());
				this.setGpassword(pa);
			}
			else{
				this.setUser(existCustomer.getFirstName()+"%20"+existCustomer.getLastName());
				pa.put("Code", ServiceErrorCode.STATS_OK);
				pa.put("Message", "SUCCESS!");
				pa.put("User", this.getUser());
				this.setGpassword(pa);
			}
				
			return "success";
		}
	}
	
	@Action(value="Register",
			results={@Result(name="success",type="json",params={"root","gpassword"}),  
					 @Result(name="fail",type="json",params={"root","gpassword"}),
					 @Result(name="input",type="json",params={"root","gpassword"})}
			) 
	public String Register() throws Exception{
		JSONObject pa = new JSONObject();
		ServiceResult sret = new ServiceResult();
		sret = accessService.saveUser(customer);
		switch((Integer)sret.getResult().get("Code")){
		case 0:
			if(customer.getSn()!=null&&!"".equals(customer.getSn()))
				this.setSn(customer.getSn().split("0010A0")[1]);
			this.setRet("Unknow Error!");
			pa.put("Code", ServiceErrorCode.STATS_ERROR);
			pa.put("Message", "Unknow Error!");
			this.setGpassword(pa);
			return "fail";
		case 1:
			return "success";
		case 1002:
			if(customer.getSn()!=null&&!"".equals(customer.getSn()))
				this.setSn(customer.getSn().split("0010A0")[1]);
			this.setRet("The Email already exist.Please register again");
			pa.put("Code", ServiceErrorCode.EMAIL_EXIST);
			pa.put("Message", "The Email already exist!");
			this.setGpassword(pa);
			return "fail";
		default:
			if(customer.getSn()!=null&&!"".equals(customer.getSn()))
				this.setSn(customer.getSn().split("0010A0")[1]);
			this.setRet("Unknow Error!");
			pa.put("Code", ServiceErrorCode.STATS_ERROR);
			pa.put("Message", "Unknow Error!");
			this.setGpassword(pa);
			return "fail";
		}
		
	}
	
	@Action(value="getpassword",
			results={@Result(name="success",type="json",params={"root","gpassword"}),  
					 @Result(name="fail",type="json",params={"root","gpassword"})}
			)
	public String SendPassword(){
		JSONObject pa = new JSONObject();
		if(customer.getEmail()==null){
			pa.put("Code", ServiceErrorCode.EMAIL_NO_EXIST);
			pa.put("Message", "Email does not Exist!");
			this.setGpassword(pa);
			return "fail";
		}
		String existPass = accessService.getPassword(customer);
		if(existPass!=null){
			sendEmailUtil.send(existPass, customer.getEmail());
			pa.put("Code", ServiceErrorCode.STATS_OK);
			pa.put("Message", "Password has been sent to your mailbox!");
			this.setGpassword(pa);
			this.addActionError("Send Successfully");
			this.setRet("Password has been sent to your mailbox!");
			return "success";
		}else{
			this.setEmail(customer.getEmail());
			pa.put("Code", ServiceErrorCode.EMAIL_NO_EXIST);
			pa.put("Message", "Email does not Exist!");
			this.setGpassword(pa);
			this.setRet("Email does not Exist");
			this.addActionError("Email does not Exist");
			return "fail";
		}
	}*/
	
}
