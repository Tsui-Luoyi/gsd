package com.jsd.web.po;

import java.util.Date;

/**
 * TsysBulletin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TsysBulletin implements java.io.Serializable {

	// Fields

	private Integer bulletinId;
	private String bulletinTitle;
	private String 公告内容;
	private Date 发布时间;
	private Integer 发布人;

	// Constructors

	/** default constructor */
	public TsysBulletin() {
	}

	/** minimal constructor */
	public TsysBulletin(Integer bulletinId) {
		this.bulletinId = bulletinId;
	}

	/** full constructor */
	public TsysBulletin(Integer bulletinId, String bulletinTitle, String 公告内容,
			Date 发布时间, Integer 发布人) {
		this.bulletinId = bulletinId;
		this.bulletinTitle = bulletinTitle;
		this.公告内容 = 公告内容;
		this.发布时间 = 发布时间;
		this.发布人 = 发布人;
	}

	// Property accessors

	public Integer getBulletinId() {
		return this.bulletinId;
	}

	public void setBulletinId(Integer bulletinId) {
		this.bulletinId = bulletinId;
	}

	public String getBulletinTitle() {
		return this.bulletinTitle;
	}

	public void setBulletinTitle(String bulletinTitle) {
		this.bulletinTitle = bulletinTitle;
	}

	public String get公告内容() {
		return this.公告内容;
	}

	public void set公告内容(String 公告内容) {
		this.公告内容 = 公告内容;
	}

	public Date get发布时间() {
		return this.发布时间;
	}

	public void set发布时间(Date 发布时间) {
		this.发布时间 = 发布时间;
	}

	public Integer get发布人() {
		return this.发布人;
	}

	public void set发布人(Integer 发布人) {
		this.发布人 = 发布人;
	}

}