package com.jsd.web.vehicle.po;

import java.io.Serializable;
import java.util.Date;
/*
CREATE TABLE `mn_cmd_history_2` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vno` varchar(30) DEFAULT '',
  `user_id` int(11) DEFAULT NULL,
  `cmd_name` varchar(30) DEFAULT NULL,
  `cmd_type` varchar(6) DEFAULT NULL,
  `devterm_state` varchar(6) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `rev_time` datetime DEFAULT NULL,
  `receive_result` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
 * */
@SuppressWarnings("serial")
public class History implements Serializable{

	private Integer id;				//主键ID
	private String vno;				//整机编号
	private Integer user_id;		//用户id
	private String cmd_name;		//命令名称
	private String cmd_type;		//命令类型
	private String devterm_state;	//终端状态；0-正常，1-紧急
	private Date send_time;			//发送时间
	private Date rev_time;			//接收时间
	private String receive_result;	//接收结果
	private String user_name;		//用户
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	public String getDevterm_state() {
		return devterm_state;
	}
	public void setDevterm_state(String devterm_state) {
		this.devterm_state = devterm_state;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public Date getRev_time() {
		return rev_time;
	}
	public void setRev_time(Date rev_time) {
		this.rev_time = rev_time;
	}
	public String getReceive_result() {
		return receive_result;
	}
	public void setReceive_result(String receive_result) {
		this.receive_result = receive_result;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/*******************非持久化对象*************************/
	private Integer count;		//次数
	private String sendtime;
	private String receivetime;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}
	
}
