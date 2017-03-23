package com.jsd.web.po;

/**
 * UserPrivilege entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class UserPrivilege implements java.io.Serializable {

	// Fields

	private Integer privilegeId;
	private Tuser tuser;
	private String privilegeValue;

	// Constructors

	/** default constructor */
	public UserPrivilege() {
	}

	/** full constructor */
	public UserPrivilege(Integer privilegeId, Tuser tuser, String privilegeValue) {
		this.privilegeId = privilegeId;
		this.tuser = tuser;
		this.privilegeValue = privilegeValue;
	}

	// Property accessors

	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeValue() {
		return privilegeValue;
	}

	public void setPrivilegeValue(String privilegeValue) {
		this.privilegeValue = privilegeValue;
	}

}