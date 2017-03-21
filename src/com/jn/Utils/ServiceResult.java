package com.jn.Utils;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class ServiceResult {
	
//	private HashMap<String, Integer> code = new HashMap<String, Integer>();
//	private HashMap<String, String> message = new HashMap<String, String>();
//	private String[] date = null;
//	private HashMap<String, Object> object = new HashMap<String, Object>();
	
	private HashMap<String, Object> result = new HashMap<String, Object>();

	public HashMap<String, Object> getResult() {
		return result;
	}
	public Object getResult(String message) {
		return result.get(message);
	}

	public void setResult(String Message,Object object) {
		result.put(Message, object);
	}
	
	public void deleteResult(String Message) {
		result.remove(Message);
	}
	
	
}
