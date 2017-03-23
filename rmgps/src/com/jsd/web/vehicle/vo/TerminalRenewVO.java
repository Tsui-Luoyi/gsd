package com.jsd.web.vehicle.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TerminalRenewVO implements Serializable{
	private String vname;// 整机名称
	private String dno;// 终端编号
	private String simnum;// SIM号码
	private String lastrenewdate;// 最后续费时间
	private String renewperiod;// 续费周期

	private String daytime;		//即将到期的天数
	public String getDaytime() {
		return daytime;
	}
	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}
	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getSimnum() {
		return simnum;
	}

	public void setSimnum(String simnum) {
		this.simnum = simnum;
	}

	public String getLastrenewdate() {
		return lastrenewdate;
	}

	public void setLastrenewdate(String lastrenewdate) {
		this.lastrenewdate = lastrenewdate;
	}

	public String getRenewperiod() {
		return renewperiod;
	}

	public void setRenewperiod(String renewperiod) {
		this.renewperiod = renewperiod;
	}

}
