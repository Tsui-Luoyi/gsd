package com.jsd.web.vehicle.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jsd.web.po.TorgTable;
import com.jsd.web.po.TvehicleModel;

@SuppressWarnings({ "rawtypes", "serial" })
public class VehicleListVO implements Serializable{

	private Integer vid;
	private TvehicleModel tvehicleModel;
	private String vm_id;//型号id
	private String mname;//型号名字
	private String longitude;
	private String latitude;
	private String user_id;//代理商用户id
	private String agent_name;//代理商名字
	private String vno;
	private String vname;
	private String engineNo;
	private String carframeno;
	private String vcolor;
	private Integer vweight;
	private String trademark;
	private String picPath;
	private Integer initMoto;
	private String orgNo;
	private String orgName;
	private Date leaveDate;
	private Date inputTime;
	private String rcm_time;// 最后接受数据时间
	private String mch_state;// 整机状态
	private String eng_rpm;
	private String eng_coolant_t;
	private String fuel_level;
	private String work_time;// 累计工作时间
	private String alarmDesc;
	private String queryVModel;

	private Set tvvBands = new HashSet(0);
	private TorgTable torgTable;

	private String queryVno;
	private String queryVType;
	private String workState;
	private List newObjectList;// 含有累计工作时间跟相匹配的vno的map对象集合
	private String dno;

	private String timenum;//无数据小时
	private String alarmcon;//报警内容
	private String simnum;//手机号
	private String gsmstr;//信号是否异常
	private String locatef;//定位是否成功
	private String sellsta;//销售状态
	private String sellt1;//销售时间从
	private String sellt2;//销售时间至
	private String locksta;//锁车状态
	private String agentn;//代理商用户

	private String addr;//经纬度解析地址

	public TorgTable getTorgTable() {
		return torgTable;
	}

	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public TvehicleModel getTvehicleModel() {
		return tvehicleModel;
	}

	public void setTvehicleModel(TvehicleModel tvehicleModel) {
		this.tvehicleModel = tvehicleModel;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getCarframeno() {
		return carframeno;
	}

	public void setCarframeno(String carframeno) {
		this.carframeno = carframeno;
	}

	public String getVcolor() {
		return vcolor;
	}

	public void setVcolor(String vcolor) {
		this.vcolor = vcolor;
	}

	public Integer getVweight() {
		return vweight;
	}

	public void setVweight(Integer vweight) {
		this.vweight = vweight;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getInitMoto() {
		return initMoto;
	}

	public void setInitMoto(Integer initMoto) {
		this.initMoto = initMoto;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Set getTvvBands() {
		return tvvBands;
	}

	public void setTvvBands(Set tvvBands) {
		this.tvvBands = tvvBands;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRcm_time() {
		return rcm_time;
	}

	public void setRcm_time(String rcm_time) {
		this.rcm_time = rcm_time;
	}

	public String getQueryVno() {
		return queryVno;
	}

	public void setQueryVno(String queryVno) {
		this.queryVno = queryVno;
	}

	public String getQueryVType() {
		return queryVType;
	}

	public void setQueryVType(String queryVType) {
		this.queryVType = queryVType;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getEng_coolant_t() {
		return eng_coolant_t;
	}

	public void setEng_coolant_t(String eng_coolant_t) {
		this.eng_coolant_t = eng_coolant_t;
	}

	public String getFuel_level() {
		return fuel_level;
	}

	public void setFuel_level(String fuel_level) {
		this.fuel_level = fuel_level;
	}

	public String getEng_rpm() {
		return eng_rpm;
	}

	public void setEng_rpm(String eng_rpm) {
		this.eng_rpm = eng_rpm;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}

	public String getMch_state() {
		return mch_state;
	}

	public void setMch_state(String mchState) {
		mch_state = mchState;
	}

	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String workTime) {
		work_time = workTime;
	}

	public String getQueryVModel() {
		return queryVModel;
	}

	public void setQueryVModel(String queryVModel) {
		this.queryVModel = queryVModel;
	}

	public List getNewObjectList() {
		return newObjectList;
	}

	public void setNewObjectList(List newObjectList) {
		this.newObjectList = newObjectList;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getTimenum() {
		return timenum;
	}

	public void setTimenum(String timenum) {
		this.timenum = timenum;
	}

	public String getAlarmcon() {
		return alarmcon;
	}

	public void setAlarmcon(String alarmcon) {
		this.alarmcon = alarmcon;
	}

	public String getSimnum() {
		return simnum;
	}

	public void setSimnum(String simnum) {
		this.simnum = simnum;
	}

	public String getGsmstr() {
		return gsmstr;
	}

	public void setGsmstr(String gsmstr) {
		this.gsmstr = gsmstr;
	}

	public String getLocatef() {
		return locatef;
	}

	public void setLocatef(String locatef) {
		this.locatef = locatef;
	}

	public String getSellsta() {
		return sellsta;
	}

	public void setSellsta(String sellsta) {
		this.sellsta = sellsta;
	}

	public String getSellt1() {
		return sellt1;
	}

	public void setSellt1(String sellt1) {
		this.sellt1 = sellt1;
	}

	public String getSellt2() {
		return sellt2;
	}

	public void setSellt2(String sellt2) {
		this.sellt2 = sellt2;
	}

	public String getLocksta() {
		return locksta;
	}

	public void setLocksta(String locksta) {
		this.locksta = locksta;
	}

	public String getAgentn() {
		return agentn;
	}

	public void setAgentn(String agentn) {
		this.agentn = agentn;
	}

	public String getVm_id() {
		return vm_id;
	}

	public void setVm_id(String vm_id) {
		this.vm_id = vm_id;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}


}
