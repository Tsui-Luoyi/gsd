package com.jsd.web.vehicle.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.service.VehicleManageService;
import com.jsd.web.vehicle.service.VehicleService;
import com.jsd.web.vehicle.vo.AgentResponseBean;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "serial", "rawtypes" })
public class VehicleManageAction extends ActionSupport {
	private VehicleManageService vehicleManageService;
	private VehicleService vehicleService;
	private List agentList;// 代理商列表
	private String agent_id;
	private List subList;
	private List totalList;
	private String vname1;
	private String vno1;
	private String vname2;
	private String vno2;
	private AgentResponseBean agentResponseBean;
	private String ids1;
	private String ids2;

	public String getIds1() {
		return ids1;
	}

	public void setIds1(String ids1) {
		this.ids1 = ids1;
	}

	public String getIds2() {
		return ids2;
	}

	public void setIds2(String ids2) {
		this.ids2 = ids2;
	}

	public String home() {
		// 得到登陆用户的信息
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		// 根据用户来查询用户下的代理商
		this.agentList = this.vehicleService.queryAgentList2(uservo);
		return "success";
	}

	public String queryAgent() {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		// 根据用户userid,来查询tagent_tuser表
		List list = vehicleService.getAgentListByUserId2(uservo.getUserId()
				.toString());
		String orgId = uservo.getTorgTable().getOrgId().toString();
		// 调用业务层
		if (agent_id != null && agent_id.length() > 0 && !agent_id.equals("-1")
				&& list != null && list.size() > 0) {// 如果list不为null，表示为子代；为null，表示是总代
			// 子代理走的业务层
			this.subList = vehicleManageService.querySubList(agent_id);
			this.totalList = vehicleManageService.queryTotalList(uservo
					.getUserId().toString());
		} else {
			// 总代理走的业务层
			if (agent_id != null && agent_id.length() > 0
					&& !agent_id.equals("-1")) {
				this.subList = vehicleManageService.querySubList(agent_id);
				this.totalList = vehicleManageService.queryTotalList2(orgId);
			}
		}
		agentResponseBean = new AgentResponseBean();
		agentResponseBean.setSubList(this.subList);
		agentResponseBean.setTotalList(this.totalList);

		return "queryAgent";
	}

	public String manageSubmit1() {
		// UserVO uservo = (UserVO)
		// ServletActionContext.getRequest().getSession().getAttribute("USER");
		if (agent_id != null && agent_id.length() > 0 && !agent_id.equals("-1")) {
			this.subList = vehicleManageService.manageSubmit11(agent_id,
					vname1, vno1);
		}
		agentResponseBean = new AgentResponseBean();
		agentResponseBean.setSubList(this.subList);
		return "manageSubmit1";
	}

	public String manageSubmit2() {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		List list = vehicleService.getAgentListByUserId2(uservo.getUserId()
				.toString());
		String orgId = uservo.getTorgTable().getOrgId().toString();
		if (agent_id != null && agent_id.length() > 0 && !agent_id.equals("-1")
				&& list != null && list.size() > 0) {
			this.totalList = vehicleManageService.manageSubmit21(uservo
					.getUserId().toString(), vname2, vno2);
		} else {
			if (agent_id != null && agent_id.length() > 0
					&& !agent_id.equals("-1")) {
				this.totalList = vehicleManageService.manageSubmit22(orgId,
						vname2, vno2);
			}
		}
		agentResponseBean = new AgentResponseBean();
		agentResponseBean.setTotalList(this.totalList);
		return "manageSubmit2";
	}

	public String toRight() {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER"); // 上级代理
		List list = vehicleService.getAgentListByUserId2(uservo.getUserId()
				.toString());
		String orgId = uservo.getTorgTable().getOrgId().toString();

		if (agent_id != null && agent_id.length() > 0 && !agent_id.equals("-1")
				&& list != null && list.size() > 0) {// 子代
			vehicleManageService.toRight(ids1, uservo.getUserId().toString());
		} else {// 总代
			vehicleManageService.toRight2(ids1);
		}
		if (agent_id != null && agent_id.length() > 0 && !agent_id.equals("-1")
				&& list != null && list.size() > 0) {// 如果list不为null，表示为子代；为null，表示是总代
			// 子代理走的业务层
			this.subList = vehicleManageService.querySubList(agent_id);
			this.totalList = vehicleManageService.queryTotalList(uservo
					.getUserId().toString());
		} else {
			// 总代理走的业务层
			if (agent_id != null && agent_id.length() > 0
					&& !agent_id.equals("-1")) {
				this.subList = vehicleManageService.querySubList(agent_id);
				this.totalList = vehicleManageService.queryTotalList2(orgId);
			}
		}

		agentResponseBean = new AgentResponseBean();
		agentResponseBean.setSubList(this.subList);
		agentResponseBean.setTotalList(this.totalList);

		return "toRight";
	}

	public String toLeft() {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession()
				.getAttribute("USER");
		List list = vehicleService.getAgentListByUserId2(uservo.getUserId()
				.toString());
		String orgId = uservo.getTorgTable().getOrgId().toString();

		vehicleManageService.toLeft(ids2, agent_id);

		if (agent_id != null && agent_id.length() > 0 && !agent_id.equals("-1")
				&& list != null && list.size() > 0) {// 如果list不为null，表示为子代；为null，表示是总代
			// 子代理走的业务层
			this.subList = vehicleManageService.querySubList(agent_id);
			this.totalList = vehicleManageService.queryTotalList(uservo
					.getUserId().toString());
		} else {
			// 总代理走的业务层
			if (agent_id != null && agent_id.length() > 0
					&& !agent_id.equals("-1")) {
				this.subList = vehicleManageService.querySubList(agent_id);
				this.totalList = vehicleManageService.queryTotalList2(orgId);
			}
		}

		agentResponseBean = new AgentResponseBean();
		agentResponseBean.setSubList(this.subList);
		agentResponseBean.setTotalList(this.totalList);

		return "toLeft";
	}

	/************************* set/get ************************************/
	public VehicleManageService getVehicleManageService() {
		return vehicleManageService;
	}

	public void setVehicleManageService(
			VehicleManageService vehicleManageService) {
		this.vehicleManageService = vehicleManageService;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
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

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public AgentResponseBean getAgentResponseBean() {
		return agentResponseBean;
	}

	public void setAgentResponseBean(AgentResponseBean agentResponseBean) {
		this.agentResponseBean = agentResponseBean;
	}

	public String getVname1() {
		return vname1;
	}

	public void setVname1(String vname1) {
		this.vname1 = vname1;
	}

	public String getVno1() {
		return vno1;
	}

	public void setVno1(String vno1) {
		this.vno1 = vno1;
	}

	public String getVname2() {
		return vname2;
	}

	public void setVname2(String vname2) {
		this.vname2 = vname2;
	}

	public String getVno2() {
		return vno2;
	}

	public void setVno2(String vno2) {
		this.vno2 = vno2;
	}
	/************************* set/get ************************************/
}
