package com.jsd.web.vehicle.service.impl;

import java.io.Serializable;

import com.jsd.web.po.DevtermInfo;
import com.jsd.web.po.TvehicleInfoRecord007;
import com.jsd.web.vehicle.dao.VehicleRecordDao;
import com.jsd.web.vehicle.service.VehicleRecordService;
import com.jsd.web.vehicle.vo.OtherInfoVo;

@SuppressWarnings("serial")
public class VehicleRecordServiceImpl implements VehicleRecordService,Serializable {
	private VehicleRecordDao vehicleRecordDao;

	public void setVehicleRecordDao(VehicleRecordDao vehicleRecordDao) {
		this.vehicleRecordDao = vehicleRecordDao;
	}

	// 查询整机记录信息
	@Override
	public TvehicleInfoRecord007 findVehicleRecord007(String vno) {
		TvehicleInfoRecord007 t007 = this.vehicleRecordDao.findVehicleRecord007(vno);
		return t007;
	}

	@Override
	public boolean modifyEngineNo007(String vno, String vname, String engineNo) {
		boolean flag = vehicleRecordDao.modifyEngineNo007(vno,vname,engineNo);
		return flag;
	}

	@Override
	public DevtermInfo findDevtermRecord007(String vno) {
		// TODO Auto-generated method stub
		DevtermInfo dInfo=vehicleRecordDao.findDevtermRecord007(vno);
		return dInfo;
	}

	@Override
	public OtherInfoVo findOtherRecord007(String vno) {
		// TODO Auto-generated method stub
		OtherInfoVo ov=new OtherInfoVo();
		TvehicleInfoRecord007 tInfo = vehicleRecordDao.findvm_id(vno);
		String findtvModel = vehicleRecordDao.findtvModel(tInfo.getVm_id());
		ov.setmName(findtvModel);
		return ov;
	}
}
