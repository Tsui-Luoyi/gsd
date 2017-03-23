package com.jsd.test.map;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jsd.web.common.DictVo;
import com.jsd.web.common.PageInfo;
import com.jsd.web.vehicle.service.MnService;
import com.jsd.web.vehicle.vo.MnCommonVo;
import com.jsd.web.vehicle.vo.VehicleRecordVO;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("rawtypes")
public class testMnAction extends ActionSupport {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private MnService mnService;

	private VehicleRecordVO vehicleRecordVO;
	private String vid;
	private String vname;
	private String vno;
	private String vmid;
	private String engineno;
	private MnCommonVo vo;
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

	private String map;

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}


	/*
	 * 第三方地图商最新数据执行方法
	 */
	public String testFindBaiduCurrentInfo() {
		String ret;
		this.vo = this.mnService.findCurrentInfo(vno);
		int mapId = Integer.parseInt(this.map);
		switch(mapId){
		case 1:
			ret = "baiduSuccess";
			break;
		case 2:
			ret = "googleSuccess";
			break;
		case 3:
			ret = "gaodeSuccess";
			break;
		default:
			ret = "baiduSuccess";
			break;
		}

		return ret;
	}


	// ************--getters and setters--*************

	public void setMnService(MnService mnService) {
		this.mnService = mnService;
	}

	public MnService getMnService() {
		return mnService;
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

}
