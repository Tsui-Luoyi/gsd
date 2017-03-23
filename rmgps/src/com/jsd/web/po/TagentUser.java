package com.jsd.web.po;

import java.util.Date;


public class TagentUser {
	private Integer id; 
	private String ip;
	private Date time;
	private Integer dengluId;//操作者id
	private String beiId;//被操作id
	private String des;
	
	public String getBeiId() {
		return beiId;
	}
	public void setBeiId(String beiId) {
		this.beiId = beiId;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getDengluId() {
		return dengluId;
	}
	public void setDengluId(Integer dengluId) {
		this.dengluId = dengluId;
	}
	
}
