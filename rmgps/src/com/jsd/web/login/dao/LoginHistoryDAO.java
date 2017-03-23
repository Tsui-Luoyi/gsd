package com.jsd.web.login.dao;

import java.util.List;

import com.jsd.web.po.TloginHistory;

public interface LoginHistoryDAO {

	/**
	 * 根据用户登录历史
	 * @param usercode 用户登录ID
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List queryLoginHistory(String userCode,String today);

	/**
	 * 增加人员的登录系统历史记录
	 * @param loginHistory
	 * @return int
	 */
	public int addLoginHistory(TloginHistory loginHistory);

}
