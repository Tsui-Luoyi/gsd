package com.jsd.web.vehicle.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class VehicleOfProtocal extends ActionSupport {
	private String vmid;
	private String vno;
	private String vname;

	public String readVehProXML() {
		String s_xmlpath = "com/jsd/web/xml/vehicleOfProtocal.xml";
		ClassLoader classLoader = VehicleOfProtocal.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream(s_xmlpath);

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element rootElement = document.getDocumentElement();
			NodeList nodeList = rootElement.getElementsByTagName("vehicle");
			for (int i = 0; i < nodeList.getLength(); i++) {
				String getNodeValue = nodeList.item(i).getChildNodes().item(1).getFirstChild().getNodeValue();
				if (this.vmid.equals(getNodeValue)) {
					String forwardTo = nodeList.item(i).getChildNodes().item(3).getFirstChild().getNodeValue();
					return forwardTo;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		try {
			this.vno = java.net.URLDecoder.decode(vno,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		try {
			this.vname = java.net.URLDecoder.decode(vname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
