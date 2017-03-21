package com.jn.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * sn±í
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class SNInfo {
	private int sid;
	private String sn;
	private String email;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SNInfo(String sn){
		this.sn = sn;
	}
	
	public SNInfo() {
		super();
	}
	public SNInfo(int sid, String sn, String email) {
		super();
		this.sid = sid;
		this.sn = sn;
		this.email = email;
	}
	
}
