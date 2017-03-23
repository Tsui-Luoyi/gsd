package com.jsd.web.po;

/**
 * TrolePrivilege entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrolePrivilege implements java.io.Serializable {

	// Fields

	private TrolePrivilegeId id;
	private Trole trole;
	private Tprivilege tprivilege;

	// Constructors

	/** default constructor */
	public TrolePrivilege() {
	}

	/** minimal constructor */
	public TrolePrivilege(TrolePrivilegeId id) {
		this.id = id;
	}

	/** full constructor */
	public TrolePrivilege(TrolePrivilegeId id, Trole trole,
			Tprivilege tprivilege) {
		this.id = id;
		this.trole = trole;
		this.tprivilege = tprivilege;
	}

	// Property accessors

	public TrolePrivilegeId getId() {
		return this.id;
	}

	public void setId(TrolePrivilegeId id) {
		this.id = id;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public Tprivilege getTprivilege() {
		return this.tprivilege;
	}

	public void setTprivilege(Tprivilege tprivilege) {
		this.tprivilege = tprivilege;
	}

}