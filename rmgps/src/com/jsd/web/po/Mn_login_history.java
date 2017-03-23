package com.jsd.web.po;

import java.util.Date;

/**
 * GpsLocateHistory entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Mn_login_history implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String vno;
	private Date login_time;
	private String locate_flag;
	private String o_lng;
	private String o_lat;
	private String longitude;
	private String latitude;
	private String gsm_strong;
	private String lac;
	private String cellid;
	private String addr;
	/**
	 *
	 * 添加百度经纬度
	 */
	private String b_Longitude;
	private String b_Latitude;


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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVno() {
		return vno;
	}
	public void setVno(String vno) {
		this.vno = vno;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public String getLocate_flag() {
		return locate_flag;
	}
	public void setLocate_flag(String locate_flag) {
		this.locate_flag = locate_flag;
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getGsm_strong() {
		return gsm_strong;
	}
	public void setGsm_strong(String gsm_strong) {
		this.gsm_strong = gsm_strong;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getCellid() {
		return cellid;
	}
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}




}