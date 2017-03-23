package com.jsd.web.po;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Tuser entity.
 * 用于007手机登陆
 * 
 */
@XmlRootElement
public class Tagent implements java.io.Serializable {

	// Fields

	private int id;
	private int user_id;
	private String agent_name;
	private String lock_privilege;
	private String vid;
	private int level;
	private String parent_id;
	private String remark;
	// Constructors
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getLock_privilege() {
		return lock_privilege;
	}
	public void setLock_privilege(String lock_privilege) {
		this.lock_privilege = lock_privilege;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	
	
}