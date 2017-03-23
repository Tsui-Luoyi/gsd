package com.jsd.web.vehicle.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.jsd.util.Util;
import com.jsd.web.common.PageInfo;
import com.jsd.web.common.dao.DictDefineDAO;
import com.jsd.web.mq.SendMsgToMQ;
import com.jsd.web.po.GpsLocateCurrent;
import com.jsd.web.vehicle.dao.MnDao;
import com.jsd.web.vehicle.service.MnService;
import com.jsd.web.vehicle.vo.MnCommonVo;

@SuppressWarnings({"unchecked","rawtypes"})
public class MnServiceImpl implements MnService {
	private MnDao mnDao;
	private DictDefineDAO dectDefineDao;

	public void setMnDao(MnDao mnDao) {
		this.mnDao = mnDao;
	}

	public void setDectDefineDao(DictDefineDAO dectDefineDao) {
		this.dectDefineDao = dectDefineDao;
	}

	private List constructList(List<Object[]> list) {
		List cmdHistoryList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				MnCommonVo vo = new MnCommonVo();
				vo.setVno(getObj(obj[0]));
				vo.setCmd_name(getObj(obj[2]));
				vo.setCmd_type(getObj(obj[3]));
				vo.setStart_time(getObj(obj[4]));
				vo.setEnd_time(getObj(obj[5]));
				vo.setReceive_result(getObj(obj[6]));

				cmdHistoryList.add(vo);
			}
		}
		return cmdHistoryList;
	}

	private List constructList_3(List<Object[]> list) {
		List cmdQueueList = new ArrayList();
		for (Object[] obj : list) {
			MnCommonVo vo = new MnCommonVo();
			vo.setCmd_name(getObj(obj[3]));
			vo.setCmd_type(getObj(obj[4]));
			vo.setStart_time(getObj(obj[5]));
			vo.setCmd_id(getObj(obj[6]));

			cmdQueueList.add(vo);
		}

		return cmdQueueList;
	}

	private MnCommonVo constructList_7(List<Object[]> list) {
		MnCommonVo vo = new MnCommonVo();
		for (Object[] obj : list) {
			vo.setSuccess_login_num(getObj(obj[5]));
			vo.setFail_login_num(getObj(obj[6]));
			vo.setSuccess_locate_num(getObj(obj[7]));
			vo.setFail_locate_num(getObj(obj[8]));
			vo.setEmergencyState(getObj(obj[11]));
			vo.setResidue(getObj(obj[15]));
			break;// 循环一次，取第一个
		}

		return vo;
	}

	private MnCommonVo constructList_8(List<Object[]> list) {
		MnCommonVo vo = new MnCommonVo();
		for (Object[] obj : list) {
			vo.setIpaddress(getObj(obj[2]) + "-" + getObj(obj[3]) + "-"
					+ getObj(obj[4]) + "-" + getObj(obj[5]));
			vo.setPort(getObj(obj[6]));
			vo.setEmergencyState(getObj(obj[7]));
			vo.setStarthour(getObj(obj[8]));
			vo.setStartminute(getObj(obj[9]));
			vo.setResidue(getObj(obj[10]));
			vo.setUpinterval(getObj(obj[11]));
			vo.setEverydayUpNum(getObj(obj[12]));
			vo.setHour(getObj(obj[13]));
			vo.setMinute(getObj(obj[14]));
			vo.setMessage(getObj(obj[15]));
			break;// 循环一次，取第一个
		}

		return vo;
	}

	private String getObj(Object obj) {
		if (obj == null) {
			return "";
		} else {
			if (obj instanceof Integer) {
				return ((Integer) obj).toString();
			} else if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof Double) {
				return ((Double) obj).toString();
			} else if (obj instanceof Float) {
				return ((Float) obj).toString();
			} else if (obj instanceof Long) {
				return ((Long) obj).toString();
			} else if (obj instanceof Boolean) {
				return ((Boolean) obj).toString();
			} else if (obj instanceof Date) {
				return ((Date) obj).toString();
			} else if (obj instanceof Timestamp) {
				return ((Timestamp) obj).toString();
			} else {
				return "";
			}
		}
	}

	/**
	 * constructList_5(List<Object[]> list) 专门用于处理登录信息历史查询list
	 *
	 * @param list
	 * @return
	 */

	private List constructList_5(List<Object[]> list) {
		List loginHistoryList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				MnCommonVo vo = new MnCommonVo();
				vo.setVno(getObj(obj[0]));
				vo.setRevtime(getObj(obj[1]));
				vo.setLocate_flag(getObj(obj[2]));
				vo.setLongitude(getObj(obj[3]));
				vo.setLatitude(getObj(obj[4]));
				vo.setGsmstrong(getObj(obj[5]));

				if ("V".equals(vo.getLocate_flag())) {
					vo.setAddr("lac:" + getObj(obj[6]) + ",cellid:"
							+ getObj(obj[7]) + "LBS数据库正在更新");
				} else {
					vo.setAddr(getObj(obj[8]));
				}
				loginHistoryList.add(vo);
			}
		}
		return loginHistoryList;
	}

	@Override
	public MnCommonVo findCurrentInfo(String vno) {
		MnCommonVo vo = new MnCommonVo();
		GpsLocateCurrent gc = this.mnDao.findCurrentInfo(vno);
		if (gc != null) {
			vo.setLocate_flag(gc.getLocateFlag());
			vo.setB_Latitude(gc.getB_Latitude());
			vo.setB_Longitude(gc.getB_Longitude());
			vo.setLatitude(gc.getLatitude());
			vo.setLongitude(gc.getLongitude());
			vo.setGpslongitude(gc.getO_lng());
			vo.setGpslatitude(gc.getO_lat());
			vo.setSatellitenum(gc.getSatellitenum());
			vo.setGsmstrong(gc.getGsmStrong());
			vo.setRevtime(gc.getRevtime().toString());
			vo.setAddr(gc.getAddr());
		}
		return vo;
	}

	@Override
	public List findCmdHistoryInfo(MnCommonVo vo, PageInfo pagination) {
		List list = this.mnDao.findCmdHistoryInfo(vo, pagination);
		if (list != null) {
			return constructList(list);
		}

		return new ArrayList();
	}

	@Override
	public List findLoginHistoryInfo(MnCommonVo vo, PageInfo pagination) {
		List list = this.mnDao.findLoginHistoryInfo(vo, pagination);
		if (list != null) {
			return constructList_5(list);
		}

		return new ArrayList();
	}

	@Override
	public MnCommonVo findCmdInfo1(String vno) {
		MnCommonVo vo = new MnCommonVo();
		List list = this.mnDao.findCmdInfo1(vno);
		if (list != null && list.size() > 0) {
			vo = constructList_7(list);
		}

		return vo;
	}

	@Override
	public MnCommonVo findCmdInfo2(String vno) {
		MnCommonVo vo = new MnCommonVo();
		List list = this.mnDao.findCmdInfo2(vno);
		if (list != null && list.size() > 0) {
			vo = constructList_8(list);
		}

		return vo;
	}

	@Override
	public List findCmdQueueInfo(String vno) {
		List list = this.mnDao.findCmdQueueInfo(vno);
		if (list != null && list.size() > 0) {
			return constructList_3(list);
		}

		return new ArrayList();
	}

	@Override
	public void sendGatherCmd(MnCommonVo mnvo) {
		String uuid = Util.getUUIDstring();
		try {
			SendMsgToMQ producer = SendMsgToMQ.instance();
			TextMessage rptmsg = producer.getSession().createTextMessage();
			// 封装命令为xml格式
			String timestamp = Util.getTimeStamp();
			String cmdname = "";
			// 用以区分MINI与LYB协议
			if (mnvo.getCmd_id().length() == 2) {
				cmdname = this.dectDefineDao.queryDefNamebyId(mnvo.getCmd_id(),
						"21");

				rptmsg.setIntProperty("msgid", 1160);// msgid暂未定
			} else if (mnvo.getCmd_id().length() == 1) {
				cmdname = this.dectDefineDao.queryDefNamebyId(mnvo.getCmd_id(),
						"14");

				rptmsg.setIntProperty("msgid", 1060);// msgid暂未定
			}
			rptmsg.setStringProperty("msgtype", "SWITCH");
			rptmsg.setStringProperty("msgreturn", "FALSE");
			rptmsg.setStringProperty("msgdestination", "COMMAND");
			String strxml = "";
			strxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			strxml += "<root>\n";
			strxml += "<vno>" + mnvo.getVno() + "</vno>\n";
			strxml += "<opt>1</opt>\n";
			strxml += "<userid>" + mnvo.getUser_id() + "</userid>\n";
			strxml += "<cmdid>" + mnvo.getCmd_id() + "</cmdid>\n";
			strxml += "<cmdname>" + cmdname + "</cmdname>\n";
			strxml += "<sendtype>" + "" + "</sendtype>\n";// 暂未定,可不需要
			strxml += "<msguuid>" + uuid + "</msguuid>\n";// 可不需要
			strxml += "<timestamp>" + timestamp + "</timestamp>\n";
			strxml += "<ip>" + "" + "</ip>\n";
			strxml += "<paramcount>0</paramcount>\n";// 可不需要
			strxml += "</root>\n";
			rptmsg.setText(strxml);
			producer.getProducer().send(rptmsg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendCancel(MnCommonVo mnvo) {
		String uuid = Util.getUUIDstring();
		try {
			SendMsgToMQ producer = SendMsgToMQ.instance();
			TextMessage rptmsg = producer.getSession().createTextMessage();
			// 封装命令为xml格式
			String timestamp = Util.getTimeStamp();
			String cmdname = "";
			// 用以区分MINI与LYB协议
			if (mnvo.getCmd_id().split("_").length == 2) {
				cmdname = this.dectDefineDao.queryDefNamebyId("201", "16");

				rptmsg.setIntProperty("msgid", 1160);// msgid暂未定
			} else if (mnvo.getCmd_id().split("_").length == 1) {
				cmdname = this.dectDefineDao.queryDefNamebyId("201",
						"16");

				rptmsg.setIntProperty("msgid", 1060);// msgid暂未定
			}
			rptmsg.setStringProperty("msgtype", "SWITCH");
			rptmsg.setStringProperty("msgreturn", "FALSE");
			rptmsg.setStringProperty("msgdestination", "COMMAND");
			String strxml = "";
			strxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			strxml += "<root>\n";
			strxml += "<vno>" + mnvo.getVno() + "</vno>\n";
			strxml += "<opt>0</opt>\n";
			strxml += "<userid>" + mnvo.getUser_id() + "</userid>\n";
			// strxml += "<cmdid>" + mnvo.getcCmd_id() + "</cmdid>\n";
			// strxml += "<cancel_cmdid>" + mnvo.getCancel_cmdid() +
			// "</cancel_cmdid>\n";//取消的发送的cmdid
			strxml += "<cmdid>" + mnvo.getCancel_cmdid() + "</cmdid>\n";
			strxml += "<cmdname>" + cmdname + "</cmdname>\n";
			strxml += "<sendtype>" + "" + "</sendtype>\n";// 暂未定,可不需要
			strxml += "<msguuid>" + uuid + "</msguuid>\n";// 可不需要
			strxml += "<timestamp>" + timestamp + "</timestamp>\n";
			strxml += "<ip>" + "" + "</ip>\n";
			strxml += "<paramcount>0</paramcount>\n";// 可不需要
			strxml += "</root>\n";
			rptmsg.setText(strxml);
			producer.getProducer().send(rptmsg);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSettingCmd(MnCommonVo mnvo) {
		String uuid = Util.getUUIDstring();
		try {
			SendMsgToMQ producer = SendMsgToMQ.instance();
			TextMessage rptmsg = producer.getSession().createTextMessage();
			// 封装命令为xml格式
			String timestamp = Util.getTimeStamp();
			String cmdname = this.dectDefineDao.queryDefNamebyId(
					mnvo.getCmd_id(), "15");
			rptmsg.setIntProperty("msgid", 1060);// msgid暂未定
			rptmsg.setStringProperty("msgtype", "SWITCH");
			rptmsg.setStringProperty("msgreturn", "FALSE");
			rptmsg.setStringProperty("msgdestination", "COMMAND");
			String strxml = "";
			strxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			strxml += "<root>\n";
			strxml += "<vno>" + mnvo.getVno() + "</vno>\n";
			strxml += "<opt>2</opt>\n";
			strxml += "<userid>" + mnvo.getUser_id() + "</userid>\n";
			strxml += "<cmdid>" + mnvo.getCmd_id() + "</cmdid>\n";
			strxml += "<cmdname>" + cmdname + "</cmdname>\n";
			strxml += "<sendtype>" + "GPRS" + "</sendtype>\n";// 暂未定,可不需要
			strxml += "<msguuid>" + uuid + "</msguuid>\n";// 可不需要
			strxml += "<timestamp>" + timestamp + "</timestamp>\n";
			// 加入IP
			strxml += "<ip>" + mnvo.getIp() + "</ip>\n";
			// 命令详细信息
			strxml += "<cmdDetail>" + mnvo.getSettingCmdDetail()
					+ "</cmdDetail>\n";

			strxml += convertVoToXml(mnvo);
			strxml += "</root>\n";
			rptmsg.setText(strxml);
			producer.getProducer().send(rptmsg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSettingLYBCmd(MnCommonVo mnvo,int type) {
		String uuid = Util.getUUIDstring();
		try {
			SendMsgToMQ producer = SendMsgToMQ.instance();
			TextMessage rptmsg = producer.getSession().createTextMessage();
			// 封装命令为xml格式
			String timestamp = Util.getTimeStamp();
			String cmdname;
			if(type == 7) {
				String cmdid = mnvo.getCmd_id().equals("303")?"33":"34";
				cmdname = this.dectDefineDao.queryDefNamebyId(cmdid, "21");
			}else {
				cmdname = this.dectDefineDao.queryDefNamebyId(mnvo.getCmd_id(), "20");
			}
			rptmsg.setIntProperty("msgid", 1160);// msgid暂未定
			rptmsg.setStringProperty("msgtype", "SWITCH");
			rptmsg.setStringProperty("msgreturn", "FALSE");
			rptmsg.setStringProperty("msgdestination", "COMMAND");
			String strxml = "";
			strxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			strxml += "<root>\n";
			strxml += "<vno>" + mnvo.getVno() + "</vno>\n";
			strxml += "<opt>2</opt>\n";
			strxml += "<userid>" + mnvo.getUser_id() + "</userid>\n";
			strxml += "<cmdid>" + mnvo.getCmd_id() + "</cmdid>\n";
			strxml += "<cmdname>" + cmdname + "</cmdname>\n";
			strxml += "<sendtype>" + "GPRS" + "</sendtype>\n";// 暂未定,可不需要
			strxml += "<msguuid>" + uuid + "</msguuid>\n";// 可不需要
			strxml += "<timestamp>" + timestamp + "</timestamp>\n";
			// 加入IP
			strxml += "<ip>" + mnvo.getIp() + "</ip>\n";
			// 命令详细信息
			strxml += "<cmdDetail>" + mnvo.getSettingCmdDetail()
					+ "</cmdDetail>\n";

			strxml += convertVoToXml(mnvo);
			strxml += "</root>\n";
			rptmsg.setText(strxml);
			producer.getProducer().send(rptmsg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private String convertVoToXml(MnCommonVo mnvo) {
		String info = "";
		String cmd = mnvo.getCmd_id();
		switch (Integer.parseInt(cmd)) {
		case 101:
			info += "<paramcount>2</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='ipaddress'>" + mnvo.getIpaddress()
					+ "</item>\n";
			info += "<item name='port'>" + mnvo.getPort() + "</item>\n";
			info += "</paramitem>\n";
			break;
		case 102:
			info += "<paramcount>2</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='up_time_normal'>" + mnvo.getUp_time_normal()
					+ "</item>\n";
			info += "<item name='up_time_urgent'>" + mnvo.getUp_time_urgent()
					+ "</item>\n";
			info += "</paramitem>\n";
			break;
		case 103:
			if ("1".equals(mnvo.getDevterm_state())) {
				info += "<paramcount>2</paramcount>\n";
				info += "<paramitem>\n";
				info += "<item name='devterm_state'>" + mnvo.getDevterm_state()
						+ "</item>\n";
				info += "<item name='duration'>" + mnvo.getDuration()
						+ "</item>\n";
				info += "</paramitem>\n";
			} else {
				info += "<paramcount>1</paramcount>\n";
				info += "<paramitem>\n";
				info += "<item name='devterm_state'>0</item>\n";
				info += "</paramitem>\n";
			}
			break;
		case 105:
			info += "<paramcount>3</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='week'>" + mnvo.getWeek() + "</item>\n";
			info += "<item name='hour'>" + mnvo.getHour() + "</item>\n";
			info += "<item name='minute'>" + mnvo.getMinute() + "</item>\n";
			info += "</paramitem>\n";
			break;
		case 303:
			info += "<paramcount>5</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='upinterval'>" + mnvo.getUpinterval()
					+ "</item>\n";
			info += "<item name='starthour'>" + mnvo.getStarthour()
					+ "</item>\n";
			info += "<item name='startminute'>" + mnvo.getStartminute()
					+ "</item>\n";
			info += "<item name='residue'>" + mnvo.getResidue() + "</item>\n";
			info += "<item name='emergencyState'>" + mnvo.getEmergencyState()
					+ "</item>\n";
			info += "</paramitem>\n";
			break;
		case 304:
			info += "<paramcount>4</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='day'>" + mnvo.getCycDay() + "</item>\n";
			info += "<item name='everydayUpNum'>" + mnvo.getEverydayUpNum()
					+ "</item>\n";
			info += "<item name='hour'>" + mnvo.getHour() + "</item>\n";
			info += "<item name='minute'>" + mnvo.getMinute() + "</item>\n";
			info += "</paramitem>\n";
			break;
		case 305:
			info += "<paramcount>5</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='ipaddress0'>"
					+ mnvo.getIpaddress().split("-")[0] + "</item>\n";
			info += "<item name='ipaddress1'>"
					+ mnvo.getIpaddress().split("-")[1] + "</item>\n";
			info += "<item name='ipaddress2'>"
					+ mnvo.getIpaddress().split("-")[2] + "</item>\n";
			info += "<item name='ipaddress3'>"
					+ mnvo.getIpaddress().split("-")[3] + "</item>\n";
			info += "<item name='port'>" + mnvo.getPort() + "</item>\n";
			info += "</paramitem>\n";
			break;
		case 306:
			info += "<paramcount>1</paramcount>\n";
			info += "<paramitem>\n";
			info += "<item name='message'>" + mnvo.getMessage() + "</item>\n";
			info += "</paramitem>\n";
			break;
		}
		return info;
	}

	@Override
	public int findDeviceType(String vno) {
		return this.mnDao.findDeviceType(vno);
	}
}
