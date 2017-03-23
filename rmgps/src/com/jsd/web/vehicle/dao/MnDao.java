package com.jsd.web.vehicle.dao;

import java.util.List;

import com.jsd.web.common.PageInfo;
import com.jsd.web.po.GpsLocateCurrent;
import com.jsd.web.vehicle.vo.MnCommonVo;

@SuppressWarnings("rawtypes")
public interface MnDao {

	GpsLocateCurrent findCurrentInfo(String vno);

	List findCmdHistoryInfo(MnCommonVo vo, PageInfo pagination);

	List findLoginHistoryInfo(MnCommonVo vo, PageInfo pagination);

	List findCmdInfo1(String vno);

	List findCmdInfo2(String vno);

	List findCmdQueueInfo(String vno);

	int findDeviceType(String vno);
}
