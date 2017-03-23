/**
 * 
 */
package com.jsd.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author cuilupeng
 *
 */
public class Tools {

	@SuppressWarnings("unchecked")
	public static Map nullConvertEmptyForMap(Map map) {   
	    Map convertedMap = null;   
	    Set mapset = map.entrySet();   
	    String EMPTYSTR = "";   
	    if (map != null) {   
	        convertedMap = new HashMap();   
	        for (Iterator it = mapset.iterator(); it.hasNext();) {   
	            Entry entry = (Entry) it.next();   
	            if (entry.getValue() == null) {   
	                convertedMap.put(entry.getKey(), EMPTYSTR);   
	            }   
	            else {   
	                convertedMap.put(entry.getKey(), entry.getValue());   
	            }   
	        }   
	    }
	    return convertedMap;   
	}
}
