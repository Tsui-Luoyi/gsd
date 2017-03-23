package com.jsd.web.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.common.PageInfo;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.VehicleOfNoAgentBean;
import com.jsd.web.po.VehicleOfOneAgentBean;
import com.jsd.web.vehicle.po.VehicleListPO;
import com.jsd.web.vehicle.service.VehicleService;
import com.jsd.web.vehicle.vo.AgentVO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

@SuppressWarnings("rawtypes")
public class AgentManageAction extends ActionSupport {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;
	private PageInfo pagination;
	private String url; // 分页时的url
	private String ifHit;// 判别是否对分页进行了操作
	private List agentList;
	private String ids;

	private String user_id;
	private String agent_name;// 代理商名称
	private String user_code;// 用户登录名
	private String userName;// 用户显示名称
	private String user_password;// 密码
	private String privileges;
	private Integer level;
	private String lock_privilege;// 解锁权限
	private String agent_privilege;// 代理商管理权限
	private String vehicle_privilege;// 车辆管理权限
	private String terminal_privilege;// 终端续费权限
	private int blocks;
	private String remark;

	private String jquerybackdata;// 异步返回值
	private String agentbackdata;
	private String userbackdata;

	private String vehicleList;// 逗号分割
	private List vehicleOfOneAgent;// 一个代理商的车辆列表
	private List vehicleOfNoAgent;// 未代理车辆列表
	private String first_agent_name;// 代理商修改页面代理商名称的初始值
	private String first_user_code;// 代理商修改页面用户登录名称的初始值

	private int resetFlag;

	/**
	 * 代理商管理
	 *
	 * @return
	 * @throws IOException
	 */
	public String queryListAgentInfo() throws IOException {
		// 分页
		if (ifHit != null) {// 如果是就定位到具体页面
			if (pagination == null) {
				pagination = new PageInfo();
			}
			if (pagination.getCurrentPage() <= 0) {
				pagination.setCurrentPage(1);
			}
			if (pagination.getTotalPages() != 0
					&& pagination.getCurrentPage() > pagination.getTotalPages()) {
				pagination.setCurrentPage(pagination.getTotalPages());
			}
		} else {// 否则总是定位到第一页
			if (pagination == null) {
				pagination = new PageInfo();
			}
			pagination.setCurrentPage(1);
		}
		this.ifHit = null;
		this.url = "AgentManageAction.action";
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		this.agentList = this.vehicleService.queryListAgentInfo4(pagination,
				uservo);
		return "success";
	}

	/**
	 * 新增代理商
	 *
	 * @throws IOException
	 */
	public void insertAgent() throws IOException {// 加上所属组织，即当前用户的所属组织
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		String agentbackstr = this.vehicleService
				.getAgentByAgentName(this.agent_name);
		String userbackstr = this.vehicleService.getUserByUserCode(
				this.user_code, uservo);
		this.agentbackdata = "";
		this.userbackdata = "";
		boolean flag = false;
		if ("该代理商已被注册".equals(agentbackstr)) {
			this.agentbackdata = agentbackstr;
			flag = true;
		}
		if ("该用户已被注册".equals(userbackstr)) {
			this.userbackdata = userbackstr;
			flag = true;
		}
		if (flag) {
			clearResponse("添加失败！");
		}
		AgentVO vo = new AgentVO();
		vo.setAgent_name(agent_name);
		vo.setUser_code(user_code);
		vo.setUser_password(user_password);
		String[] parray = privileges.split(",");
		lock_privilege = "0";
		for (String privilege : parray) {
			if (privilege == "1" || privilege.equals("1")) {
				lock_privilege = "1";
			}
			if (privilege == "3" || privilege.equals("3")) {
				vo.setAgent_privilege("3");
			}
			if (privilege == "4" || privilege.equals("4")) {
				vo.setVehicle_privilege("4");
			}
			if (privilege == "5" || privilege.equals("5")) {
				vo.setTerminal_privilege("5");
			}
		}
		if (uservo.getRoleName().equals("普通用户")
				|| uservo.getRoleName() == "普通用户") {
			vo.setLevel("1");
			vo.setLock_privilege(lock_privilege);
			vo.setParent_id(String.valueOf(uservo.getUserId()));
		} else {
			vo.setLevel(String.valueOf(uservo.getLevel() + 1));
			vo.setParent_id(uservo.getParent_id() + ","
					+ String.valueOf(uservo.getUserId()));
			if (uservo.getLockprivilege().equals("1")
					|| uservo.getLockprivilege() == "1") {
				vo.setLock_privilege(lock_privilege);
			} else {
				vo.setLock_privilege("0");
			}
		}

		vo.setRemark(remark);
		this.vehicleService.insertAgent(uservo, vo);
		queryListAgentInfo();// 跳转到代理商列表页面
		clearResponse("添加成功！");
	}

