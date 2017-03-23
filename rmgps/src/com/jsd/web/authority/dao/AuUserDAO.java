package com.jsd.web.authority.dao;

import java.util.List;

public interface AuUserDAO {

	/**
	 * 根据用户查询车辆机构关联表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryVehicleOrgRelListByUser(String userId);

}
