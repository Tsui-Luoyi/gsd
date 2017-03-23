package com.jsd.web.vehicle.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.common.DictVo;
import com.jsd.web.common.PageInfo;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.map.service.MapDataService;
import com.jsd.web.po.Mn_login_history;
import com.jsd.web.vehicle.service.MnService;
import com.jsd.web.vehicle.vo.MnCommonVo;
import com.jsd.web.vehicle.vo.VehicleRecordVO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
public class MnAction extends ActionSupport {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MnService mnService;
	private MapDataService vehicleMapService;
	private VehicleRecordVO vehicleRecordVO;
	private String vid;
	private String vname;
	private String vno;
	private String vmid;
	private String engineno;
	private MnCommonVo vo;
	private MnCommonVo get;
	private DictVo dvo;
	private String cmd_name;
	private String cmd_type;
	private String start_time;
	private String end_time;
	private PageInfo pagination;
	private String url;
	private String ifHit;
	private List cmdHistoryList;
	private List loginHistoryList;
	private List gpsHistoryList;
	private List cmdQueueList;
	private String jquerybackdata;// 异步返回值
	private String cmdid;
	private String cancel_cmdid;
	private String jsonstr;
	private Double lng;
	private Double lat;
	/*
	 * 最新数据执行这个方法
	 */
	public String findCurrentInfo() {
		this.vo = this.mnService.findCurrentInfo(vno);
		return "success";
	}

	/*
	 * 采集设置
	 */
	public String findCmdInfo() {
		// TODO 新设备方法
		if(this.mnService.findDeviceType(this.vno) == 6) {
			this.vo = this.mnService.findCmdInfo1(this.vno);// 采集设置初始化数据(已返回的采集设置结果)
			this.get = this.mnService.findCmdInfo2(this.vno);// 采集设置初始化数据(已返回的采集设置结果)
			this.cmdQueueList = this.mnService.findCmdQueueInfo(this.vno);// 采集设置命令队列(发送但未收到结果的命令)
			return "LYBSUCCESS";
		}else if(this.mnService.findDeviceType(this.vno) == 7){
			this.vo = this.mnService.findCmdInfo1(this.vno);// 采集设置初始化数据(已返回的采集设置结果)
			this.get = this.mnService.findCmdInfo2(this.vno);// 采集设置初始化数据(已返回的采集设置结果)
			this.cmdQueueList = this.mnService.findCmdQueueInfo(this.vno);// 采集设置命令队列(发送但未收到结果的命令)
			return "LYBVSUCCESS";
		}else{
			this.vo = this.mnService.findCmdInfo1(this.vno);// 采集设置初始化数据(已返回的采集设置结果)
			this.cmdQueueList = this.mnService.findCmdQueueInfo(this.vno);// 采集设置命令队列(发送但未收到结果的命令)
			return "success";
		}
	}

	/*
	 * 发送采集命令
	 */
	public String sendGatherCmd() {
		//TODO 采集
		UserVO tuser = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		MnCommonVo mnvo = new MnCommonVo();
		mnvo.setUser_id(tuser.getUserId().toString());
		mnvo.setCmd_id(this.cmdid);
		mnvo.setVno(this.vno);
		this.mnService.sendGatherCmd(mnvo);
		this.jquerybackdata = "";
		return "success";
	}

	/*
	 * 发送取消命令
	 */
	public String sendCancelCmd() {
		//TODO 取消
		UserVO tuser = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		MnCommonVo mnvo = new MnCommonVo();
		mnvo.setUser_id(tuser.getUserId().toString());
		mnvo.setCmd_id(this.cmdid);//页面cmdid   与cancelID的值传相反,为了兼容,亦反之
		mnvo.setVno(this.vno);
		mnvo.setCancel_cmdid(this.cancel_cmdid);//页面cmdid   与cancelID的值传相反,为了兼容,亦反之
		this.mnService.sendCancel(mnvo);
		this.jquerybackdata = "";
		return "success";
	}

	/*
	 * 发送设置命令
	 */
	@SuppressWarnings("static-access")
	public String sendSettingCmd() {
		//TODO 设置
		if (this.jsonstr != null && !"".equals(this.jsonstr)) {
			HttpServletRequest request = ServletActionContext.getRequest();
			UserVO tuser = (UserVO) request.getSession().getAttribute("USER");
			JSONObject js = JSONObject.fromObject(jsonstr);// 根据字符串转换对象
			MnCommonVo mnvo = (MnCommonVo) js.toBean(js, MnCommonVo.class);
			mnvo.setUser_id(tuser.getUserId().toString());
			mnvo.setCmd_id(this.cmdid);
			mnvo.setVno(this.vno);
			// 加入IP
			mnvo.setIp(getIpString(request));
			// 设置命令详细信息
			int type = this.mnService.findDeviceType(this.vno);
			String settingCmdDetail = getSettingCmdDetail(mnvo,type);
			mnvo.setSettingCmdDetail(settingCmdDetail);
			if(Integer.parseInt(cmdid) > 300) {
				this.mnService.sendSettingLYBCmd(mnvo,type);
			}else {
				this.mnService.sendSettingCmd(mnvo);
			}
		}

		this.jquerybackdata = "";
		return "success";
	}

