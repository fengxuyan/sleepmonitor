package com.jn.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jn.Utils.ServiceErrorCode;
import com.jn.domain.Customer;
import com.jn.service.ConcernService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 处理亲情关注
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
@ParentPackage("data-package")
@Namespace("/concern")
public class ConcerAction extends ActionSupport {
	
	@Autowired
	private ConcernService concernService;

	public ConcernService getConcernService() {
		return concernService;
	}

	public void setConcernService(ConcernService concernService) {
		this.concernService = concernService;
	}
	
	private JSONObject data = new JSONObject();
	
	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	/**
	 * 查询对应的姓名的用户
	 * @return
	 */
	@Action(value="query",
			results={@Result(name="success",type="json",params={"root","data"})})
	public String query(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String Email = request.getParameter("Email");
		data = concernService.query(Email);
		return "success";
	}

	/**
	 * 申请关注
	 * @return
	 */
	@Action(value="apply",
			results={@Result(name="success",type="json",params={"root","data"})})
	public String apply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = concernService.apply(request);
		return "success";
	}
	/**
	 * 同意申请
	 * myEmail 被申请的人
	 * otherEmail 发出申请的人
	 * @return
	 */
	@Action(value="agree",
			results={@Result(name="success",type="json",params={"root","data"})})
	public String agreeApply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = concernService.agreeApply(request);
		return "success";
	}
	
	/**
	 * 不同意申请
	 * myEmail 被申请的人
	 * otherEmail 发出申请的人
	 * @return
	 */
	@Action(value="disagree",
			results={@Result(name="success",type="json",params={"root","data"})})
	public String disAgreeApply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = concernService.disAgreeApply(request);
		return "success";
	}
	
	/**
	 * 取消关注
	 * @return
	 */
	@Action(value="cancel",
			results={@Result(name="success",type="json",params={"root","data"})})
	public String cancelConcern(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = concernService.cancelConcern(request);
		return "success";
	}
	
	/**
	 * 查询亲情关注页面的信息<br/>
	 *  包括我关注的人，关注我的人，以及申请信息
	 * @return
	 */
	@Action(value="getConcern",
			results={@Result(name="success",type="json",params={"root","data"})})
	public String getConcernInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//String Email = request.getParameter(request);
		data = concernService.getConcern(request);
		return "success";
	}
}
