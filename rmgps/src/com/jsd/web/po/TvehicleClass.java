package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * TvehicleClass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TvehicleClass implements java.io.Serializable {

	// Fields

	private Integer vcId;
	private Integer parentId;
	private String vcCode;
	private String vcName;
	private String vcNote;
	private String vcPicpath;
	private Set tvehicleModels = new HashSet(0);

	// Constructors

	/** default constructor */
	public TvehicleClass() {
	}

	/** minimal constructor */
	public TvehicleClass(Integer vcId, String vcName) {
		this.vcId = vcId;
		this.vcName = vcName;
	}

	/** full constructor */
	public TvehicleClass(Integer vcId, Integer parentId, String vcCode,
			String vcName, String vcNote, String vcPicpath, Set tvehicleModels) {
		this.vcId = vcId;
		this.parentId = parentId;
		this.vcCode = vcCode;
		this.vcName = vcName;
		this.vcNote = vcNote;
		this.vcPicpath = vcPicpath;
		this.tvehicleModels = tvehicleModels;
	}

	// Property accessors

	public Integer getVcId() {
		return this.vcId;
	}

	public void setVcId(Integer vcId) {
		this.vcId = vcId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getVcCode() {
		return this.vcCode;
	}

	public void setVcCode(String vcCode) {
		this.vcCode = vcCode;
	}

	public String getVcName() {
		return this.vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public String getVcNote() {
		return this.vcNote;
	}

	public void setVcNote(String vcNote) {
		this.vcNote = vcNote;
	}

	public String getVcPicpath() {
		return this.vcPicpath;
	}

	public void setVcPicpath(String vcPicpath) {
		this.vcPicpath = vcPicpath;
	}

	public Set getTvehicleModels() {
		return this.tvehicleModels;
	}

	public void setTvehicleModels(Set tvehicleModels) {
		this.tvehicleModels = tvehicleModels;
	}

}