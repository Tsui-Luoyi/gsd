package com.jsd.web.mq;

import com.jsd.cache.mq.ProducerTool;

public class SendMsgToMQ extends ProducerTool{
	
	private static SendMsgToMQ sendMsgToMQ = null;
	private static Object classLock = SendMsgToMQ.class;
	
	public static SendMsgToMQ instance() {
		synchronized (classLock) {
			if(sendMsgToMQ == null) {
				sendMsgToMQ = new SendMsgToMQ();
			}
			return sendMsgToMQ;
		}
	}
	
}