	private String getIpString(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	private String getSettingCmdDetail(MnCommonVo mnvo,int type) {
		String info = "";
		String cmd = mnvo.getCmd_id();
		switch (Integer.parseInt(cmd)) {
		case 101:
			info += "IP地址:" + mnvo.getIpaddress() + "，端口号：" + mnvo.getPort()
					+ " ";
			break;
		case 102:
			info += "上传时间间隔，正常：" + mnvo.getUp_time_normal() + " 紧急："
					+ mnvo.getUp_time_urgent() + " ";
			break;
		case 103:
			if ("0".equals(mnvo.getDevterm_state())) {
				info += "终端状态，正常：0 ";
			} else {
				info += "终端状态，紧急：1 持续时间：" + mnvo.getDuration() + "小时 ";
			}
			break;
		case 105:
			info += "预定上传时间:" + mnvo.getWeek() + "，" + mnvo.getHour() + "时，"
					+ mnvo.getMinute() + "分 ";
			break;
		case 303:
			if(type == 7) {
				info += "模式选择:"+mnvo.getEmergencyState()+" 间隔:"+ mnvo.getUpinterval() +",起始小时:" + mnvo.getStarthour()+",起始分钟:" +mnvo.getStartminute()+"剩余次数:"+mnvo.getResidue();
				break;
			}else {
				info += "模式选择:"+mnvo.getEmergencyState()+" 间隔:"+ mnvo.getUpinterval() +",起始小时:" + mnvo.getStarthour()+",起始分钟:" +mnvo.getStartminute()+"剩余次数:"+mnvo.getResidue();
				break;
			}
		case 304:
			if(type == 7) {
				info += "参数设置:" +mnvo.getCycDay() + "天," + mnvo.getEverydayUpNum() + "次," + mnvo.getHour() + "时，"
						+ mnvo.getMinute() + "分 ";
				break;
			}else {
				info += "参数设置:" + mnvo.getEverydayUpNum() + "次," + mnvo.getHour() + "时，"
						+ mnvo.getMinute() + "分 ";
				break;
			}
		case 305:
			info += "IP地址:" + mnvo.getIpaddress() + "，端口号：" + mnvo.getPort();
			break;
		}

		return info;
	}

	/*
	 * 命令日志
	 */
	public String findCmdHistoryInfo() {
		if (ifHit != null) {// 分页
			if (pagination == null) {
				pagination = new PageInfo();
			}
			if (pagination.getCurrentPage() <= 0) {
				pagination.setCurrentPage(1);
			}
			if (pagination.getTotalPages() != 0
					&& pagination.getCurrentPage() > pagination.getTotalPages()) {
				pagination.setCurrentPage(pagination.getTotalPages());
			}
		} else {// 定位到第一页
			if (pagination == null) {
				pagination = new PageInfo();
			}
			pagination.setCurrentPage(1);
		}
		this.ifHit = null;
		this.url = "mnCmdHistoryAction.action?vno=" + vno;// 分页时的url

		MnCommonVo vo = new MnCommonVo();
		vo.setVno(vno);
		vo.setCmd_name(cmd_name);
		vo.setCmd_type(cmd_type);
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		vo.setUser_id(uservo.getUserId().toString());
		this.cmdHistoryList = this.mnService.findCmdHistoryInfo(vo, pagination);

		return "success";
	}

	@SuppressWarnings("unchecked")
	/**
	 * 百度地图获取轨迹数据
	 * 数据库表Mn_login_history
	 *
	 * @return
	 */
	public String findTrackBaiduHistoryInfo() {
		boolean firstFlag=false;
		JSONArray jsonMutiInfo = null;
		List<Mn_login_history> gpsLocateHisList = this.vehicleMapService.getGpsLocateTrack(
				this.vno, this.start_time, this.end_time);
		List mutiInfo4Json = new ArrayList();
		for (Mn_login_history bean : gpsLocateHisList) {
			if(firstFlag == false){
				firstFlag=true;
				//设置第一个点为中心
				lng=Double.valueOf(bean.getB_Longitude());
				lat=Double.valueOf(bean.getB_Latitude());
			}
			//放入bean剔除无用数据。
			List l=bean2mutiInfo(bean);
			mutiInfo4Json.add(l);
		}
		//循环结束对全部地址进行json化处理
		jsonMutiInfo = JSONArray.fromObject(mutiInfo4Json);
		ServletActionContext.getRequest().setAttribute("jsonMutiInfo", jsonMutiInfo);
		return "baiduSuccess";
	}

	/**
	 *
	 * baidu多点详细信息json方法
	 */
	@SuppressWarnings("unchecked")
	public List bean2mutiInfo(Mn_login_history bean){
		List listInlist = new ArrayList();
		listInlist.add(Double.valueOf(bean.getB_Longitude()));
		listInlist.add(Double.valueOf(bean.getB_Latitude()));
		String str = "时间：" + bean.getLogin_time() + "</br>";

		if (bean.getLocate_flag() == null) {
			str += "定位方式：</br>";
		} else {
			if (bean.getLocate_flag().equals("A")) {
				str += "定位方式：卫星定位。</br>";
			} else if (bean.getLocate_flag().equals("S")) {
				str += "定位方式：基站定位。</br>";
			} else {
				str += "定位方式：其他。</br>";
			}
		}
		if (bean.getAddr() == null) {
			str += "地址：</br>";
		} else {
			str += "地址：" + bean.getAddr() + "</br>";
		}
		listInlist.add(str);

		return listInlist;
	}

	/*
	 * 登录日志
	 */
	public String findLoginHistoryInfo() {
		if (ifHit != null) {// 分页
			if (pagination == null) {
				pagination = new PageInfo();
			}
			if (pagination.getCurrentPage() <= 0) {
				pagination.setCurrentPage(1);
			}
			if (pagination.getTotalPages() != 0
					&& pagination.getCurrentPage() > pagination.getTotalPages()) {
				pagination.setCurrentPage(pagination.getTotalPages());
			}
		} else {// 定位到第一页
			if (pagination == null) {
				pagination = new PageInfo();
			}
			pagination.setCurrentPage(1);
		}
		this.ifHit = null;
		this.url = "mnLoginHistoryAction.action?vno=" + vno;// 分页时的url

		MnCommonVo vo = new MnCommonVo();
		vo.setVno(vno);
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		this.loginHistoryList = this.mnService.findLoginHistoryInfo(vo,
				pagination);

		return "success";
	}

	// ************--getters and setters--*************

	public void setMnService(MnService mnService) {
		this.mnService = mnService;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		try {
			this.vno = java.net.URLDecoder.decode(vno, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public MnCommonVo getVo() {
		return vo;
	}

	public void setVo(MnCommonVo vo) {
		this.vo = vo;
	}

	public String getCmd_name() {
		return cmd_name;
	}

	public void setCmd_name(String cmd_name) {
		this.cmd_name = cmd_name;
	}

	public String getCmd_type() {
		return cmd_type;
	}

	public void setCmd_type(String cmd_type) {
		this.cmd_type = cmd_type;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public PageInfo getPagination() {
		return pagination;
	}

	public void setPagination(PageInfo pagination) {
		this.pagination = pagination;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIfHit() {
		return ifHit;
	}

	public void setIfHit(String ifHit) {
		this.ifHit = ifHit;
	}

	public List getCmdHistoryList() {
		return cmdHistoryList;
	}

	public void setCmdHistoryList(List cmdHistoryList) {
		this.cmdHistoryList = cmdHistoryList;
	}

	public List getLoginHistoryList() {
		return loginHistoryList;
	}

	public void setLoginHistoryList(List loginHistoryList) {
		this.loginHistoryList = loginHistoryList;
	}

	public List getGpsHistoryList() {
		return gpsHistoryList;
	}

	public void setGpsHistoryList(List gpsHistoryList) {
		this.gpsHistoryList = gpsHistoryList;
	}

	public void setVehicleMapService(MapDataService vehicleMapService) {
		this.vehicleMapService = vehicleMapService;
	}

	public List getCmdQueueList() {
		return cmdQueueList;
	}

	public void setCmdQueueList(List cmdQueueList) {
		this.cmdQueueList = cmdQueueList;
	}

	public String getJquerybackdata() {
		return jquerybackdata;
	}

	public void setJquerybackdata(String jquerybackdata) {
		this.jquerybackdata = jquerybackdata;
	}

	public String getCmdid() {
		return cmdid;
	}

	public void setCmdid(String cmdid) {
		this.cmdid = cmdid;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public String getCancel_cmdid() {
		return cancel_cmdid;
	}

	public void setCancel_cmdid(String cancel_cmdid) {
		this.cancel_cmdid = cancel_cmdid;
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}

	public VehicleRecordVO getVehicleRecordVO() {
		return vehicleRecordVO;
	}

	public void setVehicleRecordVO(VehicleRecordVO vehicleRecordVO) {
		this.vehicleRecordVO = vehicleRecordVO;
	}

	public DictVo getDvo() {
		return dvo;
	}

	public void setDvo(DictVo dvo) {
		this.dvo = dvo;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getEngineno() {
		return engineno;
	}

	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}

	public MnCommonVo getGet() {
		return get;
	}

	public void setGet(MnCommonVo get) {
		this.get = get;
	}
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
}
