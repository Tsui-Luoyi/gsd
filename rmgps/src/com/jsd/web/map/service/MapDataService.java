package com.jsd.web.map.service;

import java.util.List;

public interface MapDataService {
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
