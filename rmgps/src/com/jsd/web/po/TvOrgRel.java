package com.jsd.web.po;

/**
 * TvOrgRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TvOrgRel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer orgId;
	private String vno;

	// Constructors

	/** default constructor */
	public TvOrgRel() {
	}

	/** minimal constructor */
	public TvOrgRel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TvOrgRel(Integer id, Integer orgId, String vno) {
		this.id = id;
		this.orgId = orgId;
		this.vno = vno;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getVno() {
		return this.vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

}