	/**
	 * 修改代理商
	 *
	 * @throws IOException
	 */
	public void modifyAgent() throws IOException {// 需要user_id
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");

		/*
		 * 修改日志
		 */
		boolean flag = false;
		this.agentbackdata = "";
		this.userbackdata = "";
		if (!this.first_agent_name.equals(this.agent_name)) {// 代理商名称未改动则不校验
			String agentbackstr = this.vehicleService
					.getAgentByAgentName(this.agent_name);
			if ("该代理商已被注册".equals(agentbackstr)) {
				this.agentbackdata = agentbackstr;
				flag = true;
			}
		}
		if (!this.first_user_code.equals(this.user_code)) {// 用户登录名称为改动则不校验
			String userbackstr = this.vehicleService.getUserByUserCode(
					this.user_code, uservo);
			if ("该用户已被注册".equals(userbackstr)) {
				this.userbackdata = userbackstr;
				flag = true;
			}
		}
		if (flag) {
			clearResponse("修改失败！");
		}
		AgentVO vo = new AgentVO();
		vo.setUser_id(user_id);
		vo.setAgent_name(agent_name);
		vo.setUser_code(user_code);
		vo.setUser_password(user_password);
		vo.setRemark(remark);
		vo.setUserName(userName);
		if (uservo.getUserId() != Integer.parseInt(user_id)) {
			String[] parray = privileges.split(",");
			lock_privilege = "0";
			for (String privilege : parray) {
				if (privilege == "1" || privilege.equals("1")) {
					lock_privilege = "1";
				}
				if (privilege == "3" || privilege.equals("3")) {
					vo.setAgent_privilege("3");
				}
				if (privilege == "4" || privilege.equals("4")) {
					vo.setVehicle_privilege("4");
				}
				if (privilege == "5" || privilege.equals("5")) {
					vo.setTerminal_privilege("5");
				}
			}
			vo.setLock_privilege(lock_privilege);
			vo.setLevel("9");
		} else {
			lock_privilege = uservo.getLockprivilege();
			vo.setLock_privilege(lock_privilege);
			vo.setAgent_privilege(uservo.getAgentprivilege());
			vo.setVehicle_privilege(uservo.getVehicleprivilege());
			vo.setTerminal_privilege(uservo.getTerminalprivilege());
			vo.setLevel(uservo.getLevel().toString());
		}
		int count = this.vehicleService.modifyAgent(vo);
		if (count == 0) {
			clearResponse("修改成功！");
			if (uservo.getUserId() == Integer.parseInt(user_id)) {
				uservo.setOrgName(agent_name);
				uservo.setUserCode(user_code);
				uservo.setUserPassword(user_password);
				uservo.setAgentprivilege(agent_privilege);
				uservo.setUserName(userName);
				ServletActionContext.getRequest().getSession()
						.setAttribute("USER", uservo);
			}
		} else {
			clearResponse("修改失败！");
		}

	}

