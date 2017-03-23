package com.jsd.web.vehicle.dao;

import java.util.List;

import com.jsd.util.PageRequestBean;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.po.History;

@SuppressWarnings("rawtypes")
public interface VehicleMnDao {

	List queryAgentList2(UserVO uservo);

	List getTotal(History h,UserVO uservo);

	List getEmergentListByUserVo(PageRequestBean pageRequestBean, History h, UserVO user,
			Integer firstResult, Integer maxResults);
}
