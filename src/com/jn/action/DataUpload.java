package com.jn.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jn.Utils.SaveHead;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.ServiceResult;
import com.jn.service.DataUploadService;
import com.opensymphony.xwork2.ActionSupport;
@Component
@Scope("prototype")
@ParentPackage(value="data-package")
@Namespace(value="/dataUpload")
public class DataUpload extends ActionSupport{
	
	
	private String fileUrl = null;

	public String getFileUri() {
		return fileUrl;
	}

	public void setFileUri(String fileUri) {
		this.fileUrl = fileUri;
	}
	
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Autowired
	private ServiceResult ret;
	
	public ServiceResult getRet() {
		return ret;
	}

	public void setRet(ServiceResult ret) {
		this.ret = ret;
	}

	@Autowired
	private DataUploadService dataUploadService;

	@Action(value="uploadFile",
			results={@Result(name="success",type="json",params={"root","ret"}),
			 		 @Result(name="fail",type="json",params={"root","ret"})}
			)
	public String dataUpload() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String s = request.getParameter("test");
		new SaveHead().saveHead(s, file);
		if(request.getContentType()==null||"Get".equals(request.getContentType())){
			ret.setResult("Code",ServiceErrorCode.REQUEST_TYPE);
			ret.setResult("Message","Request Type Error");
			return "fail";
		}
			
		//ret = dataUploadService.dataUpload(request,file);
		if((Integer)ret.getResult().get("Code")==0)
			return "success";
		return "fail";
	}

}
