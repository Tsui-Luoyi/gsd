package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * TvehicleModel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TvehicleModel implements java.io.Serializable {

	// Fields

	private Integer vmId;
	private TvehicleClass tvehicleClass;
	private String vmCode;
	private String mname;
	private String vmPicpath;
	private String mnote;
	private Set tvehicleInfos = new HashSet(0);
	private TorgTable torgTable;//型号所属组织

	// Constructors

	/** default constructor */
	public TvehicleModel() {
	}

	/** minimal constructor */
	public TvehicleModel(Integer vmId, String mname) {
		this.vmId = vmId;
		this.mname = mname;
	}

	/** full constructor */
	public TvehicleModel(Integer vmId, TvehicleClass tvehicleClass,
			String vmCode, String mname, String vmPicpath, String mnote,
			Set tvehicleInfos) {
		this.vmId = vmId;
		this.tvehicleClass = tvehicleClass;
		this.vmCode = vmCode;
		this.mname = mname;
		this.vmPicpath = vmPicpath;
		this.mnote = mnote;
		this.tvehicleInfos = tvehicleInfos;
	}

	// Property accessors

	public Integer getVmId() {
		return this.vmId;
	}

	public void setVmId(Integer vmId) {
		this.vmId = vmId;
	}

	public TvehicleClass getTvehicleClass() {
		return this.tvehicleClass;
	}

	public void setTvehicleClass(TvehicleClass tvehicleClass) {
		this.tvehicleClass = tvehicleClass;
	}

	public String getVmCode() {
		return this.vmCode;
	}

	public void setVmCode(String vmCode) {
		this.vmCode = vmCode;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getVmPicpath() {
		return this.vmPicpath;
	}

	public void setVmPicpath(String vmPicpath) {
		this.vmPicpath = vmPicpath;
	}

	public String getMnote() {
		return this.mnote;
	}

	public void setMnote(String mnote) {
		this.mnote = mnote;
	}

	public Set getTvehicleInfos() {
		return this.tvehicleInfos;
	}

	public void setTvehicleInfos(Set tvehicleInfos) {
		this.tvehicleInfos = tvehicleInfos;
	}

	public TorgTable getTorgTable() {
		return torgTable;
	}

	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}
}