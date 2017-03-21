package com.jn.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CustomerData {
	private String hupDate;//年月日
	private String upTime;//时分秒
	private int heartbeat;
	private int hmovement;
	private int respiration;
	private double  temperature;
	private int extend1;
	private int extend2;
	public String getHupDate() {
		return hupDate;
	}
	public void setHupDate(String hupDate) {
		this.hupDate = hupDate;
	}
	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}
	public int getHeartbeat() {
		return heartbeat;
	}
	public void setHeartbeat(int heartbeat) {
		this.heartbeat = heartbeat;
	}
	public int getHmovement() {
		return hmovement;
	}
	public void setHmovement(int hmovement) {
		this.hmovement = hmovement;
	}
	public int getRespiration() {
		return respiration;
	}
	public void setRespiration(int respiration) {
		this.respiration = respiration;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getExtend1() {
		return extend1;
	}
	public void setExtend1(int extend1) {
		this.extend1 = extend1;
	}
	public int getExtend2() {
		return extend2;
	}
	public void setExtend2(int extend2) {
		this.extend2 = extend2;
	}
	
}
