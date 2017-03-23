package com.jsd.web.po;

/**
 * VehicleInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VehicleInfo implements java.io.Serializable {

	// Fields

	private Integer vid;
	private String vno;
	private String vname;
	private String vc;
	private String vm;

	// Constructors

	/** default constructor */
	public VehicleInfo() {
	}

	/** minimal constructor */
	public VehicleInfo(Integer vid, String vno, String vc, String vm) {
		this.vid = vid;
		this.vno = vno;
		this.vc = vc;
		this.vm = vm;
	}

	/** full constructor */
	public VehicleInfo(Integer vid, String vno, String vname, String vc,
			String vm) {
		this.vid = vid;
		this.vno = vno;
		this.vname = vname;
		this.vc = vc;
		this.vm = vm;
	}

	// Property accessors

	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
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

	public String getVc() {
		return this.vc;
	}

	public void setVc(String vc) {
		this.vc = vc;
	}

	public String getVm() {
		return this.vm;
	}

	public void setVm(String vm) {
		this.vm = vm;
	}

}