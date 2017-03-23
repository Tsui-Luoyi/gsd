package com.jsd.web.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TvehicleInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TvehicleInfoRecord007 implements java.io.Serializable {

	// Fields

	private Integer vid;
	private String vno;
	private String vname;
	private String engineNo;
	private Integer vm_id;
	public TvehicleInfoRecord007(){
		
	}
	
	public TvehicleInfoRecord007(Integer vid) {
		super();
		this.vid = vid;
	}

	public TvehicleInfoRecord007(Integer vid, Integer vm_id) {
		super();
		this.vid = vid;
		this.vm_id = vm_id;
	}

	public Integer getVm_id() {
		return vm_id;
	}
	public void setVm_id(Integer vm_id) {
		this.vm_id = vm_id;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getVno() {
		return vno;
	}
	public void setVno(String vno) {
		this.vno = vno;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	
	
	// Constructors

	
}