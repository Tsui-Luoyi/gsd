package com.jsd.web.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DevtermInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DevtermInfo implements java.io.Serializable {

	// Fields

	private Integer did;
	private DevtermGroup devtermGroup;
	private String dno;
	private String vname;
	private String manufacturer;
	private Integer batchno;
	private Date vdate;
	private String vstate;
	private String note;
	private Set tvvBands = new HashSet(0);
	private String port;
	private String simnumber;
	private String simgateway;

	// Constructors

	/** default constructor */
	public DevtermInfo() {
	}

	/** minimal constructor */
	public DevtermInfo(Integer did) {
		this.did = did;
	}
	public DevtermInfo(String dno,String manufacturer,String simnumber) {
	
		this.dno = dno;
		this.manufacturer = manufacturer;
		this.simnumber = simnumber;
	}
	/** full constructor */
	public DevtermInfo(Integer did, DevtermGroup devtermGroup, String dno,
			String vname, String manufacturer, Integer batchno, Date vdate,
			String vstate, String note, Set tvvBands) {
		this.did = did;
		this.devtermGroup = devtermGroup;
		this.dno = dno;
		this.vname = vname;
		this.manufacturer = manufacturer;
		this.batchno = batchno;
		this.vdate = vdate;
		this.vstate = vstate;
		this.note = note;
		this.tvvBands = tvvBands;
	}

	// Property accessors

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public DevtermGroup getDevtermGroup() {
		return this.devtermGroup;
	}

	public void setDevtermGroup(DevtermGroup devtermGroup) {
		this.devtermGroup = devtermGroup;
	}

	public String getDno() {
		return this.dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getBatchno() {
		return this.batchno;
	}

	public void setBatchno(Integer batchno) {
		this.batchno = batchno;
	}

	public Date getVdate() {
		return this.vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public String getVstate() {
		return this.vstate;
	}

	public void setVstate(String vstate) {
		this.vstate = vstate;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getTvvBands() {
		return this.tvvBands;
	}

	public void setTvvBands(Set tvvBands) {
		this.tvvBands = tvvBands;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSimnumber() {
		return simnumber;
	}

	public void setSimnumber(String simnumber) {
		this.simnumber = simnumber;
	}

	public String getSimgateway() {
		return simgateway;
	}

	public void setSimgateway(String simgateway) {
		this.simgateway = simgateway;
	}

}