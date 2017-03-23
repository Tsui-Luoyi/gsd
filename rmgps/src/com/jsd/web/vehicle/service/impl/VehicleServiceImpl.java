package com.jsd.web.vehicle.service.impl;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.jsd.util.PageRequestBean;
import com.jsd.util.PageResponseBean;
import com.jsd.web.authority.dao.AuUserDAO;
import com.jsd.web.common.PageInfo;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.TagentUser;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TvOrgRel;
import com.jsd.web.po.TvehicleInfo;
import com.jsd.web.po.TvvBand;
import com.jsd.web.vehicle.dao.VehicleDAO;
import com.jsd.web.vehicle.po.VehicleListPO;
import com.jsd.web.vehicle.service.VehicleService;
import com.jsd.web.vehicle.vo.AgentVO;
import com.jsd.web.vehicle.vo.RemarkerVO;
import com.jsd.web.vehicle.vo.VehicleMapInfoVO;

@SuppressWarnings({"serial","rawtypes","unchecked","unused"})
public class VehicleServiceImpl implements VehicleService,Serializable{

	private VehicleDAO vehicleDao;
	private AuUserDAO auUserDAO;
	private String substring;

	//通过整车vno查找对应的dno
	private String getDnoByVno(TvehicleInfo vi){
		Set<TvvBand> tvvBandSet = vi.getTvvBands();
		String dno = "";

		Iterator it = tvvBandSet.iterator();

		while(it.hasNext()){
			TvvBand vb = (TvvBand)it.next();
			dno = vb.getDevtermInfo().getDno();
		}

		return dno;
	}

	private String getMapValue(HashMap map, String key) {
		Object obj = null;
		obj = map.get(key);
		if(obj == null)
			return "-";

		return (String)obj;
	}

