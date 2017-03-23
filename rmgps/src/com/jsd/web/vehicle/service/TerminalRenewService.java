package com.jsd.web.vehicle.service;

import java.util.List;

import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.vo.TerminalRenewVO;

public interface TerminalRenewService {

	List<TerminalRenewVO> findTerminalRenew(TerminalRenewVO vo, UserVO uservo);

	void modifyTerminalRenew(String[] renewinfos);

}
