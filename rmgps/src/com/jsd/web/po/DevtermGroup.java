package com.jsd.web.po;

import java.util.HashSet;
import java.util.Set;

/**
 * DevtermGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DevtermGroup implements java.io.Serializable {

	// Fields

	private Integer vgId;
	private String vgName;
	private String gnote;
	private Set devtermInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public DevtermGroup() {
	}

	/** minimal constructor */
	public DevtermGroup(Integer vgId) {
		this.vgId = vgId;
	}

	/** full constructor */
	public DevtermGroup(Integer vgId, String vgName, String gnote,
			Set devtermInfos) {
		this.vgId = vgId;
		this.vgName = vgName;
		this.gnote = gnote;
		this.devtermInfos = devtermInfos;
	}

	// Property accessors

	public Integer getVgId() {
		return this.vgId;
	}

	public void setVgId(Integer vgId) {
		this.vgId = vgId;
	}

	public String getVgName() {
		return this.vgName;
	}

	public void setVgName(String vgName) {
		this.vgName = vgName;
	}

	public String getGnote() {
		return this.gnote;
	}

	public void setGnote(String gnote) {
		this.gnote = gnote;
	}

	public Set getDevtermInfos() {
		return this.devtermInfos;
	}

	public void setDevtermInfos(Set devtermInfos) {
		this.devtermInfos = devtermInfos;
	}

}