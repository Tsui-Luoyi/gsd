package com.jsd.web.po;

import java.util.Date;

/**
 * TloginHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TloginHistory implements java.io.Serializable {

	// Fields

	private Integer historyId;
	private String userCode;
	private Date loginDate;
	private String loginAddrIp;

	// Constructors

	/** default constructor */
	public TloginHistory() {
	}

	/** minimal constructor */
	public TloginHistory(Integer historyId) {
		this.historyId = historyId;
	}

	/** full constructor */
	public TloginHistory(Integer historyId, String userCode, Date loginDate,
			String loginAddrIp) {
		this.historyId = historyId;
		this.userCode = userCode;
		this.loginDate = loginDate;
		this.loginAddrIp = loginAddrIp;
	}

	// Property accessors

	public Integer getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginAddrIp() {
		return this.loginAddrIp;
	}

	public void setLoginAddrIp(String loginAddrIp) {
		this.loginAddrIp = loginAddrIp;
	}

}