package com.jsd.web.po;

import java.util.Date;

/**
 * GpsLocateCurrent entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class GpsLocateCurrent implements java.io.Serializable {

	// Fields

	private GpsLocateCurrentId id;
	private String dno;
	private String msgUuid;
	private Date revtime;
	private Date gpsTime;
	private String locateFlag;
	private String longitude;
	private String latitude;
	private String o_lng; //真实经度
	private String o_lat; //真实纬度
	private String nsflag;
	private String ewflag;
	private String groundSpeed;
	private String course;
	private String satellitenum;
	private String asl;
	private String gsmStrong;
	private String addr;

	//添加百度纠偏经纬度
	private String b_Longitude;
	private String b_Latitude;
	// Constructors

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	/** default constructor */
	public GpsLocateCurrent() {
	}

	/** minimal constructor */
	public GpsLocateCurrent(GpsLocateCurrentId id, String dno, String msgUuid,
			Date revtime) {
		this.id = id;
		this.dno = dno;
		this.msgUuid = msgUuid;
		this.revtime = revtime;
	}

	/** full constructor */
	public GpsLocateCurrent(GpsLocateCurrentId id, String dno, String msgUuid,
			Date revtime, Date gpsTime, String locateFlag, String longitude,
			String latitude, String nsflag, String ewflag, String groundSpeed,
			String course, String satellitenum, String asl, String gsmStrong) {
		this.id = id;
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

	public GpsLocateCurrentId getId() {
		return this.id;
	}

	public String getB_Longitude() {
		return b_Longitude;
	}

	public void setB_Longitude(String b_Longitude) {
		this.b_Longitude = b_Longitude;
	}

	public String getB_Latitude() {
		return b_Latitude;
	}

	public void setB_Latitude(String b_Latitude) {
		this.b_Latitude = b_Latitude;
	}

	public void setId(GpsLocateCurrentId id) {
		this.id = id;
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

	public String getO_lng() {
		return o_lng;
	}

	public void setO_lng(String o_lng) {
		this.o_lng = o_lng;
	}

	public String getO_lat() {
		return o_lat;
	}

	public void setO_lat(String o_lat) {
		this.o_lat = o_lat;
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