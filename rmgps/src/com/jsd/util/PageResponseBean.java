package com.jsd.util;

import java.util.List;

/**
 * 存放分页查询 结果数据 ，在序列化时 生成 datagrid 需要json 格式
 * @author 
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class PageResponseBean implements java.io.Serializable{
	private int total;	//数据总数
	private List rows; 	// 集合 存放每个对象
	private List WLrows;

	public List getWLrows() {
		return WLrows;
	}
	public void setWLrows(List wLrows) {
		WLrows = wLrows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
