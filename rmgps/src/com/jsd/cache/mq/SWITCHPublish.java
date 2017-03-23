package com.jsd.cache.mq;

/**
 * 逻辑层交换消息发布者
 * @author liujd
 * @date 2009-11-27
 * @version $Revision: 1.0.0.1 $
 */

public class SWITCHPublish extends ProducerTool {

	private static SWITCHPublish switchpublish = null;
	private static Object classLock = SWITCHPublish.class;
	
	private SWITCHPublish() {
		// TODO Auto-generated constructor stub
	}

	public static SWITCHPublish instance() {
		synchronized (classLock) {
			if(switchpublish == null) {
				switchpublish = new SWITCHPublish();
			}
			return switchpublish;
		}
	}
}
