package com.jsd.web.vehicle.service.impl;

import java.util.List;

import com.jsd.web.vehicle.dao.VehicleManageDao;
import com.jsd.web.vehicle.service.VehicleManageService;

@SuppressWarnings({"rawtypes"})
public class VehicleManageServiceImpl implements VehicleManageService{

	private VehicleManageDao vehicleManageDao;

	public VehicleManageDao getVehicleManageDao() {
		return vehicleManageDao;
	}
	public void setVehicleManageDao(VehicleManageDao vehicleManageDao) {
		this.vehicleManageDao = vehicleManageDao;
	}

	@Override
	public List querySubList(String agent_id) {
		return vehicleManageDao.querySubList(agent_id);
	}
	@Override
	public List queryTotalList(String userId) {
		return vehicleManageDao.queryTotalList(userId);
	}
	@Override
	public List manageSubmit11(String agent_id, String vname1, String vno1) {
		return vehicleManageDao.manageSubmit11(agent_id,vname1,vno1);
	}
	@Override
	public List queryTotalList2(String orgId) {
		return vehicleManageDao.queryTotalList2(orgId);
	}
	@Override
	public List manageSubmit21(String user_id, String vname2, String vno2) {
		return vehicleManageDao.manageSubmit21(user_id,vname2,vno2);
	}
	@Override
	public List manageSubmit22(String orgId, String vname2, String vno2) {
		return vehicleManageDao.manageSubmit22(orgId,vname2,vno2);
	}
	@Override
	public void toRight(String ids1, String userId) {
		vehicleManageDao.toRight(ids1,userId);
	}
	@Override
	public void toLeft(String ids2, String agent_id) {
		vehicleManageDao.toLeft(ids2,agent_id);
	}
	@Override
	public void toRight2(String ids1) {
		vehicleManageDao.toRight2(ids1);
	}
}
