package com.jsd.web.po;

import java.io.Serializable;
import java.util.Date;

public class LoginState implements Serializable {
	private String vno;
	private String linkUuid;
	private String opt;
	private Date onlineTime;
	private String offlineTime;

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getLinkUuid() {
		return linkUuid;
	}

	public void setLinkUuid(String linkUuid) {
		this.linkUuid = linkUuid;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

}
