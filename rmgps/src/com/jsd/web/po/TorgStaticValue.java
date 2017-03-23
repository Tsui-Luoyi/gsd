package com.jsd.web.po;

/**
 * TorgStaticValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgStaticValue implements java.io.Serializable {

	// Fields

	private TorgStaticValueId id;
	private String value;

	// Constructors

	/** default constructor */
	public TorgStaticValue() {
	}

	/** minimal constructor */
	public TorgStaticValue(TorgStaticValueId id) {
		this.id = id;
	}

	/** full constructor */
	public TorgStaticValue(TorgStaticValueId id, String value) {
		this.id = id;
		this.value = value;
	}

	// Property accessors

	public TorgStaticValueId getId() {
		return this.id;
	}

	public void setId(TorgStaticValueId id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}