package com.jn.action;

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
import com.jn.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 添加或删除设备
 * @author Administrator
 *
 */

@Component
@Scope("prototype")
@ParentPackage("data-package")
@Namespace("/product")
public class ProductAction extends ActionSupport {

	private JSONObject data = new JSONObject();
	
	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	@Autowired
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Action(value="add",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="fail",type="json",params={"root","data"})})
	public String addProduct(){
		HttpServletRequest request= ServletActionContext.getRequest();
		data = productService.addProduct(request);
		return "success";
	}
	
	@Action(value="delete",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="fail",type="json",params={"root","data"})})
	public String deleteProduct(){
		HttpServletRequest request= ServletActionContext.getRequest();
		data = productService.deleteProduct(request);
		return "success";
	}
	
	
	private String returnRet;
	
	public String getReturnRet() {
		return returnRet;
	}

	public void setReturnRet(String returnRet) {
		this.returnRet = returnRet;
	}
	//子服务器端程序，根据SN来转移数据
	@Action(value="transData",
			results={@Result(name="success",type="json",params={"root","returnRet"})})
	public String transData(){
		HttpServletRequest request= ServletActionContext.getRequest();
		returnRet = productService.transData(request);
		return "success";
	}
	
	//子服务器程序，删除对应SN的数据
	@Action(value="deleteData",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="fail",type="json",params={"root","data"})})
	public String deleteData(){
		HttpServletRequest request= ServletActionContext.getRequest();
		returnRet = productService.deleteData(request);
		return "success";
	}
	
	//子服务器程序，将旧设备在另一服务器上的数据转移到新设备的服务器上
	@Action(value="getDataFromOld",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="fail",type="json",params={"root","data"})})
	public String getDataFromOld(){
		HttpServletRequest request= ServletActionContext.getRequest();
		returnRet = productService.getDataFromOld(request);
		return "success";
	}
	
	//子服务器程序，将旧设备所在服务器上的数据删除
	@Action(value="deleteOldData",
			results={@Result(name="success",type="json",params={"root","data"}),
					 @Result(name="fail",type="json",params={"root","data"})})
	public String deleteOldData(){
		HttpServletRequest request= ServletActionContext.getRequest();
		returnRet = productService.deleteOldData(request);
		return "success";
	}
	
	
}
