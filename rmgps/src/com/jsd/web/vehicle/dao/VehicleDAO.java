package com.jsd.web.vehicle.dao;

import java.util.List;

import com.jsd.util.PageRequestBean;
import com.jsd.web.common.PageInfo;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.TagentUser;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TvehicleInfo;
import com.jsd.web.vehicle.po.VehicleListPO;
import com.jsd.web.vehicle.vo.AgentVO;
import com.jsd.web.vehicle.vo.RemarkerVO;

@SuppressWarnings("rawtypes")
public interface VehicleDAO {

	/**
	 *
	 * @param vno
	 * @return
	 */
	public TvehicleInfo queryVehicleInfobyVno(String vno);

	public List queryListAgentInfo2(PageInfo pagination, UserVO uservo);

	public List queryListAgentInfo2(UserVO uservo);

	public void insertAgent(UserVO uservo, AgentVO vo);

	public String getAgentByAgentName(String agent_name);

	public String getUserByUserCode(String user_code, UserVO uservo);

	public List getAgentUserByUserId(String user_id);

	public List getAgentUserByUserId2(String user_id);

	public int modifyAgent(AgentVO vo);

	public int deleteAgent(String[] userids);

	public List getAgentsByUserId(UserVO uservo);

	public List getAgentsByUserId(String[] userids);

	public String getVehicleByUserId(UserVO uservo);

	public String getVehicleOfAgents(UserVO uservo, String user_id);

	public List getVehicleOfNoAgent(String vidOfNoAgent);

	public List getVehicleOfOneAgent(String user_id);

	public void resetVehicles(String user_id, String ids,TagentUser tau);

	public String queryVidOfAgent(UserVO uservo);

	public List queryAgentList(UserVO uservo);

	public List queryParentId(UserVO uservo);//查询是否有下级代理商

	public void resetVehicles2(String user_id, String ids,TagentUser tau);

	/**
	 * 添加地图标注
	 * addRemarker
	 * @param remarker
	 * @return
	 * @author Jnhuy
	 */
	public int addRemarker(RemarkerVO remarker);

	/**
	 * 删除地图标注
	 * removeRemarker
	 * @param remarker
	 * @return
	 * @author Jnhuy
	 */
	public int removeRemarker(RemarkerVO remarker);

	/**
	 * 查询地图标注
	 * queryRemarker
	 * @param userId
	 * @return
	 * @author Jnhuy
	 */
	public List<RemarkerVO> queryRemarker(int userId);

	/******************尹志刚;完成整机列表任务**********************/
	public List queryAgentList2(UserVO uservo);

	public List getAgentListByUserId(String userId);

	public List getSubTotal(VehicleListPO vlp,UserVO uservo);

	public List<VehicleListPO> getVehicleListByUserVo(PageRequestBean pageRequestBean, VehicleListPO vlp, UserVO user,
			Integer firstResult, Integer maxResults);

	public List getAllTotal(VehicleListPO vlp, String orgId);

	public List<VehicleListPO> getVehicleListByOrgId(PageRequestBean pageRequestBean,VehicleListPO vlp, String orgId,
			Integer firstResult, Integer maxResults);


	/******************尹志刚;完成整机列表任务**********************/
	public List getAgentListByUserId2(String userId);
	/******************baidu整机地图**********************/
	public List queryVehicleBaiduMap(String vehicleIds);

	public Tuser getUserByUserId(String userid);

	public Tuser getUserByUserCode(String user_code);

	public VehicleListPO findUserInDevice(Tuser tuser);
}
