package com.jsd.web.vehicle.vo;

import java.io.Serializable;

public class MnCommonVo implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String vno;
	private String longitude;
	private String latitude;
	private String gpslongitude; //真实经度
	private String gpslatitude; //真实纬度
	private String satellitenum;
	private String gsmstrong;
	private String revtime;//也当登录时间用过
	private String cmd_name;//命令名称
	private String cmd_type;//命令类型
	private String start_time;//也当发送命令时间用过
	private String end_time;//也当接收命令时间用过
	private String user_name;//暂未使用
	private String receive_result;
	private String user_id;
	private String locate_flag;
	private String ipaddress;//IP地址
	private String port;//端口号
	private String up_time_normal;//正常上传时间间隔
	private String up_time_urgent;
	private String devterm_state;//终端状态
	private String duration;//终端状态紧急持续时间
	private String week;//预定上传时间 星期
	private String hour;//预定上传时间 小时-几点
	private String minute;//预定上传时间 分-分钟
	private String success_login_num;//成功登录次数
	private String fail_login_num;
	private String success_locate_num;//成功定位次数
	private String fail_locate_num;
	private String cmd_id;//发送命令id
	private String ip;//发送命令者的ip
	private String settingCmdDetail;//发送设置命令的详细内容
	private String cancel_cmdid;//取消发送的命令的cmd_id
	private String addr; //经纬度解析地址
	private String cancel_html;//取消按钮的innerhtml
	private String cancel_click_param;//取消按钮click函数所带的参数
	private String lac;//lac小区号 暂时没有数据库映射关系
	private String cellid;//基站号 暂时没有数据库映射关系
	private String cycDay;//上传间隔（天）
	private String everydayUpNum;//每天上数次数
	private String upinterval;//上传时间间隔
	private String starthour;//上传间隔起始小时
	private String startminute;//上传间隔起始分钟
	private String residue;//剩余次数
	private String emergencyState;//紧急状态
	private String message;//短信号码

	//为vo添加百度纠偏经纬度
	private String b_Longitude;
	private String b_Latitude;




	public String getB_Longitude() {
		return b_Longitude;
	}

	public void setB_Longitude(String b_Longitude) {
		this.b_Longitude = b_Longitude;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmergencyState() {
		return emergencyState;
	}

	public void setEmergencyState(String emergencyState) {
		this.emergencyState = emergencyState;
	}

	public String getResidue() {
		return residue;
	}

	public void setResidue(String residue) {
		this.residue = residue;
	}

	public String getStarthour() {
		return starthour;
	}

	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}

	public String getStartminute() {
		return startminute;
	}

	public void setStartminute(String startminute) {
		this.startminute = startminute;
	}

	public String getB_Latitude() {
		return b_Latitude;
	}

	public void setB_Latitude(String b_Latitude) {
		this.b_Latitude = b_Latitude;
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

	public String getGpslongitude() {
		return gpslongitude;
	}

	public void setGpslongitude(String gpslongitude) {
		this.gpslongitude = gpslongitude;
	}

	public String getGpslatitude() {
		return gpslatitude;
	}

	public void setGpslatitude(String gpslatitude) {
		this.gpslatitude = gpslatitude;
	}

	public String getSatellitenum() {
		return satellitenum;
	}

	public void setSatellitenum(String satellitenum) {
		this.satellitenum = satellitenum;
	}

	public String getGsmstrong() {
		return gsmstrong;
	}

	public void setGsmstrong(String gsmstrong) {
		this.gsmstrong = gsmstrong;
	}

	public String getRevtime() {
		return revtime;
	}

	public void setRevtime(String revtime) {
		this.revtime = revtime;
	}

	public String getCmd_name() {
		return cmd_name;
	}

	public void setCmd_name(String cmd_name) {
		this.cmd_name = cmd_name;
	}

	public String getCmd_type() {
		return cmd_type;
	}

	public void setCmd_type(String cmd_type) {
		this.cmd_type = cmd_type;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReceive_result() {
		return receive_result;
	}

	public void setReceive_result(String receive_result) {
		this.receive_result = receive_result;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getLocate_flag() {
		return locate_flag;
	}

	public void setLocate_flag(String locate_flag) {
		this.locate_flag = locate_flag;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUp_time_normal() {
		return up_time_normal;
	}

	public void setUp_time_normal(String up_time_normal) {
		this.up_time_normal = up_time_normal;
	}

	public String getUp_time_urgent() {
		return up_time_urgent;
	}

	public void setUp_time_urgent(String up_time_urgent) {
		this.up_time_urgent = up_time_urgent;
	}

	public String getDevterm_state() {
		return devterm_state;
	}

	public void setDevterm_state(String devterm_state) {
		this.devterm_state = devterm_state;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSuccess_login_num() {
		return success_login_num;
	}

	public void setSuccess_login_num(String success_login_num) {
		this.success_login_num = success_login_num;
	}

	public String getFail_login_num() {
		return fail_login_num;
	}

	public void setFail_login_num(String fail_login_num) {
		this.fail_login_num = fail_login_num;
	}

	public String getSuccess_locate_num() {
		return success_locate_num;
	}

	public void setSuccess_locate_num(String success_locate_num) {
		this.success_locate_num = success_locate_num;
	}

	public String getFail_locate_num() {
		return fail_locate_num;
	}

	public void setFail_locate_num(String fail_locate_num) {
		this.fail_locate_num = fail_locate_num;
	}

	public String getCmd_id() {
		return cmd_id;
	}

	public void setCmd_id(String cmd_id) {
		this.cmd_id = cmd_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSettingCmdDetail() {
		return settingCmdDetail;
	}

	public void setSettingCmdDetail(String settingCmdDetail) {
		this.settingCmdDetail = settingCmdDetail;
	}

	public String getCancel_cmdid() {
		return cancel_cmdid;
	}

	public void setCancel_cmdid(String cancel_cmdid) {
		this.cancel_cmdid = cancel_cmdid;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCancel_html() {
		return cancel_html;
	}

	public void setCancel_html(String cancelHtml) {
		cancel_html = cancelHtml;
	}

	public String getCancel_click_param() {
		return cancel_click_param;
	}

	public void setCancel_click_param(String cancelClickParam) {
		cancel_click_param = cancelClickParam;
	}

	public String getEverydayUpNum() {
		return everydayUpNum;
	}

	public void setEverydayUpNum(String everydayUpNum) {
		this.everydayUpNum = everydayUpNum;
	}

	public String getUpinterval() {
		return upinterval;
	}

	public void setUpinterval(String upinterval) {
		this.upinterval = upinterval;
	}

	public String getCycDay() {
		return cycDay;
	}

	public void setCycDay(String cycDay) {
		this.cycDay = cycDay;
	}

}
