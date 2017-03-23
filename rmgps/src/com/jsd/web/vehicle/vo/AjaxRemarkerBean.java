package com.jsd.web.vehicle.vo;

import java.util.List;
/**
 * 用来封装地图标注的类
 * 用于struts-json返回值
 * @author Jnhuy
 *
 */
public class AjaxRemarkerBean {
	private List<RemarkerVO> remarkerlist;

	public List<RemarkerVO> getRemarkerlist() {
		return remarkerlist;
	}

	public void setRemarkerlist(List<RemarkerVO> remarkerlist) {
		this.remarkerlist = remarkerlist;
	}
	
}
