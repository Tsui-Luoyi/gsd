package com.jsd.web.po;

/**
 * DictGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DictGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;

	// Constructors

	/** default constructor */
	public DictGroup() {
	}

	/** full constructor */
	public DictGroup(String name) {
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}