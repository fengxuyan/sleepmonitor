package com.jn.Utils;
/**
 * ·þÎñÒì³£
 * @author Administrator
 *
 */
public class ServiceException extends Exception {
	Exception e = new Exception("");
	private String message;
	private int eceptionCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getEceptionCode() {
		return eceptionCode;
	}
	public void setEceptionCode(int eceptionCode) {
		this.eceptionCode = eceptionCode;
	}
	
	public ServiceException() {
		super();
	}
	public ServiceException(String message){
		this.message = message;
	}
	public ServiceException(int eceptionCode){
		this.eceptionCode = eceptionCode;
	}
	public ServiceException(String message, int eceptionCode) {
		super();
		this.message = message;
		this.eceptionCode = eceptionCode;
	}
}
