package com.jsd.web.vehicle.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VehicleMapInfoVO implements Serializable {

	private String vno;
	private String longitude;
	private String latitude;
	private String vmid;
	private String vname;
	@SuppressWarnings("unused")
	private String vm_id;
	/**
	 * 整机地图添加百度经纬度
	 * @return
	 */
	private String b_Longitude;
	private String b_Latitude;
	private String addr;



	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getVm_id() {
		return vmid;
	}

	public void setVm_id(String vmid) {
		this.vmid = vmid;
		this.vm_id = vmid;
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
		this.vm_id = vmid;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
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

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}
}
