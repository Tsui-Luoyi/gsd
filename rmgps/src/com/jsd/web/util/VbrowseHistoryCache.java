package com.jsd.web.util;

import java.util.HashMap;
import java.util.Map;

public class VbrowseHistoryCache {

	private Map<String, String> browseHistoryMap = new HashMap<String, String>();
	private static VbrowseHistoryCache browseCache = null;

	
	public static VbrowseHistoryCache instace(){
		if(browseCache==null){
			browseCache = new VbrowseHistoryCache();
		}
		return browseCache;
	}
	
	public String getHistory(String userId){
		if(userId == null || userId.equals("")){
			return null;
		}
		return (String)browseHistoryMap.get(userId);
	}
	
	public void setHistory(String userId,String vno){
		browseHistoryMap.put(userId,vno);
	}
	
	public void clearHistory(String userId){
		browseHistoryMap.remove(userId);
	}
}
