package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Tprivilege entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tprivilege implements java.io.Serializable {

	// Fields

	private Integer privilegeId;
	private String privilegeName;
	private Set trolePrivileges = new HashSet(0);
	private Set userPrivileges = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tprivilege() {
	}

	/** minimal constructor */
	public Tprivilege(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	/** full constructor */
	public Tprivilege(Integer privilegeId, String privilegeName,
			Set trolePrivileges, Set userPrivileges) {
		this.privilegeId = privilegeId;
		this.privilegeName = privilegeName;
		this.trolePrivileges = trolePrivileges;
		this.userPrivileges = userPrivileges;
	}

	// Property accessors

	public Integer getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return this.privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Set getTrolePrivileges() {
		return this.trolePrivileges;
	}

	public void setTrolePrivileges(Set trolePrivileges) {
		this.trolePrivileges = trolePrivileges;
	}

	public Set getUserPrivileges() {
		return this.userPrivileges;
	}

	public void setUserPrivileges(Set userPrivileges) {
		this.userPrivileges = userPrivileges;
	}

}