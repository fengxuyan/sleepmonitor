package com.jn.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.jn.Utils.ServiceResult;

public interface DataUploadService {

	public ServiceResult dataUpload(HttpServletRequest request,File file);
	
}
