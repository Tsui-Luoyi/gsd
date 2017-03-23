package com.jsd.web.po;

/**
 * TorgRelTable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgRelTable implements java.io.Serializable {

	// Fields

	private TorgRelTableId id;

	// Constructors

	/** default constructor */
	public TorgRelTable() {
	}

	/** full constructor */
	public TorgRelTable(TorgRelTableId id) {
		this.id = id;
	}

	// Property accessors

	public TorgRelTableId getId() {
		return this.id;
	}

	public void setId(TorgRelTableId id) {
		this.id = id;
	}

}