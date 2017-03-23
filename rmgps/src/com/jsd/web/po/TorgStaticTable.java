package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * TorgStaticTable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgStaticTable implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String code;
	private String descr;
	private Set torgStaticValues = new HashSet(0);

	// Constructors

	/** default constructor */
	public TorgStaticTable() {
	}

	/** minimal constructor */
	public TorgStaticTable(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TorgStaticTable(Integer id, String type, String code, String descr,
			Set torgStaticValues) {
		this.id = id;
		this.type = type;
		this.code = code;
		this.descr = descr;
		this.torgStaticValues = torgStaticValues;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Set getTorgStaticValues() {
		return this.torgStaticValues;
	}

	public void setTorgStaticValues(Set torgStaticValues) {
		this.torgStaticValues = torgStaticValues;
	}

}