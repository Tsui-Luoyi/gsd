package com.jsd.web.vehicle.service;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface VehicleManageService {

	List querySubList(String user_id);

	List queryTotalList(String userId);

	List manageSubmit11(String agent_id, String vname1, String vno1);

	List queryTotalList2(String orgId);

	List manageSubmit21(String user_id, String vname2, String vno2);

	List manageSubmit22(String orgId, String vname2, String vno2);

	void toRight(String ids1, String userId);

	void toLeft(String ids2, String agent_id);

	void toRight2(String ids1);
}