	/**
	 * 根据user_id查找代理商及关联用户
	 *
	 * @throws IOException
	 */
	public void getAgentUserByUserId() throws IOException {
		this.agentList = this.vehicleService.getAgentUserByUserId(this.user_id);
		AgentVO vo = (AgentVO) this.agentList.get(0);
		this.agent_name = vo.getAgent_name();
		this.user_code = vo.getUser_code();
		this.user_password = vo.getUser_password();
		this.user_id = vo.getUser_id();
		this.level = Integer.parseInt(vo.getLevel());
		this.lock_privilege = vo.getLock_privilege();
		this.privileges = vo.getPrivileges();
		this.agent_privilege = "0";
		this.vehicle_privilege = "0";
		this.terminal_privilege = "0";
		String[] parray = privileges.split(",");
		for (String privilege : parray) {
			if (privilege == "3" || privilege.equals("3")) {
				this.agent_privilege = "3";
			}
			if (privilege == "4" || privilege.equals("4")) {
				this.vehicle_privilege = "4";
			}
			if (privilege == "5" || privilege.equals("5")) {
				this.terminal_privilege = "5";
			}
		}
		this.remark = vo.getRemark();
		UserVO userVo = new UserVO();
		userVo.setUserId(Integer.parseInt(vo.getUser_id()));
		this.blocks = this.vehicleService.queryParentId(userVo);
		Map<String, Object> agentMap = new HashMap<String, Object>();
		agentMap.put("agentName", vo.getAgent_name());
		agentMap.put("userCode", vo.getUser_code());
		agentMap.put("userPassword", vo.getUser_password());
		agentMap.put("userId", vo.getUser_id());
		agentMap.put("lockPrivilege", vo.getLock_privilege());
		agentMap.put("levels", Integer.parseInt(vo.getLevel()));
		agentMap.put("privileges", vo.getPrivileges());
		agentMap.put("agentPrivilege", agent_privilege);
		agentMap.put("vehiclePrivilege", vehicle_privilege);
		agentMap.put("terminalPrivilege", terminal_privilege);
		agentMap.put("blocks", blocks);
		agentMap.put("remark", remark);
		agentMap.put("userName", vo.getUserName());
		JSONArray jsonArray = JSONArray.fromObject(agentMap);
		clearResponse(jsonArray);
	}

	public void clearResponse(Object result) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * 删除代理商及关联用户、用户关联的角色
	 *
	 * @throws IOException
	 */
	public void deleteAgent() throws IOException {
		String[] userids = getUserIdsByUserId(user_id);

		if (userids != null && userids.length > 0) {
			List<Tuser> userList = this.vehicleService
					.getUsersByUserId(userids);
			List<VehicleListPO> devtermInfothis = this.vehicleService
					.findUserInDevice(userList);
			if (devtermInfothis.size() > 0) {
				clearResponse(3);
			} else {
				int count = this.vehicleService.deleteAgent(userids);
				if (count == 0) {
					queryListAgentInfo();// 跳转到代理商列表页面
					clearResponse(0);
				} else {
					clearResponse(2);
				}
			}
		} else {
			clearResponse(1);
		}

	}

	private String[] getUserIdsByUserId(String tempStr) {
		String returnStrArray[] = null;
		if (tempStr != null && tempStr.length() > 0)
			returnStrArray = tempStr.split(",");
		if (returnStrArray == null)
			returnStrArray = new String[0];
		return returnStrArray;
	}

	/**
	 * 查看是否存在相同的代理商名称
	 *
	 * @throws IOException
	 */
	public void getAgentByAgentName() throws IOException {
		this.jquerybackdata = this.vehicleService
				.getAgentByAgentName(this.agent_name);
		clearResponse(jquerybackdata);
	}

	/**
	 * 查看是否存在相同的登录用户
	 *
	 * @throws IOException
	 */
	public void getUserByUserCode() throws IOException {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		this.jquerybackdata = this.vehicleService.getUserByUserCode(
				this.user_code, uservo);
		clearResponse(jquerybackdata);
	}

	/**
	 * 车辆管理 代理商只在初始化页面时查询数据库 登录用户所属组织下的车辆也只在页面初始化时查询数据库 即：在页面选择代理商时
	 * 进行的异步操作不实时反应数据库中代理商和登录用户组织下的车辆的变化 车辆之间用逗号进行分割
	 *
	 * @return
	 */
	public String queryVehicleOfAgent() {// 查找登录用户所属组织下的代理商、车辆
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		this.agentList = this.vehicleService.getAgentsByUserId(uservo);
		// 查找车辆
		this.vehicleList = this.vehicleService.getVehicleByUserId(uservo);
		return "success";
	}

