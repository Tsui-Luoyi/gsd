package com.jsd.web.vehicle.vo;

import com.jsd.web.po.Tuser;
/**
 * 地图标注bean
 * @author Jnhuy
 *
 */
public class RemarkerVO {
	private Integer id;
	private String remarkerId;
	private String remarkerLat;
	private String remarkerLng;
	private String remarkerInfo;
	private String remarkerTitle;
	private Tuser tuser;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRemarkerId() {
		return remarkerId;
	}
	public void setRemarkerId(String remarkerId) {
		this.remarkerId = remarkerId;
	}
	public Tuser getTuser() {
		return tuser;
	}
	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}
	public String getRemarkerLat() {
		return remarkerLat;
	}
	public void setRemarkerLat(String remarkerLat) {
		this.remarkerLat = remarkerLat;
	}
	public String getRemarkerLng() {
		return remarkerLng;
	}
	public void setRemarkerLng(String remarkerLng) {
		this.remarkerLng = remarkerLng;
	}
	public String getRemarkerInfo() {
		return remarkerInfo;
	}
	public void setRemarkerInfo(String remarkerInfo) {
		this.remarkerInfo = remarkerInfo;
	}
	public String getRemarkerTitle() {
		return remarkerTitle;
	}
	public void setRemarkerTitle(String remarkerTitle) {
		this.remarkerTitle = remarkerTitle;
	}
	
	
}
