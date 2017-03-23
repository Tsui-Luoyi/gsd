package com.jsd.web.login.dao;

import java.util.List;

import com.jsd.web.po.Tuser;
import com.jsd.web.po.TuserOther;
import com.jsd.web.po.UserMac;

public interface UserDAO {

	/**
	 * 根据用户信息查询数据库是否存在该用户
	 * @return User用户VO
	 */
	public Tuser queryUserInfo(Tuser user);

	/**
	 * 根据用户登录Code获取详细信息
	 * @param userCode
	 * @return
	 */
	public Tuser queryUserInfoByCode(String userCode);
	/**
	 * 更新用户信息表
	 * @param user
	 * @return
	 */
	public int updateUserInfo(Tuser user);

	public List<String> getPrivileges(String privilegeValue);

	public List<UserMac> queryUserMac(Tuser rstUser);
	/**
	 * 插入获取的Mac地址
	 * @param integer
	 * @return
	 */
	public void insertUserMac(String mac, Integer integer, String cpuNum);
	/**
	 * 更新user_mac表
	 * @param mac
	 * @param uid
	 * @param cpuNum
	 */
	public void updateUserMac(String mac, Integer uid, String cpuNum);

	/**
	 * 查询其他厂家设备平台账户信息
	 * @param userId
	 * @return
	 */
	public TuserOther findOtherUserInfo(Integer userId);

}
