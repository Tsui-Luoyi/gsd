package com.jsd.web.po;

/**
 * MAC地址表
 * 
 * @author Louie
 * 
 */
public class UserMac {
	private Integer id;
	private Integer uid; //用户ID
	private String mac; //mac地址
	private String cpuNum;  //cup编号

	/*
	 * get(),set()
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}

	@Override
	public String toString() {
		return "UserMac [id=" + id + ", uid=" + uid + ", mac=" + mac
				+ ", cpuNum=" + cpuNum + "]";
	}

}
