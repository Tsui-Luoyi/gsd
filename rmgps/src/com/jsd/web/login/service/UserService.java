package com.jsd.web.login.service;

import java.util.List;

import com.jsd.web.po.TloginHistory;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TuserOther;


public interface UserService {

	/**
	 * 根据用户信息查询数据库是否存在该用户
	 * @param mac
	 * @return User用户VO
	 */
	public Tuser queryUserInfo(Tuser user, String mac, String cpuNum);
	public String queryLogo(String userId);

	/**
	 * 根据用户登录Code获取用户相信信息
	 * @param userCode
	 * @return
	 */
	public Tuser queryUserInfo(String userCode);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(Tuser user);

	/**
	 * 查询用户登录历史信息
	 * @param userCode
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryUserLoginHistory(String userCode,String today);

	/**
	 * 新增人员登录历史
	 * @param tloginHistory
	 * @return
	 */
	public int addLoginHistory(TloginHistory tloginHistory);

	public List<String> getPrivileges(String privilegeValue);

	public TuserOther findOtherUserInfo(Integer userId);



}
