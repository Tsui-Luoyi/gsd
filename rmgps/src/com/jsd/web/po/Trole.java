package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Trole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Trole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleType;
	private Set trolePrivileges = new HashSet(0);
	private Set troleVbizstates = new HashSet(0);
	private Set torgRoles = new HashSet(0);
	private Set tuserRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Trole() {
	}

	/** minimal constructor */
	public Trole(Integer roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public Trole(Integer roleId, String roleName, String roleType,
			Set trolePrivileges, Set troleVbizstates, Set torgRoles,
			Set tuserRoles) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleType = roleType;
		this.trolePrivileges = trolePrivileges;
		this.troleVbizstates = troleVbizstates;
		this.torgRoles = torgRoles;
		this.tuserRoles = tuserRoles;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set getTrolePrivileges() {
		return this.trolePrivileges;
	}

	public void setTrolePrivileges(Set trolePrivileges) {
		this.trolePrivileges = trolePrivileges;
	}

	public Set getTroleVbizstates() {
		return this.troleVbizstates;
	}

	public void setTroleVbizstates(Set troleVbizstates) {
		this.troleVbizstates = troleVbizstates;
	}

	public Set getTorgRoles() {
		return this.torgRoles;
	}

	public void setTorgRoles(Set torgRoles) {
		this.torgRoles = torgRoles;
	}

	public Set getTuserRoles() {
		return this.tuserRoles;
	}

	public void setTuserRoles(Set tuserRoles) {
		this.tuserRoles = tuserRoles;
	}

}