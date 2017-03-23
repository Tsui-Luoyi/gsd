package com.jsd.web.vehicle.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.po.DevtermInfo;
import com.jsd.web.po.TvehicleInfoRecord007;
import com.jsd.web.vehicle.service.VehicleRecordService;
import com.jsd.web.vehicle.vo.OtherInfoVo;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class VehicleRecordAction007 extends ActionSupport {
	private String vno;
	private String vname;
	private String engineNo;
	private VehicleRecordService vehicleRecordService;
	private TvehicleInfoRecord007 findVehicleRecord007 = new TvehicleInfoRecord007();
	private DevtermInfo devtermInfo;
	private OtherInfoVo OtherInfoVo;

	public OtherInfoVo getOtherInfoVo() {
		return OtherInfoVo;
	}

	public void setOtherInfoVo(OtherInfoVo otherInfoVo) {
		OtherInfoVo = otherInfoVo;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public VehicleRecordService getVehicleRecordService() {
		return vehicleRecordService;
	}

	public void setVehicleRecordService(
			VehicleRecordService vehicleRecordService) {
		this.vehicleRecordService = vehicleRecordService;
	}

	public TvehicleInfoRecord007 getFindVehicleRecord007() {
		return findVehicleRecord007;
	}

	public void setFindVehicleRecord007(
			TvehicleInfoRecord007 findVehicleRecord007) {
		this.findVehicleRecord007 = findVehicleRecord007;
	}

	public DevtermInfo getDevtermInfo() {
		return devtermInfo;
	}

	public void setDevtermInfo(DevtermInfo devtermInfo) {
		this.devtermInfo = devtermInfo;
	}

	public String findVehicleRecord007() {
		findVehicleRecord007 = vehicleRecordService.findVehicleRecord007(vno);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (findVehicleRecord007.getVname() == null) {
			request.setAttribute("vname", "");
		} else {
			request.setAttribute("vname", findVehicleRecord007.getVname());
		}
		if (findVehicleRecord007.getEngineNo() == null) {
			request.setAttribute("engineNo", "");
		} else {
			request.setAttribute("engineNo", findVehicleRecord007.getEngineNo());
		}
		if (findVehicleRecord007.getVno() == null) {
			request.setAttribute("vno", "");
		} else {
			request.setAttribute("vno", findVehicleRecord007.getVno());
		}
		return "Success";
	}

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String modifyEngineNo007() {// 修改整机名和发动机号
		boolean flag = vehicleRecordService.modifyEngineNo007(vno, vname,
				engineNo);
		// 1.判断上步操作是否成功
		if (flag) {
			msg = "SUCCESS";
		} else {
			msg = "FAIL";
		}
		return "SUCCESS";
	}

	public String findDevtermRecord007() {
		devtermInfo = vehicleRecordService.findDevtermRecord007(vno);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (devtermInfo.getDno() == null) {
			request.setAttribute("dno", "");
		} else {
			request.setAttribute("dno", devtermInfo.getDno());
		}
		if (devtermInfo.getManufacturer() == null) {
			request.setAttribute("manufacturer", "");
		} else {
			request.setAttribute("manufacturer", devtermInfo.getManufacturer());
		}
		if (devtermInfo.getSimnumber() == null) {
			request.setAttribute("simnumber", "");
		} else {
			request.setAttribute("simnumber", devtermInfo.getSimnumber());
		}
		return "success";
	}

	public String findOtherRecord007() {
		OtherInfoVo = vehicleRecordService.findOtherRecord007(vno);
		return "success";
	}
}