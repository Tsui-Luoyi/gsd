package com.jsd.web.map.service.impl;

import java.util.List;

import com.jsd.web.map.dao.MapDataDAO;
import com.jsd.web.map.service.MapDataService;

public class MapDataServiceImpl implements MapDataService {

	public MapDataServiceImpl(){

	}
	private MapDataDAO mapDataDao;

	public void setMapDataDao(MapDataDAO mapDataDao) {
		this.mapDataDao = mapDataDao;
	}


	@Override
	@SuppressWarnings({ "rawtypes" })
	public List getGpsLocateTrack(String vno, String startTime, String endTime) {
		List rstList = mapDataDao.getGpsLocateTrack(vno, startTime, endTime);
		return rstList;
	}
}
