package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Tuser entity.
 * 添加了标注remarkerVo
 * @author MyEclipse Persistence Tools
 */

public class Tuser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private TorgTable torgTable;
	private String userCode;
	private String userName;
	private String userSex;
	private String userType;
	private String userStatus;
	private String userPassword;
	private String userFlag; //标记位
	private Set userPrivileges = new HashSet(0);
	private Set tuserRoles = new HashSet(0);
	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public Tuser(Integer userId, TorgTable torgTable, String userCode,
			String userName, String userSex, String userType,
			String userStatus, String userPassword, Set userPrivileges,
			Set tuserRoles,Set remarkers) {
		this.userId = userId;
		this.torgTable = torgTable;
		this.userCode = userCode;
		this.userName = userName;
		this.userSex = userSex;
		this.userType = userType;
		this.userStatus = userStatus;
		this.userPassword = userPassword;
		this.userPrivileges = userPrivileges;
		this.tuserRoles = tuserRoles;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public TorgTable getTorgTable() {
		return this.torgTable;
	}

	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set getUserPrivileges() {
		return this.userPrivileges;
	}

	public void setUserPrivileges(Set userPrivileges) {
		this.userPrivileges = userPrivileges;
	}

	public Set getTuserRoles() {
		return this.tuserRoles;
	}

	public void setTuserRoles(Set tuserRoles) {
		this.tuserRoles = tuserRoles;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}
	
}