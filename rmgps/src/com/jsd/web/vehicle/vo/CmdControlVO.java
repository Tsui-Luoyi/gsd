package com.jsd.web.vehicle.vo;

public class CmdControlVO {

	private String vno;
	private String userId;
	private String cmdId;
	private String orderSendType;
	private String gatherParam;
	public String getCmdId() {
		return cmdId;
	}
	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}
	public String getOrderSendType() {
		return orderSendType;
	}
	public void setOrderSendType(String orderSendType) {
		this.orderSendType = orderSendType;
	}
	public String getVno() {
		return vno;
	}
	public void setVno(String vno) {
		this.vno = vno;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGatherParam() {
		return gatherParam;
	}
	public void setGatherParam(String gatherParam) {
		this.gatherParam = gatherParam;
	}
	
}
