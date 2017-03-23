package com.jsd.util;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 存放 分页查询 请求数据
 * @author 
 */
@SuppressWarnings("serial")
public class PageRequestBean implements java.io.Serializable{
	private int page; 		// 页码
	private int rows; 		// 每页多少条
	private String sort;	//排序字段
	private String order;	//asc|desc
	private DetachedCriteria detachedCriteria; // 查询所有条件

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
}
