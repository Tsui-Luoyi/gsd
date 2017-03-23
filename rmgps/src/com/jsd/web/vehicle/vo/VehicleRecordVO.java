package com.jsd.web.vehicle.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VehicleRecordVO implements Serializable {
	private String orgName;// 整机生产厂商TorgTable
	private String mname;// 整机型号TvehicleModel
	private String vno;// 整机编号TvehicleInfo
	private String Tvname;// 整机别名TvehicleInfo
	private String engineNo;// 发动机号TvehicleInfo
	private String dno;// 终端编号DevtermInfo
	private String Dvname;// 终端名称DevtermInfo
	private String manufacturer;// 终端生产厂商DevtermInfo
	private String simnumber;// 手机卡号DevtermInfo
	// 车辆终端绑定记录 某时间某公司某人..进行绑定------某公司TorgTable->orgName
	private String orgName2;// 绑定人公司名称TorgTable
	private String bindDate;// 绑定时间TvvBand
	private String userName;// 绑定人Tuser
	private String sellrd;//销售记录

	private String agentName;//代理商名字
	private String vid;//整机vid
	private String seller;//销售者
	private String selltime;//销售时间
	private String buyer;//购买者
	private String sellrecord;//销售综合信息--即销售者+销售时间+购买者
	private String sellerphone;//销售人员电话
	private String phone;//联系电话
	private String addr;//联系地址
	private String vehiclemodel;//车型
	private String buymodel;//购买方式
	private String buymodelnote;//购买方式备注
	private String refundtime;//还款期限
	private String note;//备注

	private String renewdate;//终端最后续费时间
	private String renewperiod;//终端续费周期

	public String getSellrd() {
		return sellrd;
	}

	public void setSellrd(String sellrd) {
		this.sellrd = sellrd;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getTvname() {
		return Tvname;
	}

	public void setTvname(String tvname) {
		Tvname = tvname;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getDvname() {
		return Dvname;
	}

	public void setDvname(String dvname) {
		Dvname = dvname;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSimnumber() {
		return simnumber;
	}

	public void setSimnumber(String simnumber) {
		this.simnumber = simnumber;
	}

	public String getBindDate() {
		return bindDate;
	}

	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgName2() {
		return orgName2;
	}

	public void setOrgName2(String orgName2) {
		this.orgName2 = orgName2;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSelltime() {
		return selltime;
	}

	public void setSelltime(String selltime) {
		this.selltime = selltime;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSellrecord() {
		return sellrecord;
	}

	public void setSellrecord(String sellrecord) {
		this.sellrecord = sellrecord;
	}

	public String getRenewdate() {
		return renewdate;
	}

	public void setRenewdate(String renewdate) {
		this.renewdate = renewdate;
	}

	public String getRenewperiod() {
		return renewperiod;
	}

	public void setRenewperiod(String renewperiod) {
		this.renewperiod = renewperiod;
	}

	public String getSellerphone() {
		return sellerphone;
	}

	public void setSellerphone(String sellerphone) {
		this.sellerphone = sellerphone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getVehiclemodel() {
		return vehiclemodel;
	}

	public void setVehiclemodel(String vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}

	public String getBuymodel() {
		return buymodel;
	}

	public void setBuymodel(String buymodel) {
		this.buymodel = buymodel;
	}

	public String getBuymodelnote() {
		return buymodelnote;
	}

	public void setBuymodelnote(String buymodelnote) {
		this.buymodelnote = buymodelnote;
	}

	public String getRefundtime() {
		return refundtime;
	}

	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
