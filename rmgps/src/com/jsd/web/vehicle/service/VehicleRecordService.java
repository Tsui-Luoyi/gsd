package com.jsd.web.vehicle.service;

import com.jsd.web.po.DevtermInfo;
import com.jsd.web.po.TvehicleInfoRecord007;
import com.jsd.web.vehicle.vo.OtherInfoVo;

public interface VehicleRecordService {

	public TvehicleInfoRecord007 findVehicleRecord007(String vno);

	public boolean modifyEngineNo007(String vno, String vname, String engineNo);

	public DevtermInfo findDevtermRecord007(String vno);

	public OtherInfoVo findOtherRecord007(String vno);
}
