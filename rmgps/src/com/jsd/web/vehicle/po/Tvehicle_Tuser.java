package com.jsd.web.vehicle.po;

import java.io.Serializable;

/**
 * 车辆用户类
 * @author Louie
 *
 */
@SuppressWarnings("serial")
public class Tvehicle_Tuser implements Serializable{

	private Integer id;
	private Integer vid;
	private String vno;
	private String vname;
	private Integer org_no;
	private Integer agent_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getOrg_no() {
		return org_no;
	}
	public void setOrg_no(Integer org_no) {
		this.org_no = org_no;
	}
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}
	
}
