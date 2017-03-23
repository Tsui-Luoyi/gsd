package com.jsd.web.po;

import java.util.Date;

/**
 * TbrowseVehicleLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbrowseVehicleLog implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer userId;
	private String vno;
	private Date browseDate;

	// Constructors

	/** default constructor */
	public TbrowseVehicleLog() {
	}

	/** minimal constructor */
	public TbrowseVehicleLog(Integer userId, String vno) {
		this.userId = userId;
		this.vno = vno;
	}

	/** full constructor */
	public TbrowseVehicleLog(Integer userId, String vno, Date browseDate) {
		this.userId = userId;
		this.vno = vno;
		this.browseDate = browseDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVno() {
		return this.vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public Date getBrowseDate() {
		return this.browseDate;
	}

	public void setBrowseDate(Date browseDate) {
		this.browseDate = browseDate;
	}

}