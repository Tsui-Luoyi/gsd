package com.jsd.web.vehicle.vo;

public class CmdSendParamVO {

	private String vno;
	private String cmd;
	private String sendType;
	private String userId;
	private String ip;
	private String cmdDetail;

	private String ipaddress; //设置ip地址                  
	private String ipport; //设置端口号                  
	private String time1; //设置忙时上传信息的时间间隔  
	private String time2; //设置闲时上传信息的时间间隔  
	private String clockstate; //设置锁车状态     
	private String level;//设置锁车状态的级别
	private String password; //设置锁车密码                
	private String smsflag; //设置短信功能                
	private String clock; //校时命令                    
	private String vehicleid; //设置整车信息                
	private String gpsstate; //设置GPS监控状态             
	private String workinghours; //设置锁车后继续工作小时数    
	private String llongitude; //左上角经度                  
	private String llatitude; //左上角纬度                  
	private String rlongitude; //右下角经度                  
	private String rlatitude; //右下角纬度                  
	private String type; //cirlce(设圆形置限制区域)    
	private String hour; //设置工作小时计              
	private String dianya; //设置启动门限电压            
	private String simnumber; //设置短信网关SIM卡号码  
	private String circle_longitude;
	private String circle_latitude;
	private String circle_radius;
	private String wakeup;//终端唤醒后使用的传输方式，目前固定为1-GPRS方式
	private String gpsclockstate;//是否天线锁定
	private String heartRate;//实时心跳信息
	private String rcmtime;
	private String stationInfo;//基站信息
	
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}


	public String getStationInfo() {
		return stationInfo;
	}

	public void setStationInfo(String stationInfo) {
		this.stationInfo = stationInfo;
	}

	public String getGpsclockstate() {
		return gpsclockstate;
	}

	public void setGpsclockstate(String gpsclockstate) {
		this.gpsclockstate = gpsclockstate;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getIpport() {
		return ipport;
	}

	public void setIpport(String ipport) {
		this.ipport = ipport;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getClockstate() {
		return clockstate;
	}

	public void setClockstate(String clockstate) {
		this.clockstate = clockstate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmsflag() {
		return smsflag;
	}

	public void setSmsflag(String smsflag) {
		this.smsflag = smsflag;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getGpsstate() {
		return gpsstate;
	}

	public void setGpsstate(String gpsstate) {
		this.gpsstate = gpsstate;
	}

	public String getWorkinghours() {
		return workinghours;
	}

	public void setWorkinghours(String workinghours) {
		this.workinghours = workinghours;
	}

	public String getLlongitude() {
		return llongitude;
	}

	public void setLlongitude(String llongitude) {
		this.llongitude = llongitude;
	}

	public String getLlatitude() {
		return llatitude;
	}

	public void setLlatitude(String llatitude) {
		this.llatitude = llatitude;
	}

	public String getRlongitude() {
		return rlongitude;
	}

	public void setRlongitude(String rlongitude) {
		this.rlongitude = rlongitude;
	}

	public String getRlatitude() {
		return rlatitude;
	}

	public void setRlatitude(String rlatitude) {
		this.rlatitude = rlatitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDianya() {
		return dianya;
	}

	public void setDianya(String dianya) {
		this.dianya = dianya;
	}

	public String getSimnumber() {
		return simnumber;
	}

	public void setSimnumber(String simnumber) {
		this.simnumber = simnumber;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCircle_longitude() {
		return circle_longitude;
	}

	public void setCircle_longitude(String circle_longitude) {
		this.circle_longitude = circle_longitude;
	}

	public String getCircle_latitude() {
		return circle_latitude;
	}

	public void setCircle_latitude(String circle_latitude) {
		this.circle_latitude = circle_latitude;
	}

	public String getCircle_radius() {
		return circle_radius;
	}

	public void setCircle_radius(String circle_radius) {
		this.circle_radius = circle_radius;
	}

	public String getWakeup() {
		return wakeup;
	}

	public void setWakeup(String wakeup) {
		this.wakeup = wakeup;
	}
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCmdDetail() {
		return cmdDetail;
	}

	public void setCmdDetail(String cmdDetail) {
		this.cmdDetail = cmdDetail;
	}

	public String getRcmtime() {
		return rcmtime;
	}

	public void setRcmtime(String rcmtime) {
		this.rcmtime = rcmtime;
	}

}
