package com.jsd.web.util;

import java.lang.reflect.Field;  
import java.sql.Timestamp;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;
import java.util.Set;  
import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public class ObjectUtils {
	/**
	 * 将指定对象属性名称和属性值转化为Json字符串
	 */
	public static String objectToJson(Object obj) {
		if(obj == null)
			return "";
		
		Map map = null;
		try {
			map = (Map)objectToMap(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			System.out.println("Java对象映射为Map对象出现异常！");
			e.printStackTrace();
			return "";
		}
		
		if(map == null || map.isEmpty())
			return "";
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	 /** 
     * 将指定对象属性名称和属性值转化为Map键值对 
     *  
     * @param obj 
     * @return 
     */  
	public static HashMap objectToMap(Object obj) throws Exception {  
        if (obj == null) {  
            throw new Exception("对象为空");  
        }  
  
        Class clazz = obj.getClass();  
        HashMap map = new HashMap();  
        getClass(clazz,map,obj);  
        HashMap newMap = convertHashMap(map);  
        return newMap;  
    }  
   /** 
     * 
    */  
    private static void getClass(Class clazz,HashMap map,Object obj) throws Exception{  
        if(clazz.getSimpleName().equals("Object")){  
            return;  
        }  
          
    Field[] fields = clazz.getDeclaredFields();  
        if (fields == null || fields.length <= 0) {  
            throw new Exception("当前对象中没有任何属性值");  
        }  
        for(int i=0;i<fields.length;i++){  
            fields[i].setAccessible(true);  
            String name=fields[i].getName();  
            Object value=fields[i].get(obj);  
            map.put(name,value);  
              
        }  
        Class superClzz=clazz.getSuperclass();  
        getClass(superClzz,map,obj);  
    }  
    /** 
     *  
     * @param map 
     * @return 
     * @throws Exception 
     */  
    private static HashMap convertHashMap(HashMap map) throws Exception {  
  
        HashMap newMap = new HashMap();  
        Set keys = map.keySet();  
        Iterator it = keys.iterator();  
        while (it.hasNext()) {  
            Object key = it.next();  
            convertToString(map.get(key), newMap, key);  
        }  
  
        return newMap;  
    }  
  
    /** 
     *  
     * @param value 
     * @param newMap 
     * @param key 
     */  
    private static void convertToString(Object value, HashMap newMap, Object key) {  
        if (value != null) {  
            Class clazz = value.getClass();  
            if (isBaseType(clazz)) {  
                newMap.put(key, value.toString());  
            } else if (clazz == String.class) {  
                newMap.put(key, value.toString());  
            } else if (clazz == Date.class) {  
                Date date = (Date) value;  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                newMap.put(key, sdf.format(date));  
            } else if (clazz == Timestamp.class) {  
                Timestamp timestamp = (Timestamp) value;  
                long times = timestamp.getTime();  
                Date date = new Date(times);  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                newMap.put(key, sdf.format(date));  
            } else if (clazz == java.sql.Date.class) {  
                java.sql.Date sqlDate = (java.sql.Date) value;  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                newMap.put(key, sdf.format(sqlDate));  
            } else {  
                newMap.put(key, value.toString());  
            }  
        } else {  
            newMap.put(key, "");  
        }  
  
    }  
  
    /** 
     *  
     * @param clazz 
     * @return 
     */  
    private static boolean isBaseType(Class clazz) {  
  
        if (clazz == Integer.class) {  
            return true;  
        }  
        if (clazz == Long.class) {  
            return true;  
        }  
        if (clazz == Double.class) {  
            return true;  
        }  
        if (clazz == Byte.class) {  
            return true;  
        }  
        if (clazz == Float.class) {  
            return true;  
        }  
        if (clazz == Short.class) {  
            return true;  
        }  
        if (clazz == Boolean.class) {  
            return true;  
        }  
        return false;  
    }  
  
    /** 
     * 测试 
     *  
     * @param args 
     */  
    public static void main(String args[]) throws Exception {  
    	
    }  
}
