package com.jsd.web.vehicle.service.impl;

import java.io.Serializable;
import java.util.List;

import com.jsd.util.PageRequestBean;
import com.jsd.util.PageResponseBean;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.dao.VehicleMnDao;
import com.jsd.web.vehicle.po.History;
import com.jsd.web.vehicle.service.VehicleMnService;

@SuppressWarnings({"serial","rawtypes"})
public class VehicleMnServiceImpl implements VehicleMnService,Serializable{

	private VehicleMnDao vehicleMnDao;

	public VehicleMnDao getVehicleMnDao() {
		return vehicleMnDao;
	}
	public void setVehicleMnDao(VehicleMnDao vehicleMnDao) {
		this.vehicleMnDao = vehicleMnDao;
	}
	//根据用户来查询用户下的代理商
	@Override
	public List queryAgentList2(UserVO uservo) {
		return this.vehicleMnDao.queryAgentList2(uservo);
	}

	//子代分页 查询方法
	@Override
	public PageResponseBean queryEmergentList(History h, UserVO uservo,PageRequestBean pageRequestBean) {
		// 根据 page 和 rows 计算 firstResult 和 maxResults
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();
		//根据用户查询子代的总数
		List list1 = vehicleMnDao.getTotal(h,uservo);
		int total = list1.size();
		// 结果数据
		List list = vehicleMnDao.getEmergentListByUserVo(pageRequestBean,h,uservo,firstResult, maxResults);

		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}

}
