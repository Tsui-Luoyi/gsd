package com.jsd.cache.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * 应用配置管理类
 *
 * @author liujd
 * @date 2009-11-27
 * @version $Revision: 1.0.0.1 $
 */

public class AppConfig {
	private static AppConfig appconfig = null;
	private static Object classLock = AppConfig.class;
	private HashMap<String, String> map = null;

	private AppConfig() {
		map = new HashMap<String, String>();
	}

	public static AppConfig instance() {
		synchronized (classLock) {
			if (appconfig == null) {
				appconfig = new AppConfig();
			}

			return appconfig;
		}
	}

	@SuppressWarnings("rawtypes")
	public int Init(String filename) throws JDOMException, IOException {
		if (filename == null)
			return -1;
		if (filename.equals(""))
			return -1;
		File file = new File(filename);
		if (file.exists() == false) {
//			System.out.println("配置文件:" + filename + "不存在...");
			return -1;
		}

		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(file);
		Element root = doc.getRootElement();
		List node = root.getChildren();
		Element et = null;
		for (Object obj : node) {
			et = (Element) obj;
			map.put(et.getName(), et.getText());
		}
		return 0;
	}



	public String getValue(String key) {
		return map.get(key);
	}

}
