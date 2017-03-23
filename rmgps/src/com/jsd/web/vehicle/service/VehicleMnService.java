package com.jsd.web.vehicle.service;

import java.util.List;

import com.jsd.util.PageRequestBean;
import com.jsd.util.PageResponseBean;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.po.History;

@SuppressWarnings("rawtypes")
public interface VehicleMnService {

	List queryAgentList2(UserVO uservo);

	PageResponseBean queryEmergentList(History h,UserVO uservo,
			PageRequestBean pageRequestBean);

}
