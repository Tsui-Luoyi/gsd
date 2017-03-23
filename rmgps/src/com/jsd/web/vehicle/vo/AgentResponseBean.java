package com.jsd.web.vehicle.vo;

import java.util.List;

/**
 * 存放分页查询 结果数据 ，在序列化时 生成 datagrid 需要json 格式
 * @author 
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class AgentResponseBean implements java.io.Serializable{
	private int total;	//数据总数
	private List subList; 	// 集合 存放每个对象
	private List totalList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getSubList() {
		return subList;
	}
	public void setSubList(List subList) {
		this.subList = subList;
	}
	public List getTotalList() {
		return totalList;
	}
	public void setTotalList(List totalList) {
		this.totalList = totalList;
	}

}
