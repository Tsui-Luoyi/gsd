package com.jsd.web.vehicle.vo;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings({ "rawtypes", "serial" })
public class AgentVO implements Serializable{
	private String id;
	private String user_id;
	private String agent_name;
	private String user_password;//用户密码
	private String lock_privilege;
	private String privileges;
	private String agent_privilege;
	private String vehicle_privilege;
	private String terminal_privilege;
	private String vid;
	private String user_code;//用户登录帐号
	private String userName;
	private String vno;//整机号
	private String dno;//终端号
	private String vname;//整机名称
	private String level;
	private String parent_id;
	/******************尹志刚;hnzx列表**********************/
	private Integer agent_id;
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}
	/******************尹志刚;hnzx列表**********************/
	private Set agents;
	private String remark;//用户备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
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

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
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
	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAgent_privilege() {
		return agent_privilege;
	}

	public void setAgent_privilege(String agentPrivilege) {
		agent_privilege = agentPrivilege;
	}

	public String getVehicle_privilege() {
		return vehicle_privilege;
	}

	public void setVehicle_privilege(String vehiclePrivilege) {
		vehicle_privilege = vehiclePrivilege;
	}

	public String getTerminal_privilege() {
		return terminal_privilege;
	}

	public void setTerminal_privilege(String terminalPrivilege) {
		terminal_privilege = terminalPrivilege;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public Set getAgents() {
		return agents;
	}

	public void setAgents(Set agents) {
		this.agents = agents;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
