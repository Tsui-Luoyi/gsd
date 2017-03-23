package com.jsd.web.vehicle.service;

import java.util.List;

import com.jsd.util.PageRequestBean;
import com.jsd.util.PageResponseBean;
import com.jsd.web.common.PageInfo;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.Tuser;
import com.jsd.web.vehicle.po.VehicleListPO;
import com.jsd.web.vehicle.vo.AgentVO;
import com.jsd.web.vehicle.vo.RemarkerVO;

@SuppressWarnings("rawtypes")
public interface VehicleService {

	/**
	 * 根据整车vno查询整车类型vmid
	 * @param vno
	 * @return
	 */
	public String queryVmidByVno(String vno);

	public int queryParentId(UserVO uservo);

	public List<AgentVO> queryListAgentInfo4(PageInfo pagination, UserVO uservo);

	public void insertAgent(UserVO uservo, AgentVO vo);

	public String getAgentByAgentName(String agent_name);

	public String getUserByUserCode(String user_code, UserVO uservo);

	public List getAgentUserByUserId(String user_id);

	public List getAgentUserByUserId2(String user_id);

	public int modifyAgent(AgentVO vo);

	public int deleteAgent(String[] userids);

	public List getAgentsByUserId(UserVO uservo);

	public String getVehicleByUserId(UserVO uservo);

	public String getVehicleOfAgents(UserVO uservo, String user_id);

	public List getVehicleOfOneAgent(String user_id);

	public List getVehicleOfNoAgent(String vidOfNoAgent);

	public void resetVehicles(String user_id, String ids, int resetFlag,String ip,Integer dengluId);

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

	public PageResponseBean pageQuery(VehicleListPO vlp,UserVO uservo,
			PageRequestBean pageRequestBean);

	public PageResponseBean pageQuery(VehicleListPO vlp, String orgId,
			PageRequestBean pageRequestBean);

	public List<VehicleListPO> getVehicleListBySub(VehicleListPO vlp,
			UserVO uservo, PageRequestBean pageRequestBean);

	public List<VehicleListPO> getVehicleListByAll(VehicleListPO vlp,
			String orgId, PageRequestBean pageRequestBean);

	public List getAgentListByUserId2(String userId);

	/******************尹志刚;完成整机列表任务**********************/

	public List queryVehicleBaiduMap(UserVO uservo);

	public List<Tuser> getUsersByUserId(String[] userids);

	public List<VehicleListPO> findUserInDevice(List<Tuser> userList);
}
