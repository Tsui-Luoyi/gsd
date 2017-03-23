package com.jsd.web.po;

/**
 * TorgRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgRole implements java.io.Serializable {

	// Fields

	private TorgRoleId id;
	private TorgTable torgTable;
	private Trole trole;

	// Constructors

	/** default constructor */
	public TorgRole() {
	}

	/** minimal constructor */
	public TorgRole(TorgRoleId id) {
		this.id = id;
	}

	/** full constructor */
	public TorgRole(TorgRoleId id, TorgTable torgTable, Trole trole) {
		this.id = id;
		this.torgTable = torgTable;
		this.trole = trole;
	}

	// Property accessors

	public TorgRoleId getId() {
		return this.id;
	}

	public void setId(TorgRoleId id) {
		this.id = id;
	}

	public TorgTable getTorgTable() {
		return this.torgTable;
	}

	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

}