	private String vname1;
	private String dno1;
	private String vname2;
	private String dno2;

	public String getVname1() {
		return vname1;
	}

	public void setVname1(String vname1) {
		this.vname1 = vname1;
	}

	public String getDno1() {
		return dno1;
	}

	public void setDno1(String dno1) {
		this.dno1 = dno1;
	}

	public String getVname2() {
		return vname2;
	}

	public void setVname2(String vname2) {
		this.vname2 = vname2;
	}

	public String getDno2() {
		return dno2;
	}

	public void setDno2(String dno2) {
		this.dno2 = dno2;
	}

	public String getVehicles() {// 查找登录用户所属组织下的代理商、车辆
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		System.out.println(vname1);
		String vidOfAll = this.vehicleList;// 组织下所有的vid，逗号分割
		boolean flag = true;
		if (vidOfAll != null && !"".equals(vidOfAll)) {
			String[] vidAll = vidOfAll.split(",");
			String vidOfNoAgent = "";// 所有未代理的vid
			String vidOfAgents = this.vehicleService.getVehicleOfAgents(uservo,
					this.user_id);// 组织下所有代理商的vid
			if (!"".equals(vidOfAgents)) {
				String[] vidAgents = vidOfAgents.split(",");
				for (int i = 0; i < vidAll.length; i++) {
					if (!"".equals(vidAll[i])) {// 剔除空格
						flag = true;// 假设不相同
						for (int j = 0; j < vidAgents.length; j++) {
							if (!"".equals(vidAgents[j])) {// 剔除空格
								if (vidAll[i].equals(vidAgents[j])) {// 剔除相同的
									flag = false;// 表示相同，则剔除，中断此次循环，进入下次循环
									break;
								}
							}
						}
						if (flag) {
							vidOfNoAgent = vidOfNoAgent + "," + vidAll[i];// 不相同则积累
						}
					}
				}
				if (!"".equals(vidOfNoAgent)) {
					this.vehicleOfOneAgent = this.vehicleService
							.getVehicleOfOneAgent(this.user_id);// 选中代理商的车、名字
					this.vehicleOfNoAgent = this.vehicleService
							.getVehicleOfNoAgent(vidOfNoAgent);// 所有未代理的车
				} else {// 全部被代理
					this.vehicleOfOneAgent = this.vehicleService
							.getVehicleOfOneAgent(this.user_id);// 选中代理商的车、名字
					this.vehicleOfNoAgent = new ArrayList();
				}
			} else {
				this.vehicleOfOneAgent = new ArrayList();
				this.vehicleOfNoAgent = this.vehicleService
						.getVehicleOfNoAgent(vidOfAll);// 所有车都是未代理
			}
		} else {
			this.vehicleOfOneAgent = new ArrayList();
			this.vehicleOfNoAgent = new ArrayList();
		}

		return "success";
	}

	// ----------------------modify----------------------------------------------------------

