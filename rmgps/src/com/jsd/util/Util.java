package com.jsd.util;

import java.util.Date;
import java.util.UUID;

public class Util {

	public static String getUUIDstring() {
		String uuid = UUID.randomUUID().toString().toUpperCase();
		return uuid;
	}

	public static String getTimeStamp() {
		String timestamp = "";
		timestamp = String.format("%tF %<tT", new Date());
		return timestamp;
	}

}
