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
 * ���������ע
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
	 * ��ѯ��Ӧ���������û�
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
	 * �����ע
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
	 * ͬ������
	 * myEmail ���������
	 * otherEmail �����������
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
	 * ��ͬ������
	 * myEmail ���������
	 * otherEmail �����������
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
	 * ȡ����ע
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
	 * ��ѯ�����עҳ�����Ϣ<br/>
	 *  �����ҹ�ע���ˣ���ע�ҵ��ˣ��Լ�������Ϣ
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
