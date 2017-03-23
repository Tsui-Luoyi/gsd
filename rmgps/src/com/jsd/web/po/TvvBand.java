package com.jsd.web.po;

import java.util.Date;

/**
 * TvvBand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TvvBand implements java.io.Serializable {

	// Fields

	private Integer bindNo;
	private DevtermInfo devtermInfo;
	private TvehicleInfo tvehicleInfo;
	private Date bindDate;
	private Integer userId;
	private String bindNote;

	// Constructors

	/** default constructor */
	public TvvBand() {
	}

	/** minimal constructor */
	public TvvBand(Integer bindNo, DevtermInfo devtermInfo,
			TvehicleInfo tvehicleInfo, Date bindDate) {
		this.bindNo = bindNo;
		this.devtermInfo = devtermInfo;
		this.tvehicleInfo = tvehicleInfo;
		this.bindDate = bindDate;
	}

	/** full constructor */
	public TvvBand(Integer bindNo, DevtermInfo devtermInfo,
			TvehicleInfo tvehicleInfo, Date bindDate, Integer userId,
			String bindNote) {
		this.bindNo = bindNo;
		this.devtermInfo = devtermInfo;
		this.tvehicleInfo = tvehicleInfo;
		this.bindDate = bindDate;
		this.userId = userId;
		this.bindNote = bindNote;
	}

	// Property accessors

	public Integer getBindNo() {
		return this.bindNo;
	}

	public void setBindNo(Integer bindNo) {
		this.bindNo = bindNo;
	}

	public DevtermInfo getDevtermInfo() {
		return this.devtermInfo;
	}

	public void setDevtermInfo(DevtermInfo devtermInfo) {
		this.devtermInfo = devtermInfo;
	}

	public TvehicleInfo getTvehicleInfo() {
		return this.tvehicleInfo;
	}

	public void setTvehicleInfo(TvehicleInfo tvehicleInfo) {
		this.tvehicleInfo = tvehicleInfo;
	}

	public Date getBindDate() {
		return this.bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBindNote() {
		return this.bindNote;
	}

	public void setBindNote(String bindNote) {
		this.bindNote = bindNote;
	}

}