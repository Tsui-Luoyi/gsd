package com.jsd.web.po;

/**
 * DictDefine entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DictDefine implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer defId;
	private String name;
	private Integer groupId;

	// Constructors

	/** default constructor */
	public DictDefine() {
	}

	/** full constructor */
	public DictDefine(Integer defId, String name, Integer groupId) {
		this.defId = defId;
		this.name = name;
		this.groupId = groupId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDefId() {
		return this.defId;
	}

	public void setDefId(Integer defId) {
		this.defId = defId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}