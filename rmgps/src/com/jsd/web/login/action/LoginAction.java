/**
 *
 */
package com.jsd.web.login.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.login.service.UserService;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.TloginHistory;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TuserOther;
import com.jsd.web.po.TuserRole;
import com.jsd.web.po.UserPrivilege;
import com.jsd.web.util.MD5Util;
import com.jsd.web.util.VbrowseHistoryCache;
import com.jsd.web.vehicle.service.VehicleService;
import com.jsd.web.vehicle.vo.AgentVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录验证类
 *
 * @author cuiluepng
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {

	private String userId;
	private String userPwd;
	private String userCode;
	private String newtuserpwd;
	private String licence;
	public String mac;
	public String cpuNum;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}



	private UserService userService1;
	private VehicleService vehicleService;

	private String pathSesseion;

	/**
	 * 登录验证方法
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public String loginValidate() {
		// 如果session存在用户，则证明用户已经登录
		UserVO loginUser = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		if (loginUser != null)
			return "success";

		Tuser tuser = new Tuser();
		tuser.setUserCode(this.userId);
		tuser.setUserPassword(this.userPwd);
		String checkImg = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkImg");
		ServletActionContext.getRequest().getSession().removeAttribute(
				"checkImg");// 请求验证码

		if(this.licence==null){
			return "loginerror";
		}
		if (this.licence!=null && this.licence.equals(checkImg)) {
			ServletActionContext.getRequest().setAttribute("flag", "2");
			ServletActionContext.getRequest().setAttribute("idError",
					"验证码有误，请重新输入！");
			ServletActionContext.getRequest().setAttribute("userId", userId);
			return "loginerror";
		}

		tuser = userService1.queryUserInfo(tuser,mac,cpuNum);
		if (tuser != null && !tuser.getUserFlag().equals("user no login") && !tuser.getUserFlag().equals("tashikong")) {
			UserVO uservo = new UserVO();
			uservo.setUserId(tuser.getUserId());
			uservo.setUserCode(tuser.getUserCode());
			uservo.setUserName(tuser.getUserName());
			uservo.setUserPassword(tuser.getUserPassword());
			String logoPath=userService1.queryLogo(tuser.getUserId()+"");
			uservo.setLogoPath(logoPath);
			uservo.setTorgTable(tuser.getTorgTable());
			Set tuserRoleSet = tuser.getTuserRoles();
			Iterator it = tuserRoleSet.iterator();
			while (it.hasNext()) {
				TuserRole userRole = (TuserRole) it.next();
				String roleName = userRole.getId().getTrole().getRoleName();
				uservo.setRoleName(roleName);
			}
			uservo.setAgentprivilege("0");
			uservo.setVehicleprivilege("0");
			uservo.setTerminalprivilege("0");
			Set userPrivilegeSet=tuser.getUserPrivileges();
			Iterator it2 = userPrivilegeSet.iterator();
			while (it2.hasNext()) {
				UserPrivilege userPrivilege = (UserPrivilege) it2.next();
				String privilegeValue = userPrivilege.getPrivilegeValue();
				String[] pids = privilegeValue.split(",");
				for(String pid:pids){
					if(pid=="3"||pid.equals("3")){
						uservo.setAgentprivilege("3");
					}
					if(pid=="4"||pid.equals("4")){
						uservo.setVehicleprivilege("4");
					}
					if(pid=="5"||pid.equals("5")){
						uservo.setTerminalprivilege("5");
					}
					uservo.getPrivileges().add(pid);
				}
			}
			//如果是代理商，则根据user_id查看该代理商是否可以拥有锁车权限
			if(uservo.getRoleName().equals("代理商用户")||uservo.getRoleName()=="代理商用户"){
				//String lockprivilege = this.userService1.queryLockPrivilege(uservo.getUserId());
				uservo.setLockprivilege("0");
				List<AgentVO> agentVOs=vehicleService.getAgentUserByUserId2(String.valueOf(uservo.getUserId()));
				if(agentVOs!=null&&agentVOs.size()>0){
					uservo.setLevel(Integer.parseInt(agentVOs.get(0).getLevel()));
					uservo.setParent_id(agentVOs.get(0).getParent_id());
					uservo.setOrgName(agentVOs.get(0).getAgent_name());
					uservo.setPrivilegeValue(agentVOs.get(0).getPrivileges());
					uservo.setRemark(agentVOs.get(0).getRemark());
				}
			}else{
				uservo.setOrgName(tuser.getTorgTable().getOrgName());
				uservo.setLevel(0);
				uservo.setLockprivilege("0");
				uservo.setPrivilegeValue("1,2,3,4,5");
				uservo.setRemark("");
			}

			/** *********登录历史记录**************** */
			// 查询历史信息
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				List historyList = userService1.queryUserLoginHistory(tuser
								.getUserCode(), "");
				List historyListtoday = userService1.queryUserLoginHistory(tuser
								.getUserCode(), sdf2.format(new Date()));
				if (historyList != null && historyList.size() > 0) {
							uservo.setLoginCount(historyListtoday.size());
							TloginHistory tlh = (TloginHistory) historyList.get(0);
							Date lastLoginDate = tlh.getLoginDate();
							uservo.setLastLoginDate(lastLoginDate.toLocaleString());
							uservo.setLoginIP(tlh.getLoginAddrIp());
				} else {
							uservo.setLoginCount(0);
			}
			TloginHistory tloginHistory = new TloginHistory();
			tloginHistory.setUserCode(tuser.getUserCode());
			String loginAddrIp = ServletActionContext.getRequest()
					.getRemoteAddr();
			tloginHistory.setLoginAddrIp(loginAddrIp);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Date loginDate;
			try {
				loginDate = sdf.parse(date.toLocaleString());
				tloginHistory.setLoginDate(loginDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			userService1.addLoginHistory(tloginHistory);

			/** ********************************* */
			ServletActionContext.getRequest().getSession().setAttribute("USER",
					uservo);
			ServletActionContext.getRequest().getSession().setAttribute(
					"SYSID", String.valueOf(System.currentTimeMillis()));
			ServletActionContext.getRequest().getSession().setAttribute(
					"WORKPAGEINFO_VNO", "");

			// 清除本用户之前浏览日志中的内存记录
			VbrowseHistoryCache.instace().clearHistory(uservo.getUserCode());
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(
							"com/jsd/web/login/action/resources.properties");
			Properties p = new Properties();
			try {
				p.load(inputStream);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			uservo.setCopyNum(p.getProperty("copyNum"));

			/** ******************************************************** */
			// 添加第三方厂家终端平台访问方法

			String userType = tuser.getUserType();
			if (null != userType && "2".equals(userType)) {
				// 查询第三方账户信息
				TuserOther other = userService1.findOtherUserInfo(tuser.getUserId());
				if (null != other) {
					ServletActionContext.getRequest().getSession().setAttribute(
							"otherFlag", "y");
					ServletActionContext.getRequest().getSession().setAttribute(
							"otherUserCode", other.getUserCode());
					ServletActionContext.getRequest().getSession().setAttribute(
							"otherUserName", other.getUserName());
					ServletActionContext.getRequest().getSession().setAttribute(
							"otherPassword", MD5Util.encrypt(other.getPassword()));

				}
			}

			return "success";
		} else {
			ServletActionContext.getRequest().setAttribute("flag", "3");
			if(tuser == null) {
				ServletActionContext.getRequest().setAttribute("idError",
						"口令或账号错误,或您已被禁止登陆");
			} else if(tuser.getUserFlag().equals("user no login")) {
				ServletActionContext.getRequest().setAttribute("Token",
						"您的账户只能在指定电脑登陆!");
			} else if(tuser.getUserFlag().equals("tashikong")) {
				ServletActionContext.getRequest().setAttribute("kong",
						"请设置正确的安全选项后登陆!");
			} else {
				ServletActionContext.getRequest().setAttribute("idError",
						"口令或账号错误,或您已被禁止登陆");
			}
			return "loginerror";
		}
	}

	public String modifyPwd() throws IOException {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		String userCode = uservo.getUserCode();
		Tuser tuser = userService1.queryUserInfo(userCode);
		tuser.setUserPassword(newtuserpwd);
		int flag = userService1.updateUserInfo(tuser);
		if(flag==0){
			uservo.setUserPassword(newtuserpwd);
			ServletActionContext.getRequest().getSession().setAttribute("USER", uservo);
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(flag);
		out.flush();
		out.close();
		return "success";
	}

	/** ***************************************属性注入方法*************************************************** */

	public UserService getUserService1() {
		return userService1;
	}

	public void setUserService1(UserService userService1) {
		this.userService1 = userService1;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getNewtuserpwd() {
		return newtuserpwd;
	}

	public void setNewtuserpwd(String newtuserpwd) {
		this.newtuserpwd = newtuserpwd;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public String getPathSesseion() {
		return pathSesseion;
	}

	public void setPathSesseion(String pathSesseion) {
		this.pathSesseion = pathSesseion;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}
}
