package com.jsd.web.vehicle.service.impl;

import java.util.List;

import com.jsd.web.authority.dao.AuUserDAO;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.TvOrgRel;
import com.jsd.web.vehicle.dao.TerminalRenewDao;
import com.jsd.web.vehicle.dao.VehicleDAO;
import com.jsd.web.vehicle.service.TerminalRenewService;
import com.jsd.web.vehicle.vo.TerminalRenewVO;

public class TerminalRenewServiceImpl implements TerminalRenewService {
	private TerminalRenewDao terminalRenewDao;
	private VehicleDAO vehicleDao;
	private AuUserDAO auUserDAO;

	public void setTerminalRenewDao(TerminalRenewDao terminalRenewDao) {
		this.terminalRenewDao = terminalRenewDao;
	}

	public void setAuUserDAO(AuUserDAO auUserDAO) {
		this.auUserDAO = auUserDAO;
	}

	public void setVehicleDao(VehicleDAO vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<TerminalRenewVO> findTerminalRenew(TerminalRenewVO vo,
			UserVO uservo) {
		/* 根据用户获取其所能查看的整机 */
		String vehicleIds = "''";
		if ("代理商用户".equals(uservo.getRoleName())) {// 判断是否为代理商
			String vidofagent = this.vehicleDao.queryVidOfAgent(uservo);
			vehicleIds = vehicleIds + "," + vidofagent;
		} else {
			List orgVehiList = auUserDAO.queryVehicleOrgRelListByUser(uservo
					.getUserId().toString());
			if (orgVehiList != null && orgVehiList.size() > 0) {
				for (int i = 0; i < orgVehiList.size(); i++) {
					TvOrgRel orgrel = (TvOrgRel) orgVehiList.get(i);
					String ivehicleId = orgrel.getVno();
					vehicleIds = vehicleIds + "," + ivehicleId;
				}
			}
		}

		return this.terminalRenewDao.findTerminalRenew(vo, vehicleIds);
	}

	@Override
	public void modifyTerminalRenew(String[] renewinfos) {
		String updateSimnum = "update devterm_info di set di.simnumber = '" +
										renewinfos[1] + "' where di.dno = '" + renewinfos[0] + "'";
		String selectDno = "select dno from devterm_renew dr where dr.dno = '" + renewinfos[0] + "'";
		String insertRenewInfo = "insert into devterm_renew values ('" + renewinfos[0] + "','" +
										renewinfos[2] + "','" + renewinfos[3] + "')";
		String updateRenewInfo = "update devterm_renew dr set dr.renewdate = '" + renewinfos[2] +
										"',dr.renewperiod = '" + renewinfos[3] + "' where dr.dno = '" + renewinfos[0] + "'";

		String sqls[] = {updateSimnum, selectDno, insertRenewInfo, updateRenewInfo};

		this.terminalRenewDao.modifyTerminalRenew(sqls);
	}

}
