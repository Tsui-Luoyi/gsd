package com.jsd.web.vehicle.service;

import java.util.List;

import com.jsd.web.common.PageInfo;
import com.jsd.web.vehicle.vo.MnCommonVo;

@SuppressWarnings("rawtypes")
public interface MnService {

	MnCommonVo findCurrentInfo(String vno);

	List findCmdHistoryInfo(MnCommonVo vo, PageInfo pagination);

	List findLoginHistoryInfo(MnCommonVo vo, PageInfo pagination);

	MnCommonVo findCmdInfo1(String vno);

	MnCommonVo findCmdInfo2(String vno);

	List findCmdQueueInfo(String vno);

	void sendGatherCmd(MnCommonVo mnvo);

	void sendCancel(MnCommonVo mnvo);

	void sendSettingCmd(MnCommonVo mnvo);

	void sendSettingLYBCmd(MnCommonVo mnvo,int type);

	int findDeviceType(String vno);
}
