package com.jsd.web.login.vo;

import java.util.HashSet;
import java.util.Set;

import com.jsd.web.po.TorgTable;

@SuppressWarnings("rawtypes")
public class UserVO {

	private Integer userId;
	private TorgTable torgTable;
	private String userCode;
	private String userPassword;
	private String orgName;
	private String userName;
	private String userSex;
	private String userType;
	private String userStatus;
	private Set tuserRoles = new HashSet(0);
	private String roleName;
	private int loginCount;
	private String loginIP;
	private String lastLoginDate;
	private String copyNum;
	private String lockprivilege;
	private Integer level;
	private String parent_id;
	private Set privileges = new HashSet(0);
	private String privilegeValue;
	private String agentprivilege;//代理商管理权限
	private String vehicleprivilege;//车辆管理权限
	private String terminalprivilege;//终端续费权限
	private String logoPath;//图标路径
	private String remark;

	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public TorgTable getTorgTable() {
		return torgTable;
	}
	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Set getTuserRoles() {
		return tuserRoles;
	}
	public void setTuserRoles(Set tuserRoles) {
		this.tuserRoles = tuserRoles;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(String copyNum) {
		this.copyNum = copyNum;
	}
	public String getLockprivilege() {
		return lockprivilege;
	}
	public void setLockprivilege(String lockprivilege) {
		this.lockprivilege = lockprivilege;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Set getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set privileges) {
		this.privileges = privileges;
	}
	public String getPrivilegeValue() {
		return privilegeValue;
	}
	public void setPrivilegeValue(String privilegeValue) {
		this.privilegeValue = privilegeValue;
	}
	public String getAgentprivilege() {
		return agentprivilege;
	}
	public void setAgentprivilege(String agentprivilege) {
		this.agentprivilege = agentprivilege;
	}
	public String getVehicleprivilege() {
		return vehicleprivilege;
	}
	public void setVehicleprivilege(String vehicleprivilege) {
		this.vehicleprivilege = vehicleprivilege;
	}
	public String getTerminalprivilege() {
		return terminalprivilege;
	}
	public void setTerminalprivilege(String terminalprivilege) {
		this.terminalprivilege = terminalprivilege;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