	@SuppressWarnings("unchecked")
	public String searchVehicles4oneAgent() {// 查找登录用户所属组织下的代理商、车辆

		List oneAgentBeanList;

		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		System.out.println(vname1);
		String vidOfAll = this.vehicleList;// 组织下所有的vid，逗号分割
		boolean flag = true;
		if (vidOfAll != null && !"".equals(vidOfAll)) {
			String[] vidAll = vidOfAll.split(",");
			String vidOfNoAgent = "";// 所有未代理的vid
			String vidOfAgents = this.vehicleService.getVehicleOfAgents(uservo,
					this.user_id);// 组织下所有代理商的vid
			if (!"".equals(vidOfAgents)) {
				String[] vidAgents = vidOfAgents.split(",");
				for (int i = 0; i < vidAll.length; i++) {
					if (!"".equals(vidAll[i])) {// 剔除空格
						flag = true;// 假设不相同
						for (int j = 0; j < vidAgents.length; j++) {
							if (!"".equals(vidAgents[j])) {// 剔除空格
								if (vidAll[i].equals(vidAgents[j])) {// 剔除相同的
									flag = false;// 表示相同，则剔除，中断此次循环，进入下次循环
									break;
								}
							}
						}
						if (flag) {
							vidOfNoAgent = vidOfNoAgent + "," + vidAll[i];// 不相同则积累
						}
					}
				}
				if (!"".equals(vidOfNoAgent)) {
					oneAgentBeanList = this.vehicleService
							.getVehicleOfOneAgent(this.user_id);// 选中代理商的车、名字
				} else {// 全部被代理
					oneAgentBeanList = this.vehicleService
							.getVehicleOfOneAgent(this.user_id);// 选中代理商的车、名字
				}
			} else {
				oneAgentBeanList = new ArrayList();
			}
		} else {
			oneAgentBeanList = new ArrayList();
		}

		/*
		 * 比较list
		 */
		if (!(null == vname1) && !("".equals(vname1)) && !(vname1.equals(null))) {
			Iterator it = oneAgentBeanList.iterator();
			List l = new ArrayList();
			while (it.hasNext()) {
				AgentVO a = (AgentVO) it.next();
				if (a.getVname().equalsIgnoreCase(vname1)
						|| a.getVname().toUpperCase()
								.contains(vname1.toUpperCase())) {
					l.add(a);
				}
			}
			oneAgentBeanList = l;
		}

		if (!(null == dno1) && !("".equals(dno1)) && !(dno1.equals(null))) {
			Iterator it = oneAgentBeanList.iterator();
			List l = new ArrayList();
			while (it.hasNext()) {
				AgentVO a = (AgentVO) it.next();
				if (a.getDno().equalsIgnoreCase(dno1)
						|| a.getDno().toUpperCase()
								.contains(dno1.toUpperCase())) {
					l.add(a);
				}
			}
			oneAgentBeanList = l;
		}

		oneAgentBean = new VehicleOfOneAgentBean();
		oneAgentBean.setVehicleOfOneAgent(oneAgentBeanList);

		return "success";
	}

	private VehicleOfOneAgentBean oneAgentBean;

	public VehicleOfOneAgentBean getOneAgentBean() {
		return oneAgentBean;
	}

	@SuppressWarnings("unchecked")
	public String searchVehicles4noAgent() {// 查找登录用户所属组织下的代理商、车辆

		List noAgentBeanList;

		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		System.out.println(vname1);
		String vidOfAll = this.vehicleList;// 组织下所有的vid，逗号分割
		boolean flag = true;
		if (vidOfAll != null && !"".equals(vidOfAll)) {
			String[] vidAll = vidOfAll.split(",");
			String vidOfNoAgent = "";// 所有未代理的vid
			String vidOfAgents = this.vehicleService.getVehicleOfAgents(uservo,
					this.user_id);// 组织下所有代理商的vid
			if (!"".equals(vidOfAgents)) {
				String[] vidAgents = vidOfAgents.split(",");
				for (int i = 0; i < vidAll.length; i++) {
					if (!"".equals(vidAll[i])) {// 剔除空格
						flag = true;// 假设不相同
						for (int j = 0; j < vidAgents.length; j++) {
							if (!"".equals(vidAgents[j])) {// 剔除空格
								if (vidAll[i].equals(vidAgents[j])) {// 剔除相同的
									flag = false;// 表示相同，则剔除，中断此次循环，进入下次循环
									break;
								}
							}
						}
						if (flag) {
							vidOfNoAgent = vidOfNoAgent + "," + vidAll[i];// 不相同则积累
						}
					}
				}
				if (!"".equals(vidOfNoAgent)) {
					noAgentBeanList = this.vehicleService
							.getVehicleOfNoAgent(vidOfNoAgent);// 所有未代理的车
				} else {// 全部被代理
					noAgentBeanList = new ArrayList();
				}
			} else {
				noAgentBeanList = this.vehicleService
						.getVehicleOfNoAgent(vidOfAll);// 所有车都是未代理
			}
		} else {
			noAgentBeanList = new ArrayList();
		}

		/*
		 * 比较list
		 */

		if (!(null == vname2) && !("".equals(vname2)) && !(vname2.equals(null))) {
			Iterator it = noAgentBeanList.iterator();
			List l = new ArrayList();
			while (it.hasNext()) {
				AgentVO a = (AgentVO) it.next();
				if (a.getVno().equalsIgnoreCase(vname2)
						|| a.getVname().toUpperCase()
								.contains(vname2.toUpperCase())) {
					l.add(a);
				}
			}
			noAgentBeanList = l;
		}

		if (!(null == dno2) && !("".equals(dno2)) && !(dno2.equals(null))) {
			Iterator it = noAgentBeanList.iterator();
			List l = new ArrayList();
			while (it.hasNext()) {
				AgentVO a = (AgentVO) it.next();
				if (a.getDno().equalsIgnoreCase(dno2)
						|| a.getDno().toUpperCase()
								.contains(dno2.toUpperCase())) {
					l.add(a);
				}
			}
			noAgentBeanList = l;
		}

		noAgentBean = new VehicleOfNoAgentBean();
		noAgentBean.setVehicleOfNoAgent(noAgentBeanList);
		return "success";
	}

