package com.jsd.web.po;

/**
 * TuserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TuserRole implements java.io.Serializable {

	// Fields

	private TuserRoleId id;

	// Constructors

	/** default constructor */
	public TuserRole() {
	}

	/** full constructor */
	public TuserRole(TuserRoleId id) {
		this.id = id;
	}

	// Property accessors

	public TuserRoleId getId() {
		return this.id;
	}

	public void setId(TuserRoleId id) {
		this.id = id;
	}

}