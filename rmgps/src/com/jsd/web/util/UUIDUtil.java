/**
 * 
 */
package com.jsd.web.util;

import java.util.UUID;

/**
 * @author Jnhuy
 * 
 */
public class UUIDUtil {
	public static String creatUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
