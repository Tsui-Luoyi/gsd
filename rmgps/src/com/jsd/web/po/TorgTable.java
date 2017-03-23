package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * TorgTable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgTable implements java.io.Serializable {

	// Fields

	private Integer orgId;
	private String orgNo;
	private String orgName;
	private String orgType;
	private Set tusers = new HashSet(0);
	private Set torgRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public TorgTable() {
	}

	/** minimal constructor */
	public TorgTable(Integer orgId) {
		this.orgId = orgId;
	}

	/** full constructor */
	public TorgTable(Integer orgId, String orgNo, String orgName,
			String orgType, Set tusers, Set torgRoles) {
		this.orgId = orgId;
		this.orgNo = orgNo;
		this.orgName = orgName;
		this.orgType = orgType;
		this.tusers = tusers;
		this.torgRoles = torgRoles;
	}

	// Property accessors

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Set getTusers() {
		return this.tusers;
	}

	public void setTusers(Set tusers) {
		this.tusers = tusers;
	}

	public Set getTorgRoles() {
		return this.torgRoles;
	}

	public void setTorgRoles(Set torgRoles) {
		this.torgRoles = torgRoles;
	}

}