package com.jsd.web.vehicle.po;

import java.io.Serializable;

/**
 * 代理商用户类
 * @author Louie
 *
 */
@SuppressWarnings("serial")
public class Tagent_Tuser implements Serializable{
	
	private Integer id;
	public Integer agent_id;
	private Integer user_id;
	private String agent_name;
	private Integer org_no;
	private String parent_id;
	private Integer level;
	private Integer zindex;
	private String remark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public Integer getOrg_no() {
		return org_no;
	}
	public void setOrg_no(Integer org_no) {
		this.org_no = org_no;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}