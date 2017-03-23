package com.jsd.web.map.dao;

import java.util.List;

public interface MapDataDAO {

	/**
	 * GPS定位历史查询
	 * @param vno
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getGpsLocateTrack(String vno,String startTime,String endTime);
}
