package com.jsd.web.login.service.impl;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.login.dao.LoginHistoryDAO;
import com.jsd.web.login.dao.UserDAO;
import com.jsd.web.login.service.UserService;
import com.jsd.web.po.TloginHistory;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TuserOther;
import com.jsd.web.po.UserMac;

public class UserServiceImpl implements UserService {

	private UserDAO userDao;
	private LoginHistoryDAO loginHistoryDao;

	/*
	 * 查询用户表，判断标记位，比较mac地址 (non-Javadoc)
	 *
	 * @see
	 * com.jsd.web.login.service.UserService#queryUserInfo(com.jsd.web.po.Tuser)
	 */
	@Override
	public Tuser queryUserInfo(Tuser user, String mac, String cpuNum) {
		Tuser rstUser = userDao.queryUserInfo(user);

		if (rstUser == null)
			return null;
		if (rstUser.getUserFlag() == null)
			return rstUser;
		if (rstUser.getUserFlag().equals("0"))
			return rstUser;

		List<UserMac> um = userDao.queryUserMac(rstUser);
		if (um.size() == 0) {
			if (!mac.equals("") && !cpuNum.equals("")) {
				userDao.insertUserMac(mac, rstUser.getUserId(), cpuNum);
			} else {
				Tuser newUser = new Tuser();
				newUser.setUserFlag("tashikong");
				rstUser = newUser;
			}

			return rstUser;
		}

		if (um.get(0).getMac().equals(mac))
			return rstUser;

		if (um.get(0).getMac() != null && !um.get(0).getMac().equals("")) {
			Tuser newUser = new Tuser();
			newUser.setUserFlag("user no login");
			return newUser;
		}

		// 错误安全 mac也不匹配
		// 把获取的mac地址插入到user_mac表中
		userDao.insertUserMac(mac, rstUser.getUserId(), cpuNum);

		return rstUser;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryUserLoginHistory(String userCode, String today) {
		List rstList = loginHistoryDao.queryLoginHistory(userCode, today);
		return rstList;
	}

	public LoginHistoryDAO getLoginHistoryDao() {
		return loginHistoryDao;
	}

	public void setLoginHistoryDao(LoginHistoryDAO loginHistoryDao) {
		this.loginHistoryDao = loginHistoryDao;
	}

	@Override
	public int addLoginHistory(TloginHistory tloginHistory) {
		int flag = loginHistoryDao.addLoginHistory(tloginHistory);
		return flag;
	}

	@Override
	public int updateUserInfo(Tuser user) {
		return userDao.updateUserInfo(user);
	}

	@Override
	public Tuser queryUserInfo(String userCode) {
		Tuser rst = userDao.queryUserInfoByCode(userCode);
		return rst;
	}

	@Override
	public List<String> getPrivileges(String privilegeValue) {
		return userDao.getPrivileges(privilegeValue);
	}

	@Override
	public String queryLogo(String userId) {
		String logoPath = null;
		String filepath = ServletActionContext.getServletContext().getRealPath(
				"/upload/" + userId + "/buffer/");
		File file = new File(filepath);
		if (!file.isDirectory()) {
			logoPath = "/web/images/jsd.png";
		} else {
			String[] s = file.list();
			for (int i = 0; i < s.length; i++) {
				logoPath = "/upload/" + userId + "/buffer/" + s[i];
			}
		}
		return logoPath;
	}

	@Override
	public TuserOther findOtherUserInfo(Integer userId) {

		return userDao.findOtherUserInfo(userId);
	}
}
