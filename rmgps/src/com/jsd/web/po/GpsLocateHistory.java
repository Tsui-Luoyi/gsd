package com.jsd.web.po;

import java.util.Date;

/**
 * GpsLocateHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class GpsLocateHistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vno;
	private String dno;
	private String msgUuid;
	private Date revtime;
	private Date gpsTime;
	private String locateFlag;
	private String longitude;
	private String latitude;
	private String nsflag;
	private String ewflag;
	private String groundSpeed;
	private String course;
	private String satellitenum;
	private String asl;
	private String gsmStrong;
	private String addr;
	
	// Constructors

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	/** default constructor */
	public GpsLocateHistory() {
	}

	/** minimal constructor */
	public GpsLocateHistory(String vno, String dno, String msgUuid, Date revtime) {
		this.vno = vno;
		this.dno = dno;
		this.msgUuid = msgUuid;
		this.revtime = revtime;
	}

	/** full constructor */
	public GpsLocateHistory(String vno, String dno, String msgUuid,
			Date revtime, Date gpsTime, String locateFlag, String longitude,
			String latitude, String nsflag, String ewflag, String groundSpeed,
			String course, String satellitenum, String asl, String gsmStrong) {
		this.vno = vno;
		this.dno = dno;
		this.msgUuid = msgUuid;
		this.revtime = revtime;
		this.gpsTime = gpsTime;
		this.locateFlag = locateFlag;
		this.longitude = longitude;
		this.latitude = latitude;
		this.nsflag = nsflag;
		this.ewflag = ewflag;
		this.groundSpeed = groundSpeed;
		this.course = course;
		this.satellitenum = satellitenum;
		this.asl = asl;
		this.gsmStrong = gsmStrong;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVno() {
		return this.vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getDno() {
		return this.dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getMsgUuid() {
		return this.msgUuid;
	}

	public void setMsgUuid(String msgUuid) {
		this.msgUuid = msgUuid;
	}

	public Date getRevtime() {
		return this.revtime;
	}

	public void setRevtime(Date revtime) {
		this.revtime = revtime;
	}

	public Date getGpsTime() {
		return this.gpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}

	public String getLocateFlag() {
		return this.locateFlag;
	}

	public void setLocateFlag(String locateFlag) {
		this.locateFlag = locateFlag;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getNsflag() {
		return this.nsflag;
	}

	public void setNsflag(String nsflag) {
		this.nsflag = nsflag;
	}

	public String getEwflag() {
		return this.ewflag;
	}

	public void setEwflag(String ewflag) {
		this.ewflag = ewflag;
	}

	public String getGroundSpeed() {
		return this.groundSpeed;
	}

	public void setGroundSpeed(String groundSpeed) {
		this.groundSpeed = groundSpeed;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSatellitenum() {
		return this.satellitenum;
	}

	public void setSatellitenum(String satellitenum) {
		this.satellitenum = satellitenum;
	}

	public String getAsl() {
		return this.asl;
	}

	public void setAsl(String asl) {
		this.asl = asl;
	}

	public String getGsmStrong() {
		return this.gsmStrong;
	}

	public void setGsmStrong(String gsmStrong) {
		this.gsmStrong = gsmStrong;
	}

}