package com.jsd.web.vehicle.dao;

import java.util.List;

import com.jsd.web.vehicle.vo.TerminalRenewVO;

public interface TerminalRenewDao {

	List<TerminalRenewVO> findTerminalRenew(TerminalRenewVO vo, String vehicleIds);

	void modifyTerminalRenew(String[] sqls);

}
