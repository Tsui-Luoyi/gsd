package com.jsd.web.vehicle.po;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class VehicleListPO implements Serializable{

	private Integer id;			//主键
	private Integer vid;		//车辆id
	private String vno;			//车辆编号	
	private String vname;		//车辆名称
	private String dno;			//终端编号
	private Integer vtype_id;	//车辆类型id
	private String vtype_name;	//车辆类型名称
	private Integer did;		//终端id
	private Integer dtype_id;	//终端类型id
	private String dtype_name;	//终端类型名称
	private Integer level;		//代理商级别
	private Integer agent_id;	//代理商id
	private String agent_name;	//代理商名称
	private String addr;		//地址
	private Integer emergency_state;//紧急状态
	private Date update_time;	//最新数据时间
	private Integer offcount;	//离线统计
	private String locate_mode;	//定位方式
	private String longitude;	//纠偏后的经度
	private String latitude;	//纠偏后的纬度
	private String o_lng;		//纠偏前的经度
	private String o_lat;		//纠偏前的纬度
	private Integer trange;		//范围
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
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
	public Integer getVtype_id() {
		return vtype_id;
	}
	public void setVtype_id(Integer vtype_id) {
		this.vtype_id = vtype_id;
	}
	public String getVtype_name() {
		return vtype_name;
	}
	public void setVtype_name(String vtype_name) {
		this.vtype_name = vtype_name;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public Integer getDtype_id() {
		return dtype_id;
	}
	public void setDtype_id(Integer dtype_id) {
		this.dtype_id = dtype_id;
	}
	public String getDtype_name() {
		return dtype_name;
	}
	public void setDtype_name(String dtype_name) {
		this.dtype_name = dtype_name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
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
	public Integer getEmergency_state() {
		return emergency_state;
	}
	public void setEmergency_state(Integer emergency_state) {
		this.emergency_state = emergency_state;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getOffcount() {
		return offcount;
	}
	public void setOffcount(Integer offcount) {
		this.offcount = offcount;
	}
	public String getLocate_mode() {
		return locate_mode;
	}
	public void setLocate_mode(String locate_mode) {
		this.locate_mode = locate_mode;
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
	public Integer getTrange() {
		return trange;
	}
	public void setTrange(Integer trange) {
		this.trange = trange;
	}
	/*************非持久化对象************/
	private String vname_list;
	private String dno_list;
	private String agent_id_list;
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVname_list() {
		return vname_list;
	}
	public void setVname_list(String vname_list) {
		try {
			if(vname_list!=null && vname_list.trim().length()>0){
				String string = new String(vname_list.getBytes("ISO-8859-1"),"UTF-8");
				this.vname_list = string;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDno_list() {
		return dno_list;
	}
	public void setDno_list(String dno_list) {
		this.dno_list = dno_list;
	}
	public String getAgent_id_list() {
		return agent_id_list;
	}
	public void setAgent_id_list(String agent_id_list) {
		this.agent_id_list = agent_id_list;
	}
	/*************非持久化对象************/
}
