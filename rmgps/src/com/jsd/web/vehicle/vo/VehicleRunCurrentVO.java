package com.jsd.web.vehicle.vo;

import java.util.Date;

public class VehicleRunCurrentVO {

	private Integer id;
	private String vno;
	private String dno;
	private String msgUuid;
	private Date revtime;
	private String locateFlag;
	private Date gpsTime;
	private String mchBv;
	private String engHours;
	private String engCoolantT;
	private String hydraOilT;
	private String hydraOilP;
	private String fuelLevel;
	private String engRpm;
	private String rcmBv;
	private String mchTime;
	private String rcmTime;
	private String sourceType;
	private String ipaddress;
	private String ipport;
	private String time1;
	private String time2;
	private String limitType;
	private String llongitude;
	private String llatitude;
	private String rlongitude;
	private String rlatitude;
	private String circleLongitude;
	private String circleLatitude;
	private String circleRadius;
	private String lockState;
	private String gpsState;
	private String menxianDianya;
	private String neiDianya;
	private String waiDianya;
	private String limitAlarmType;
	private String currentclockvehicle;
	private String prepareclockvehicle;
	private String longitude;
	private String latitude;
	private String workmode;//工作模式
	private String runmode;//运行模式
	private String accelerograph;//油门档位

	public String getCurrentclockvehicle() {
		return currentclockvehicle;
	}
	public void setCurrentclockvehicle(String currentclockvehicle) {
		if(currentclockvehicle==null){
			this.currentclockvehicle = "";
		}else{
			this.currentclockvehicle = currentclockvehicle;
		}
	}
	public String getPrepareclockvehicle() {
		return prepareclockvehicle;
	}
	public void setPrepareclockvehicle(String prepareclockvehicle) {
		if(prepareclockvehicle==null){
			this.prepareclockvehicle = "";
		}else{
			this.prepareclockvehicle = prepareclockvehicle;
		}
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
		if(vno==null){
			this.vno = "";
		}else{
			this.vno = vno;
		}
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		if(dno==null){
			this.dno = "";
		}else{
			this.dno = dno;
		}
	}
	public String getMsgUuid() {
		return msgUuid;
	}
	public void setMsgUuid(String msgUuid) {
		if(msgUuid==null){
			this.msgUuid = "";
		}else{
			this.msgUuid = msgUuid;
		}
	}
	public Date getRevtime() {
		return revtime;
	}
	public void setRevtime(Date revtime) {
		this.revtime = revtime;
	}
	public String getLocateFlag() {
		return locateFlag;
	}
	public void setLocateFlag(String locateFlag) {
		if(locateFlag==null){
			this.locateFlag = "";
		}else{
			this.locateFlag = locateFlag;
		}
	}
	public Date getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}
	public String getMchBv() {
		return mchBv;
	}
	public void setMchBv(String mchBv) {
		if(mchBv==null){
			this.mchBv = "";
		}else{
			this.mchBv = mchBv;
		}
	}
	public String getEngHours() {
		return engHours;
	}
	public void setEngHours(String engHours) {
		if(engHours==null){
			this.engHours = "";
		}else{
			this.engHours = engHours;
		}
	}
	public String getEngCoolantT() {
		return engCoolantT;
	}
	public void setEngCoolantT(String engCoolantT) {
		if(engCoolantT==null){
			this.engCoolantT = "";
		}else{
			this.engCoolantT = engCoolantT;
		}
	}
	public String getHydraOilT() {
		return hydraOilT;
	}
	public void setHydraOilT(String hydraOilT) {
		if(hydraOilT==null){
			this.hydraOilT = "";
		}else{
			this.hydraOilT = hydraOilT;
		}
	}
	public String getHydraOilP() {
		return hydraOilP;
	}
	public void setHydraOilP(String hydraOilP) {
		if(hydraOilP==null){
			this.hydraOilP = "";
		}else{
			this.hydraOilP = hydraOilP;
		}
	}
	public String getFuelLevel() {
		return fuelLevel;
	}
	public void setFuelLevel(String fuelLevel) {
		if(fuelLevel==null){
			this.fuelLevel = "";
		}else{
			this.fuelLevel = fuelLevel;
		}
	}
	public String getEngRpm() {
		return engRpm;
	}
	public void setEngRpm(String engRpm) {
		if(engRpm==null){
			this.engRpm = "";
		}else{
			this.engRpm = engRpm;
		}
	}
	public String getRcmBv() {
		return rcmBv;
	}
	public void setRcmBv(String rcmBv) {
		if(rcmBv==null){
			this.rcmBv = "";
		}else{
			this.rcmBv = rcmBv;
		}
	}
	public String getMchTime() {
		return mchTime;
	}
	public void setMchTime(String mchTime) {
		if(mchTime==null){
			this.mchTime = "";
		}else{
			this.mchTime = mchTime;
		}
	}
	public String getRcmTime() {
		return rcmTime;
	}
	public void setRcmTime(String rcmTime) {
		if(rcmTime==null){
			this.rcmTime = "";
		}else{
			this.rcmTime = rcmTime;
		}
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		if(sourceType==null){
			this.sourceType = "";
		}else{
			this.sourceType = sourceType;
		}
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		if(ipaddress==null){
			this.ipaddress = "";
		}else{
			this.ipaddress = ipaddress;
		}
	}
	public String getIpport() {
		return ipport;
	}
	public void setIpport(String ipport) {
		if(ipport==null){
			this.ipport = "";
		}else{
			this.ipport = ipport;
		}
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		if(time1==null){
			this.time1 = "";
		}else{
			this.time1 = time1;
		}
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		if(time2==null){
			this.time2 = "";
		}else{
			this.time2 = time2;
		}
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		if(limitType==null){
			this.limitType = "";
		}else{
			this.limitType = limitType;
		}
	}
	public String getLlongitude() {
		return llongitude;
	}
	public void setLlongitude(String llongitude) {
		if(llongitude==null){
			this.llongitude = "";
		}else{
			this.llongitude = llongitude;
		}
	}
	public String getLlatitude() {
		return llatitude;
	}
	public void setLlatitude(String llatitude) {
		if(llatitude==null){
			this.llatitude = "";
		}else{
			this.llatitude = llatitude;
		}
	}
	public String getRlongitude() {
		return rlongitude;
	}
	public void setRlongitude(String rlongitude) {
		if(rlongitude==null){
			this.rlongitude = "";
		}else{
			this.rlongitude = rlongitude;
		}
	}
	public String getRlatitude() {
		return rlatitude;
	}
	public void setRlatitude(String rlatitude) {
		if(rlatitude==null){
			this.rlatitude = "";
		}else{
			this.rlatitude = rlatitude;
		}
	}
	public String getCircleLongitude() {
		return circleLongitude;
	}
	public void setCircleLongitude(String circleLongitude) {
		if(circleLongitude==null){
			this.circleLongitude = "";
		}else{
			this.circleLongitude = circleLongitude;
		}
	}
	public String getCircleLatitude() {
		return circleLatitude;
	}
	public void setCircleLatitude(String circleLatitude) {
		if(circleLatitude==null){
			this.circleLatitude = "";
		}else{
			this.circleLatitude = circleLatitude;
		}
	}
	public String getCircleRadius() {
		return circleRadius;
	}
	public void setCircleRadius(String circleRadius) {
		if(circleRadius==null){
			this.circleRadius = "";
		}else{
			this.circleRadius = circleRadius;
		}
	}
	public String getLockState() {
		return lockState;
	}
	public void setLockState(String lockState) {
		if(lockState==null){
			this.lockState = "";
		}else{
			this.lockState = lockState;
		}
	}
	public String getGpsState() {
		return gpsState;
	}
	public void setGpsState(String gpsState) {
		if(gpsState==null){
			this.gpsState = "";
		}else{
			this.gpsState = gpsState;
		}
	}
	public String getMenxianDianya() {
		return menxianDianya;
	}
	public void setMenxianDianya(String menxianDianya) {
		if(menxianDianya==null){
			this.menxianDianya = "";
		}else{
			this.menxianDianya = menxianDianya;
		}
	}
	public String getNeiDianya() {
		return neiDianya;
	}
	public void setNeiDianya(String neiDianya) {
		if(neiDianya==null){
			this.neiDianya = "";
		}else{
			this.neiDianya = neiDianya;
		}
	}
	public String getWaiDianya() {
		return waiDianya;
	}
	public void setWaiDianya(String waiDianya) {
		if(waiDianya==null){
			this.waiDianya = "";
		}else{
			this.waiDianya = waiDianya;
		}
	}
	public String getLimitAlarmType() {
		return limitAlarmType;
	}
	public void setLimitAlarmType(String limitAlarmType) {
		if(limitAlarmType==null){
			this.limitAlarmType = "";
		}else{
			this.limitAlarmType = limitAlarmType;
		}
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
	public String getWorkmode() {
		return workmode;
	}
	public void setWorkmode(String workmode) {
		this.workmode = workmode;
	}
	public String getRunmode() {
		return runmode;
	}
	public void setRunmode(String runmode) {
		this.runmode = runmode;
	}
	public String getAccelerograph() {
		return accelerograph;
	}
	public void setAccelerograph(String accelerograph) {
		this.accelerograph = accelerograph;
	}

}
