package com.jsd.web.po;

/**
 * TroleVbizstate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TroleVbizstate implements java.io.Serializable {

	// Fields

	private TroleVbizstateId id;
	private Trole trole;

	// Constructors

	/** default constructor */
	public TroleVbizstate() {
	}

	/** minimal constructor */
	public TroleVbizstate(TroleVbizstateId id) {
		this.id = id;
	}

	/** full constructor */
	public TroleVbizstate(TroleVbizstateId id, Trole trole) {
		this.id = id;
		this.trole = trole;
	}

	// Property accessors

	public TroleVbizstateId getId() {
		return this.id;
	}

	public void setId(TroleVbizstateId id) {
		this.id = id;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

}