	private boolean isOffline(String date_str) {
		boolean ret = false;
		Date date = null;
		Date date_now = null;
		if (date_str == null)
			return ret;
		if (date_str.equals(""))
			return ret;
		try {
			date_now = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			date = df.parse(date_str);
			if (date == null)
				return ret;

			int time_diff = (int) ((date_now.getTime() - date.getTime()) / 3600 / 1000);
			if (time_diff > 240)
				ret = true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		return ret;
	}

	private boolean isDateBefore(String date_str) {
		boolean ret = false;
		Date date = null;
		Date date_now = null;
		if (date_str == null)
			return ret;
		if (date_str.equals(""))
			return ret;

		try {
			date_now = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			date = df.parse(date_str);
			if (date == null)
				return ret;
			ret = date_now.before(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}

		return ret;
	}

	public void setVehicleDao(VehicleDAO vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public void setAuUserDAO(AuUserDAO auUserDAO) {
		this.auUserDAO = auUserDAO;
	}


	/**
	 * 根据整车vno查询整车类型vmid
	 * @param vno
	 * @return
	 */
	@Override
	public String queryVmidByVno(String vno) {
		//根据vno取相应整机型号的vmid的值
		String vmid = this.vehicleDao.queryVehicleInfobyVno(vno).getTvehicleModel().getVmId().toString();

		return vmid;
	}

	/**
	 * baidu查询整机vno、及其所在坐标值
	 */
	@Override
	public List queryVehicleBaiduMap(UserVO uservo) {
		String vehicleIds = "''";
		if("代理商用户".equals(uservo.getRoleName())){//判断是否为代理商
			String vidofagent = this.vehicleDao.queryVidOfAgent(uservo);
			vehicleIds = vehicleIds + "," + vidofagent;
		}else{
			List orgVehiList = auUserDAO.queryVehicleOrgRelListByUser(uservo.getUserId().toString());
			if (orgVehiList != null && orgVehiList.size() > 0) {
				for (int i = 0; i < orgVehiList.size(); i++) {
					TvOrgRel orgrel = (TvOrgRel) orgVehiList.get(i);
					String ivehicleId = orgrel.getVno();
					vehicleIds = vehicleIds + "," + ivehicleId;
				}
			}
		}

		List<VehicleMapInfoVO> list = this.vehicleDao.queryVehicleBaiduMap(vehicleIds);

			//纠偏123：用原始经纬度取得纠偏经纬度


		return list;
	}

	@Override
	public List<AgentVO> queryListAgentInfo4(PageInfo pagination, UserVO uservo){
		String str = "";
		List<AgentVO> list3=new ArrayList<AgentVO>();
		List<AgentVO> list=this.vehicleDao.queryListAgentInfo2(pagination, uservo);
		if(list!=null&&list.size()>0){
			for(AgentVO agentVO:list){
				UserVO uservo2=new UserVO();
				uservo2.setUserId(Integer.parseInt(agentVO.getUser_id()));
				uservo2.setLevel(Integer.parseInt(agentVO.getLevel()));
				uservo2.setTorgTable(uservo.getTorgTable());
				uservo2.setRoleName("代理商用户");
				uservo2.setOrgName(agentVO.getAgent_name());
				List<AgentVO> list2=this.vehicleDao.queryListAgentInfo2(uservo2);
				if(list2!=null&&list2.size()>0){
					Set<AgentVO> agentSet2=new HashSet<AgentVO>();
					for(AgentVO agentVO2:list2){
						UserVO uservo3=new UserVO();
						uservo3.setUserId(Integer.parseInt(agentVO2.getUser_id()));
						uservo3.setLevel(Integer.parseInt(agentVO2.getLevel()));
						uservo3.setTorgTable(uservo.getTorgTable());
						uservo3.setRoleName("代理商用户");
						uservo3.setOrgName(agentVO2.getAgent_name());
						List<AgentVO> list5=this.vehicleDao.queryListAgentInfo2(uservo3);
						if(list5!=null&&list5.size()>0){
							Set<AgentVO> agentSet3=new HashSet<AgentVO>();
							for(AgentVO agentVO3:list5){
								UserVO uservo4=new UserVO();
								uservo4.setUserId(Integer.parseInt(agentVO3.getUser_id()));
								uservo4.setLevel(Integer.parseInt(agentVO3.getLevel()));
								uservo4.setTorgTable(uservo.getTorgTable());
								uservo4.setRoleName("代理商用户");
								uservo4.setOrgName(agentVO3.getAgent_name());
								List<AgentVO> list6=this.vehicleDao.queryListAgentInfo2(uservo4);
								if(list6!=null&&list6.size()>0){
									Set<AgentVO> agentSet4=new HashSet<AgentVO>();
									for(AgentVO agentVO4:list6){
										String agentName04=agentVO4.getAgent_name()+"（"+agentVO4.getLevel()+"级）";
										agentVO4.setAgent_name(agentName04);
										agentSet4.add(agentVO4);
									}
									agentVO3.setAgents(agentSet4);
									str="，拥有下级代理商）";
								}else{
									str="）";
								}
								String agentName03=agentVO3.getAgent_name()+"（"+agentVO3.getLevel()+"级"+str;;
								agentVO3.setAgent_name(agentName03);
								agentSet3.add(agentVO3);
							}
							agentVO2.setAgents(agentSet3);
						}else{
							str="）";
						}
						String agentName02=agentVO2.getAgent_name()+"（"+agentVO2.getLevel()+"级"+str;
						agentVO2.setAgent_name(agentName02);
						agentSet2.add(agentVO2);
					}
					agentVO.setAgents(agentSet2);
				}else{
					str="）";
				}
				String agentName01=agentVO.getAgent_name()+"（"+agentVO.getLevel()+"级"+str;
				agentVO.setAgent_name(agentName01);
				list3.add(agentVO);
			}
		}
		return list3;
	}
	@Override
	public void insertAgent(UserVO uservo, AgentVO vo) {
		this.vehicleDao.insertAgent(uservo, vo);
	}

	@Override
	public String getAgentByAgentName(String agent_name) {
		return this.vehicleDao.getAgentByAgentName(agent_name);
	}

	@Override
	public String getUserByUserCode(String user_code, UserVO uservo) {
		return this.vehicleDao.getUserByUserCode(user_code, uservo);
	}

	@Override
	public List getAgentUserByUserId(String user_id) {
		return this.vehicleDao.getAgentUserByUserId(user_id);
	}
	@Override
	public List getAgentUserByUserId2(String user_id) {
		return this.vehicleDao.getAgentUserByUserId2(user_id);
	}

	@Override
	public int modifyAgent(AgentVO vo) {
		return this.vehicleDao.modifyAgent(vo);
	}
	@Override
	public int queryParentId(UserVO uservo){
		List list=this.vehicleDao.queryParentId(uservo);
		if(list!=null&&list.size()>0){
			return 1;
		}else{
			return 0;
		}
	}
	@Override
	public int deleteAgent(String[] userids) {
		List list=this.vehicleDao.getAgentsByUserId(userids);
		if(list!=null&&list.size()>0){
			return 2;
		}else{
			int count=this.vehicleDao.deleteAgent(userids);
			if(count==0){
				for(String userId:userids){
					deleteLogo(userId);
				}
			}
			return count;
		}
	}
	public String deleteLogo(String userId) {
		String logoPath = null;
		ServletContext context = ServletActionContext.getServletContext();
		if (null == context) {
			return null;
		}
		String filepath = ServletActionContext.getServletContext().getRealPath("/upload/" + userId);
		deleteDirectory(filepath);
		return logoPath;
	}
    public boolean deleteFile(String sPath) {
    	boolean result = false;
    	File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            result = true;
        }
        return result;
    }
    public boolean deleteDirectory(String sPath) {
    	boolean result = false;
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        result = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
            	result = deleteFile(files[i].getAbsolutePath());
                if (!result) break;
            } //删除子目录
            else {
            	result = deleteDirectory(files[i].getAbsolutePath());
                if (!result) break;
            }
        }
        if (!result) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
	@Override
	public List getAgentsByUserId(UserVO uservo) {
		return this.vehicleDao.getAgentsByUserId(uservo);
	}

	@Override
	public String getVehicleByUserId(UserVO uservo) {
		return this.vehicleDao.getVehicleByUserId(uservo);
	}

	@Override
	public String getVehicleOfAgents(UserVO uservo, String user_id) {
		return this.vehicleDao.getVehicleOfAgents(uservo, user_id);
	}

	@Override
	public List getVehicleOfNoAgent(String vidOfNoAgent) {
		return this.vehicleDao.getVehicleOfNoAgent(vidOfNoAgent);
	}

	@Override
	public List getVehicleOfOneAgent(String user_id) {
		return this.vehicleDao.getVehicleOfOneAgent(user_id);
	}

	@Override
	public void resetVehicles(String user_id, String ids, int resetFlag,String ip,Integer dengluId) {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		// new Date()为获取当前系统时间
		//System.out.println("操作者id："+user_id+"操作者ip："+ip);
		String time=(df.format(new Date()));

		TagentUser tau=new TagentUser();
		tau.setDengluId(dengluId);
		tau.setIp(ip);
		tau.setTime(Timestamp.valueOf(time));
		tau.setBeiId(user_id);
		if (1 == resetFlag)
			this.vehicleDao.resetVehicles(user_id, ids,tau);
		if ( 2 == resetFlag)
			this.vehicleDao.resetVehicles2(user_id, ids,tau);
	}

	/**
	 * 添加地图标注
	 * @author Jnhuy
	 */
	@Override
	public int addRemarker(RemarkerVO remarker) {
		return this.vehicleDao.addRemarker(remarker);
	}

	/**
	 * 删除地图标注
	 * @author Jnhuy
	 */
	@Override
	public int removeRemarker(RemarkerVO remarker) {
		return this.vehicleDao.removeRemarker(remarker);
	}

	/**
	 * 查询地图标注
	 * @author Jnhuy
	 */
	@Override
	public List<RemarkerVO> queryRemarker(int userId) {
		return this.vehicleDao.queryRemarker(userId);
	}

	/******************尹志刚;完成hnzx整机列表任务**********************/
	//根据用户来查询用户下的代理商
	@Override
	public List queryAgentList2(UserVO uservo) {
		return this.vehicleDao.queryAgentList2(uservo);
	}
	//根据用户userid,来查询tagent_tuser表
	@Override
	public List getAgentListByUserId(String userId) {
		return vehicleDao.getAgentListByUserId(userId);
	}
	//子代分页 查询方法
	@Override
	public PageResponseBean pageQuery(VehicleListPO vlp,UserVO uservo,PageRequestBean pageRequestBean) {
		// 根据 page 和 rows 计算 firstResult 和 maxResults
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();
		//根据用户查询子代的总数
		List list1 = vehicleDao.getSubTotal(vlp,uservo);
		int total = list1.size();
		// 结果数据
		List<VehicleListPO> list = vehicleDao.getVehicleListByUserVo(pageRequestBean,vlp,uservo,firstResult, maxResults);
		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}
	//总代分页 查询方法
	@Override
	public PageResponseBean pageQuery(VehicleListPO vlp,String orgId,PageRequestBean pageRequestBean) {
		// 根据 page 和 rows 计算 firstResult 和 maxResults
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();
		//查询总共有多少条
		List list1 = vehicleDao.getAllTotal(vlp,orgId);
		int total = list1.size();
		// 分页查询结果数据
		List<VehicleListPO> list = vehicleDao.getVehicleListByOrgId(pageRequestBean,vlp,orgId,firstResult, maxResults);
		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}
	//获取子代里的excel下载数据
	@Override
	public List<VehicleListPO> getVehicleListBySub(VehicleListPO vlp,UserVO uservo,
			PageRequestBean pageRequestBean){
		return vehicleDao.getVehicleListByUserVo(pageRequestBean,vlp,uservo,null, null);
	}
	//获取总代理的excel下载数据
	@Override
	public List<VehicleListPO> getVehicleListByAll(VehicleListPO vlp,String orgId,
			PageRequestBean pageRequestBean){
		return vehicleDao.getVehicleListByOrgId(pageRequestBean,vlp,orgId,null, null);
	}

	/******************尹志刚;完成hnzx整机列表任务**********************/

	@Override
	public List getAgentListByUserId2(String userId) {
		return vehicleDao.getAgentListByUserId2(userId);
	}

	@Override
	public List<Tuser> getUsersByUserId(String[] userids) {
		List<Tuser> users = new ArrayList<Tuser>();
		for (String userid : userids) {
			Tuser tuser = vehicleDao.getUserByUserId(userid);
			users.add(tuser);
		}

		return users;
	}

	public Tuser getUserByUserCode(String user_code) {
		return this.vehicleDao.getUserByUserCode(user_code);

	}

	@Override
	public List<VehicleListPO> findUserInDevice(List<Tuser> userList) {
		List<VehicleListPO> list = new ArrayList<VehicleListPO>();
		for (Tuser tuser : userList) {
			VehicleListPO d = vehicleDao.findUserInDevice(tuser);
			if(d == null) {
				return list;
			}
			list.add(d);
		}
		return list;
	}
}
