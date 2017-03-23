package com.jsd.web.vehicle.dao;

import com.jsd.web.po.DevtermInfo;
import com.jsd.web.po.TvehicleInfoRecord007;

public interface VehicleRecordDao {

	public TvehicleInfoRecord007 findVehicleRecord007(String vno);

	public boolean modifyEngineNo007(String vno, String vname, String engineNo);

	public DevtermInfo findDevtermRecord007(String vno);

	public void findOtherRecord007(String vno);

	public TvehicleInfoRecord007 findvm_id(String vno);

	public String findtvModel(Integer vm_id);

}
