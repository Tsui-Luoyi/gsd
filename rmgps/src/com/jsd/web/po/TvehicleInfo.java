package com.jsd.web.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TvehicleInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TvehicleInfo implements java.io.Serializable {

	// Fields

	private Integer vid;
	private TvehicleModel tvehicleModel;
	private String vno;
	private String vname;
	private String engineNo;
	private String carframeno;
	private String vcolor;
	private Integer vweight;
	private String trademark;
	private String picPath;
	private Integer initMoto;
	private String orgNo;
	private Date leaveDate;
	private Date inputTime;
	private Set tvvBands = new HashSet(0);
	private TorgTable torgTable;
	private LoginState loginState;
	private String sellrd;
	
	// Constructors

	public String getSellrd() {
		return sellrd;
	}

	public void setSellrd(String sellrd) {
		this.sellrd = sellrd;
	}

	/** default constructor */
	public TvehicleInfo() {
	}

	/** minimal constructor */
	public TvehicleInfo(Integer vid, String vno, String vname) {
		this.vid = vid;
		this.vno = vno;
		this.vname = vname;
	}

	/** minimal constructor */
	public TvehicleInfo(Integer vid, String vno, TvehicleModel tvehicleModel,
			String vname) {
		this.vid = vid;
		this.vno = vno;
		this.tvehicleModel = tvehicleModel;
		this.vname = vname;
	}
	/** mini007 改造3后使用*/
	public TvehicleInfo(Integer vid, String vno, String vname, String engineNo) {
		super();
		this.vid = vid;
		this.vno = vno;
		this.vname = vname;
		this.engineNo = engineNo;
	}

	/** full constructor */
	public TvehicleInfo(Integer vid, TvehicleModel tvehicleModel, String vno,
			String vname, String engineNo, String carframeno, String vcolor,
			Integer vweight, String trademark, String picPath,
			Integer initMoto, String orgNo, Date leaveDate, Date inputTime,
			Set tvvBands,String sellrd) {
		this.vid = vid;
		this.tvehicleModel = tvehicleModel;
		this.vno = vno;
		this.vname = vname;
		this.engineNo = engineNo;
		this.carframeno = carframeno;
		this.vcolor = vcolor;
		this.vweight = vweight;
		this.trademark = trademark;
		this.picPath = picPath;
		this.initMoto = initMoto;
		this.orgNo = orgNo;
		this.leaveDate = leaveDate;
		this.inputTime = inputTime;
		this.tvvBands = tvvBands;
		this.sellrd = sellrd;
	}

	// Property accessors

	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public TvehicleModel getTvehicleModel() {
		return this.tvehicleModel;
	}

	public void setTvehicleModel(TvehicleModel tvehicleModel) {
		this.tvehicleModel = tvehicleModel;
	}

	public String getVno() {
		return this.vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getEngineNo() {
		return this.engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getCarframeno() {
		return this.carframeno;
	}

	public void setCarframeno(String carframeno) {
		this.carframeno = carframeno;
	}

	public String getVcolor() {
		return this.vcolor;
	}

	public void setVcolor(String vcolor) {
		this.vcolor = vcolor;
	}

	public Integer getVweight() {
		return this.vweight;
	}

	public void setVweight(Integer vweight) {
		this.vweight = vweight;
	}

	public String getTrademark() {
		return this.trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getInitMoto() {
		return this.initMoto;
	}

	public void setInitMoto(Integer initMoto) {
		this.initMoto = initMoto;
	}

	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Set getTvvBands() {
		return this.tvvBands;
	}

	public void setTvvBands(Set tvvBands) {
		this.tvvBands = tvvBands;
	}

	public TorgTable getTorgTable() {
		return torgTable;
	}

	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}

	public LoginState getLoginState() {
		return loginState;
	}

	public void setLoginState(LoginState loginState) {
		this.loginState = loginState;
	}

}