	private VehicleOfNoAgentBean noAgentBean;

	public VehicleOfNoAgentBean getNoAgentBean() {
		return noAgentBean;
	}

	// ---------------end----------------------

	public String resetVehicles() {
		System.out.println("resetFlag = " + this.resetFlag);
		// 获取user
		UserVO user = (UserVO) ServletActionContext.getContext().getSession()
				.get("USER");
		Integer dengluId = user.getUserId();
		String dengluIp = user.getLoginIP();
		/*
		 * System.out.println("ip:"+ip);
		 * System.out.println("dengluIp:"+dengluIp);
		 * System.out.println("dengluId:"+dengluId);
		 */
		// 使用用户对象内携带的ip
		this.vehicleService.resetVehicles(this.user_id, this.ids,
				this.resetFlag, dengluIp, dengluId);
		getVehicles();// 重新查询
		return "success";
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

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public List getAgentList() {
		return agentList;
	}

	public void setAgentList(List agentList) {
		this.agentList = agentList;
	}

	public String getIfHit() {
		return ifHit;
	}

	public void setIfHit(String ifHit) {
		this.ifHit = ifHit;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getLock_privilege() {
		return lock_privilege;
	}

	public void setLock_privilege(String lock_privilege) {
		this.lock_privilege = lock_privilege;
	}

	public String getJquerybackdata() {
		return jquerybackdata;
	}

	public void setJquerybackdata(String jquerybackdata) {
		this.jquerybackdata = jquerybackdata;
	}

	public String getAgentbackdata() {
		return agentbackdata;
	}

	public void setAgentbackdata(String agentbackdata) {
		this.agentbackdata = agentbackdata;
	}

	public String getUserbackdata() {
		return userbackdata;
	}

	public void setUserbackdata(String userbackdata) {
		this.userbackdata = userbackdata;
	}

	public String getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(String vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List getVehicleOfOneAgent() {
		return vehicleOfOneAgent;
	}

	public void setVehicleOfOneAgent(List vehicleOfOneAgent) {
		this.vehicleOfOneAgent = vehicleOfOneAgent;
	}

	public List getVehicleOfNoAgent() {
		return vehicleOfNoAgent;
	}

	public void setVehicleOfNoAgent(List vehicleOfNoAgent) {
		this.vehicleOfNoAgent = vehicleOfNoAgent;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getFirst_agent_name() {
		return first_agent_name;
	}

	public void setFirst_agent_name(String first_agent_name) {
		this.first_agent_name = first_agent_name;
	}

	public String getFirst_user_code() {
		return first_user_code;
	}

	public void setFirst_user_code(String first_user_code) {
		this.first_user_code = first_user_code;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public String getAgent_privilege() {
		return agent_privilege;
	}

	public void setAgent_privilege(String agentPrivilege) {
		agent_privilege = agentPrivilege;
	}

	public String getVehicle_privilege() {
		return vehicle_privilege;
	}

	public void setVehicle_privilege(String vehiclePrivilege) {
		vehicle_privilege = vehiclePrivilege;
	}

	public String getTerminal_privilege() {
		return terminal_privilege;
	}

	public void setTerminal_privilege(String terminalPrivilege) {
		terminal_privilege = terminalPrivilege;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setResetFlag(int resetFlag) {
		this.resetFlag = resetFlag;
	}

}
