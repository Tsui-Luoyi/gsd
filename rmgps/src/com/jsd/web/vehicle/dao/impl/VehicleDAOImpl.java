package com.jsd.web.vehicle.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.util.PageRequestBean;
import com.jsd.web.common.PageInfo;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.TagentUser;
import com.jsd.web.po.TbrowseVehicleLog;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TvehicleInfo;
import com.jsd.web.po.UserPrivilege;
import com.jsd.web.vehicle.dao.VehicleDAO;
import com.jsd.web.vehicle.po.Tagent_Tuser;
import com.jsd.web.vehicle.po.VehicleListPO;
import com.jsd.web.vehicle.vo.AgentVO;
import com.jsd.web.vehicle.vo.RemarkerVO;
import com.jsd.web.vehicle.vo.VehicleMapInfoVO;

@SuppressWarnings({"rawtypes","unchecked"})
public class VehicleDAOImpl extends HibernateDaoSupport implements VehicleDAO,
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TvehicleInfo queryVehicleInfobyVno(String vno) {
		TvehicleInfo vinfo = new TvehicleInfo();
		final String sql = "from TvehicleInfo u where u.vno='" + vno + "'";
		List rstList = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(sql);
						return query.list();
					}
				});
		if (rstList != null) {
			for (int i = 0; i < rstList.size(); i++) {
				vinfo = (TvehicleInfo) rstList.get(0);
			}
		}
		return vinfo;
	}

	/**
	 * 如果整车浏览日志里有该整车的日记记录则更新
	 */
	public void updateVehicleLog(TbrowseVehicleLog vlog, Long tvlId) {
		this.getHibernateTemplate().update(vlog);
	}

	/**
	 * 查询整机vno、及其所在坐标值
	 */
	@Override
	public List queryVehicleBaiduMap(String vehicleIds) {
		final String sqlstr = "select vinfo.vno,glc.longitude,glc.latitude,glc.b_Longitude,glc.b_Latitude,glc.addr,vinfo.vm_id,"
				+ "vinfo.vname from tvehicle_info vinfo,"
				+ "gps_locate_current glc where vinfo.vno = glc.vno and vid in("
				+ vehicleIds + ")";

		final List list = new ArrayList();

		List vehicleMapInfoList = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<Object[]> list2 = session.createSQLQuery(sqlstr)
								.list();
						for (Object[] obj : list2) {
							VehicleMapInfoVO ac = new VehicleMapInfoVO();
							ac.setVno((String) obj[0]);
							ac.setLongitude((String) obj[1]);
							ac.setLatitude((String) obj[2]);
							ac.setB_Longitude((String) obj[3]);
							ac.setB_Latitude((String) obj[4]);
							ac.setAddr((String) obj[5]);
							ac.setVmid(((Integer) obj[6]).toString());
							ac.setVname((String) obj[7]);

							list.add(ac);
						}

						return list;
					}
				});

		return vehicleMapInfoList;
	}

	private void persistData(final String sqlstr) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Connection CurConn = session.connection();
				PreparedStatement ps = CurConn.prepareStatement(sqlstr);
				ps.execute();
				ps.close();
				CurConn.close();
				session.flush();
				return null;
			}
		});
	}

	public String getObj(Object obj) {
		if (obj == null) {
			return "";
		} else {
			if (obj instanceof Integer) {
				return ((Integer) obj).toString();
			} else if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof Double) {
				return ((Double) obj).toString();
			} else if (obj instanceof Float) {
				return ((Float) obj).toString();
			} else if (obj instanceof Long) {
				return ((Long) obj).toString();
			} else if (obj instanceof Boolean) {
				return ((Boolean) obj).toString();
			} else if (obj instanceof Date) {
				return ((Date) obj).toString();
			} else if (obj instanceof Timestamp) {
				return ((Timestamp) obj).toString();
			} else {
				return "";
			}
		}
	}

	public int queryCount(final String countSql) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(countSql);
						return Integer
								.parseInt(query.uniqueResult().toString());
					}

				});
	}

	/*
	 * 执行"sql"后"具有"分页功能的内容的list
	 */
	public List queryPagingListBySql(int count, PageInfo pagination,
			final String sql) {
		pagination.setTotalRowsCount(count);
		pagination.pageRenew();
		final int startRow = pagination.getStartRow() - 1;
		final int pageSize = pagination.getPageSize();

		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(startRow);
						query.setMaxResults(pageSize);

						return query.list();
					}
				});
	}

	public List queryPagingListBySql(int count, final String sql) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						return query.list();
					}
				});
	}

	private List constructList(List<Object[]> list) {
		List agentList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setUser_code(getObj(obj[1]));
				vo.setAgent_name(getObj(obj[2]));
				vo.setLock_privilege(getObj(obj[3]));
				vo.setUser_password(getObj(obj[4]));

				agentList.add(vo);
			}
		}
		return agentList;
	}

	private List constructList_6(List<Object[]> list) {
		List agentList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setUser_code(getObj(obj[1]));
				vo.setAgent_name(getObj(obj[2]));
				vo.setLock_privilege(getObj(obj[3]));
				vo.setUser_password(getObj(obj[4]));
				vo.setLevel(getObj(obj[5]));
				vo.setRemark(getObj(obj[6]));

				agentList.add(vo);
			}
		}
		return agentList;
	}

	@Override
	public List queryListAgentInfo2(PageInfo pagination, UserVO uservo) {
		String str = "from tuser u "
				+ "left join tagent a on u.user_id = a.user_id inner join tuser_role r on "
				+ "u.user_id = r.use_user_id where r.role_id = 3 and a.level="
				+ (uservo.getLevel() + 1) + " and u.org_id = "
				+ uservo.getTorgTable().getOrgId();
		String countstr = "select count(u.user_id) " + str;
		String sqlstr = "select u.user_id as user_id,u.user_code as user_code,"
				+ "a.agent_name as agent_name,a.lock_privilege as lock_privilege,"
				+ "u.user_password as user_password,a.level as level,a.remark as remark "
				+ str;
		List list = queryParentId(uservo);
		String str2 = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				str2 += list.get(i);
			} else {
				str2 += list.get(i) + ",";
			}
		}
		if (uservo.getRoleName().equals("代理商用户")
				|| uservo.getRoleName() == "代理商用户") {
			if (list != null && list.size() > 0) {
				countstr += " and u.user_id in (" + str2 + ")";
				sqlstr += " and u.user_id in (" + str2 + ")";
			} else {
				return null;
			}
		}
		int count = queryCount(countstr);
		if (count > 0) {
			return constructList_6(queryPagingListBySql(count, pagination,
					sqlstr));
		}
		return new ArrayList();
	}

	@Override
	public List queryListAgentInfo2(UserVO uservo) {
		String str = "from tuser u "
				+ "left join tagent a on u.user_id = a.user_id inner join tuser_role r on "
				+ "u.user_id = r.use_user_id where r.role_id = 3 and a.level="
				+ (uservo.getLevel() + 1) + " and u.org_id = "
				+ uservo.getTorgTable().getOrgId();
		String countstr = "select count(u.user_id) " + str;
		String sqlstr = "select u.user_id as user_id,u.user_code as user_code,"
				+ "a.agent_name as agent_name,a.lock_privilege as lock_privilege,"
				+ "u.user_password as user_password,a.level as level,a.remark as remark "
				+ str;
		List list = queryParentId(uservo);
		String str2 = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				str2 += list.get(i);
			} else {
				str2 += list.get(i) + ",";
			}
		}
		if (uservo.getRoleName().equals("代理商用户")
				|| uservo.getRoleName() == "代理商用户") {
			if (list != null && list.size() > 0) {
				countstr += " and u.user_id in (" + str2 + ")";
				sqlstr += " and u.user_id in (" + str2 + ")";
			} else {
				return null;
			}
		}
		int count = queryCount(countstr);
		if (count > 0) {
			return constructList_6(queryPagingListBySql(count, sqlstr));
		}
		return new ArrayList();
	}

	private List constructList_3(List<Object[]> list) {
		List agentList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setUser_code(getObj(obj[1]));
				vo.setAgent_name(getObj(obj[2]));
				vo.setLevel(getObj(obj[3]));
				vo.setParent_id(getObj(obj[4]));

				agentList.add(vo);
			}
		}
		return agentList;
	}

	@Override
	public List queryParentId(UserVO uservo) {
		List user_ids = new ArrayList();
		List list = new ArrayList();
		String str = "from tuser u "
				+ "left join tagent a on u.user_id = a.user_id inner join tuser_role r on "
				+ "u.user_id = r.use_user_id where r.role_id = 3";
		final String sql = "select u.user_id as user_id,u.user_code as user_code,"
				+ "a.agent_name as agent_name,a.level as level,"
				+ "a.parent_id as parent_id " + str;
		list = constructList_3((List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						session.close();
						return query.list();
					}
				}));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AgentVO agentVO = (AgentVO) list.get(i);
				String[] pids = agentVO.getParent_id().split(",");
				for (String pid : pids) {
					if ("".equals(pid)) {
						System.out.println(pid);
					}
					int pid2 = Integer.parseInt(pid);
					if (pid2 == uservo.getUserId()) {
						user_ids.add(agentVO.getUser_id());
					}
				}
			}
		} else {
			user_ids = null;
		}
		return user_ids;
	}

	public int getMaxPrivilegeId() {
		Integer privilege_id = (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(
								UserPrivilege.class).setProjection(
								Projections.max("privilegeId"));
						return criteria.list().get(0);
					}
				});
		if (privilege_id == null || privilege_id.equals(null)) {
			return 0;
		} else {
			return privilege_id + 1;
		}
	}

	/**
	 * 有事务处理
	 */
	@Override
	public void insertAgent(UserVO uservo, AgentVO vo) {
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			session.beginTransaction();
			conn = session.connection();

			// 插入用户表
			String str1 = "insert into tuser(user_code,user_name,user_sex,user_type,user_status,org_id,"
					+ "user_password) value('"
					+ vo.getUser_code()
					+ "','"
					+ vo.getUser_code()
					+ "','1','1','1',"
					+ uservo.getTorgTable().getOrgId()
					+ ",'"
					+ vo.getUser_password() + "')";
			ps = conn.prepareStatement(str1);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int user_id = -1;
			if (rs.next()) {
				user_id = rs.getInt(1);
			}

			// 插入用户角色关联表
			String str2 = "insert into tuser_role values(" + user_id + ",3)";
			ps = conn.prepareStatement(str2);
			ps.executeUpdate();

			// 插入用户代理商关联表
			String str3 = "insert into tagent(user_id,agent_name,lock_privilege,level,parent_id,remark) values("
					+ user_id
					+ ",'"
					+ vo.getAgent_name()
					+ "','"
					+ vo.getLock_privilege()
					+ "',"
					+ vo.getLevel()
					+ ",'"
					+ vo.getParent_id() + "','" + vo.getRemark() + "')";
			ps = conn.prepareStatement(str3);
			ps.executeUpdate();

			String privilegeStr = "1,2";
			String agent_privilege = vo.getAgent_privilege();
			String vehicle_privilege = vo.getVehicle_privilege();
			String terminal_privilege = vo.getTerminal_privilege();
			if (agent_privilege == null || agent_privilege.equals(null)
					|| agent_privilege == "") {
			} else {
				privilegeStr += "," + agent_privilege;
			}
			if (vehicle_privilege == null || vehicle_privilege.equals(null)
					|| vehicle_privilege == "") {
			} else {
				privilegeStr += "," + vehicle_privilege;
			}
			if (terminal_privilege == null || terminal_privilege.equals(null)
					|| terminal_privilege == "") {
			} else {
				privilegeStr += "," + terminal_privilege;
			}
			// 插入用户权限关联表
			String str4 = "insert into user_privilege(privilege_id,user_id,privilege_value) values("
					+ getMaxPrivilegeId()
					+ ",'"
					+ user_id
					+ "','"
					+ privilegeStr + "')";
			ps = conn.prepareStatement(str4);
			ps.executeUpdate();
		} catch (Exception e) {
			// System.out.println("数据库操作失败！");
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			try {
				session.getTransaction().commit();
				session.close();
				rs.close();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				// System.out.println("数据库操作对象关闭失败！");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 未作事务处理 暂停使用
	 */
	public void insertAgent2222(UserVO uservo, AgentVO vo) {
		final String MYSQL_ID_SQL = "SELECT @@identity AS ID";
		// 插入用户表
		final String str1 = "insert into tuser(user_code,user_name,user_sex,user_type,user_status,org_id,"
				+ "user_password) value('"
				+ vo.getUser_code()
				+ "','"
				+ vo.getUser_code()
				+ "','1','1','1',"
				+ uservo.getTorgTable().getOrgId()
				+ ",'"
				+ vo.getUser_password() + "')";
		Object user_id = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Connection CurConn = session.connection();
						PreparedStatement ps = CurConn.prepareStatement(str1);
						ps.execute();
						ResultSet rs = ps.executeQuery(MYSQL_ID_SQL);
						rs.next();
						int id = rs.getInt("ID");
						ps.close();
						session.flush();
						return id;
					}
				});

		// 插入用户角色关联表
		String str2 = "insert into tuser_role values(" + user_id
				+ ",3)";
		persistData(str2);

		// 插入用户代理商关联表
		String str3 = "insert into tagent(user_id,agent_name,lock_privilege) values("
				+ user_id
				+ ",'"
				+ vo.getAgent_name()
				+ "','"
				+ vo.getLock_privilege() + "')";
		persistData(str3);
	}

	@Override
	public String getAgentByAgentName(String agent_name) {
		final String sqlstr = "select id as id from tagent a where a.agent_name='"
				+ agent_name
				+ "'"
				+ " union select org_id as org_id from torg_table t where t.org_name='"
				+ agent_name + "'";

		Object result = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Object uniqueResult = session.createSQLQuery(sqlstr)
								.uniqueResult();
						session.close();
						return uniqueResult;
					}
				});

		if (result != null && !"".equals(result)) {
			return "该代理商已被注册";
		} else {
			return "";
		}
	}

	@Override
	public String getUserByUserCode(String user_code, UserVO uservo) {
		final String sqlstr = "select u.user_code as user_code from tuser u where u.user_code = '"
				+ user_code + "'";

		Object result = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sqlstr).uniqueResult();
					}
				});

		if (result != null && !"".equals(result)) {
			return "该用户已被注册";
		} else {
			return "";
		}
	}

	private List constructList_5(List<Object[]> list) {
		List agentList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setUser_code(getObj(obj[1]));
				vo.setAgent_name(getObj(obj[2]));
				vo.setLock_privilege(getObj(obj[3]));
				vo.setUser_password(getObj(obj[4]));
				vo.setPrivileges(getObj(obj[5]));
				vo.setLevel(getObj(obj[6]));
				vo.setRemark(getObj(obj[7]));
				vo.setUserName(getObj(obj[8]));

				agentList.add(vo);
			}
		}
		return agentList;
	}

	@Override
	public List getAgentUserByUserId(String user_id) {
		final String sqlstr = "select u.user_id as user_id,u.user_code as user_code,a.agent_name as agent_name,"
				+ "a.lock_privilege as lock_privilege,u.user_password as user_password,p.privilege_value as"
				+ " privilege_value,a.level as level,a.remark as remark,u.user_name as user_name from tuser u inner"
				+ " join tagent a on u.user_id = a.user_id inner join user_privilege p on u.user_id=p.user_id where"
				+ " u.user_id = " + user_id;
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sqlstr);
						return query.list();
					}
				});

		return constructList_5(list);
	}

	@Override
	public List getAgentUserByUserId2(String user_id) {
		final String sqlstr = "select u.user_id as user_id,u.user_code as user_code,"
				+ "a.agent_name as agent_name,a.lock_privilege as lock_privilege,"
				+ "u.user_password as user_password,a.level,a.parent_id from tuser u "
				+ "inner join tagent a on u.user_id = a.user_id "
				+ "where u.user_id = " + user_id;

		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sqlstr);
						return query.list();
					}
				});

		return constructList_2(list);
	}

	private List constructList_2(List<Object[]> list) {
		List agentList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setUser_code(getObj(obj[1]));
				vo.setAgent_name(getObj(obj[2]));
				vo.setLock_privilege(getObj(obj[3]));
				vo.setUser_password(getObj(obj[4]));
				vo.setLevel(getObj(obj[5]));
				vo.setParent_id(getObj(obj[6]));

				agentList.add(vo);
			}
		}
		return agentList;
	}

	/**
	 * 事务处理
	 */
	@Override
	public int modifyAgent(AgentVO vo) {
		UserVO uservo = new UserVO();
		uservo.setUserId(Integer.parseInt(vo.getUser_id()));
		List list2 = queryParentId(uservo);
		String str4 = "";
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			session.beginTransaction();
			conn = session.connection();

			// 更新用户表
			String str1 = "update tuser set user_code='" + vo.getUser_code()
					+ "',user_name='" + vo.getUserName() + "',user_password='"
					+ vo.getUser_password() + "' where user_id="
					+ vo.getUser_id();
			ps = conn.prepareStatement(str1);
			ps.executeUpdate();

			if (vo.getLevel() == "0" || vo.getLevel().equals("0")) {
				// 更新公司表
				String str6 = "update torg_table set org_name='"
						+ vo.getAgent_name() + "' where org_id=(select org_id"
						+ " from tuser where user_id=" + vo.getUser_id() + ")";
				ps = conn.prepareStatement(str6);
				ps.executeUpdate();
			}

			// 更新用户代理商关联表
			String str2 = "update tagent set agent_name='" + vo.getAgent_name()
					+ "',lock_privilege='" + vo.getLock_privilege()
					+ "',remark='" + vo.getRemark() + "' where user_id="
					+ vo.getUser_id();
			ps = conn.prepareStatement(str2);
			ps.executeUpdate();

			if (list2 != null && list2.size() > 0) {
				for (int i = 0; i < list2.size(); i++) {
					if (i == list2.size() - 1) {
						str4 += list2.get(i);
					} else {
						str4 += list2.get(i) + ",";
					}
				}
				// 代理商锁车权限被回收时，下级代理商的锁车权限全部回收
				if (vo.getLock_privilege().equals("0")
						|| vo.getLock_privilege() == "0") {
					// 更新用户下级代理商关联表
					String str3 = "update tagent set lock_privilege='0' where user_id in("
							+ str4 + ")";
					ps = conn.prepareStatement(str3);
					ps.executeUpdate();
				}
			}

			String privilegeStr = "1,2";
			if (vo.getAgent_privilege() != null
					&& !vo.getAgent_privilege().equals(null)) {
				privilegeStr += "," + vo.getAgent_privilege();
			}
			if (vo.getVehicle_privilege() != null
					&& (!vo.getVehicle_privilege().equals(null))) {
				privilegeStr += "," + vo.getVehicle_privilege();
			}
			if (vo.getTerminal_privilege() != null
					&& (!vo.getTerminal_privilege().equals(null))) {
				privilegeStr += "," + vo.getTerminal_privilege();
			}
			// 修改用户权限关联表
			String str5 = "update user_privilege set privilege_value='"
					+ privilegeStr + "' where  user_id=" + vo.getUser_id();
			ps = conn.prepareStatement(str5);
			ps.executeUpdate();

			return 0;
		} catch (Exception e) {
			// System.out.println("数据库操作失败！");
			e.printStackTrace();
			session.getTransaction().rollback();
			return 1;
		} finally {
			try {
				ps.close();
				conn.close();
				session.getTransaction().commit();
				session.close();
			} catch (SQLException e) {
				// System.out.println("数据库操作对象关闭失败！");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 事务处理
	 */
	@Override
	public int deleteAgent(String[] userids) {
		String ids = parseToSQLStringComma(userids);
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			session.beginTransaction();
			conn = session.connection();

			// 删除用户角色关联表
			String str1 = "delete from tuser_role where use_user_id in (" + ids
					+ ")";
			ps = conn.prepareStatement(str1);
			ps.executeUpdate();

			// 删除用户代理商关联表
			String str2 = "delete from tagent where user_id in (" + ids + ")";
			ps = conn.prepareStatement(str2);
			ps.executeUpdate();

			// 删除用户表
			String str4 = "delete from user_privilege where user_id in (" + ids
					+ ")";
			ps = conn.prepareStatement(str4);
			ps.executeUpdate();

			// 删除用户表
			String str3 = "delete from tuser where user_id in (" + ids + ")";
			ps = conn.prepareStatement(str3);
			ps.executeUpdate();
			return 0;
		} catch (Exception e) {
			// System.out.println("数据库操作失败！");
			e.printStackTrace();
			session.getTransaction().rollback();
			return 1;
		} finally {
			try {
				ps.close();
				conn.close();
				session.getTransaction().commit();
				session.close();
			} catch (SQLException e) {
				// System.out.println("数据库操作对象关闭失败！");
				e.printStackTrace();
			}
		}
	}

	private String parseToSQLStringComma(Object strArray[]) {
		if (strArray == null || strArray.length == 0)
			return "'notExistId'";
		String myStr = "";
		for (int i = 0; i < strArray.length - 1; i++)
			myStr = (new StringBuilder(String.valueOf(myStr))).append("'")
					.append(strArray[i]).append("',").toString();

		myStr = (new StringBuilder(String.valueOf(myStr))).append("'")
				.append(strArray[strArray.length - 1]).append("'").toString();
		return myStr;
	}

	@Override
	public List getAgentsByUserId(UserVO uservo) {
		if (uservo.getRoleName().equals("代理商用户")
				|| uservo.getRoleName() == "代理商用户") {
			List list2 = queryParentId(uservo);
			String str2 = "";
			for (int i = 0; i < list2.size(); i++) {
				if (i == list2.size() - 1) {
					str2 += list2.get(i);
				} else {
					str2 += list2.get(i) + ",";
				}
			}
			String str = "";
			if (list2 != null && list2.size() > 0) {
				str = " and a.user_id in (" + str2 + ")";
				final String sqlstr = "select a.user_id as user_id,a.agent_name as agent_name from tagent a inner join tuser u "
						+ "on a.user_id = u.user_id where a.level="
						+ (uservo.getLevel() + 1)
						+ " and u.org_id = "
						+ uservo.getTorgTable().getOrgId() + str;
				List list = (List) this.getHibernateTemplate().execute(
						new HibernateCallback() {
							@Override
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException {
								Query query = session.createSQLQuery(sqlstr);
								return query.list();
							}
						});
				return constructList2(list);
			} else {
				return null;
			}
		} else {
			final String sqlstr = "select a.user_id as user_id,a.agent_name as agent_name from tagent a inner join tuser u "
					+ "on a.user_id = u.user_id where a.level="
					+ (uservo.getLevel() + 1)
					+ " and u.org_id = "
					+ uservo.getTorgTable().getOrgId();
			List list = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sqlstr);
							return query.list();
						}
					});
			return constructList2(list);
		}
	}

	@Override
	public List getAgentsByUserId(String[] userids) {
		String ids = parseToSQLStringComma(userids);
		for (String user_id : userids) {
			UserVO userVo = new UserVO();
			userVo.setUserId(Integer.parseInt(user_id));
			List list2 = queryParentId(userVo);
			if (list2 != null && list2.size() > 0) {
				return list2;
			}
		}
		return null;
	}

	private List constructList2(List<Object[]> list) {
		List agentList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setAgent_name(getObj(obj[1]));

				agentList.add(vo);
			}
		}
		return agentList;
	}

	@Override
	public String getVehicleByUserId(UserVO uservo) {// 登录用户所属组织下的整机vid列表
		final String sqlstr;
		if (uservo.getRoleName().equals("代理商用户")
				|| uservo.getRoleName() == "代理商用户") {
			sqlstr = "select a.vid as vid from tagent a where a.user_id = "
					+ uservo.getUserId();
		} else {
			sqlstr = "select tv.vno as vid from tv_org_rel tv where tv.org_id = "
					+ uservo.getTorgTable().getOrgId();
		}

		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sqlstr);
						return query.list();
					}
				});

		String vehicleIds = "";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && !"".equals(list.get(i))) {
					vehicleIds = vehicleIds + "," + list.get(i).toString();
				}
			}
		}
		return vehicleIds;
	}

	@Override
	public String getVehicleOfAgents(UserVO uservo, String user_id) {
		List list2 = queryParentId(uservo);
		String str2 = "";
		for (int i = 0; i < list2.size(); i++) {
			if (i == list2.size() - 1) {
				str2 += list2.get(i);
			} else {
				str2 += list2.get(i) + ",";
			}
		}
		String str = "";
		if (uservo.getRoleName().equals("代理商用户")
				|| uservo.getRoleName() == "代理商用户") {
			if (list2 != null && list2.size() > 0) {
				str = " where a.user_id in (" + str2 + ")";
			}
		}
		final String sqlstr;
		if (uservo.getRoleName().equals("代理商用户")
				|| uservo.getRoleName() == "代理商用户") {
			sqlstr = "select a.vid as vid from tagent a" + str;
		} else {
			sqlstr = "select a.vid as vid from tagent a inner join tuser u "
					+ "on a.user_id = u.user_id where u.org_id = "
					+ uservo.getTorgTable().getOrgId();
		}
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sqlstr);
						return query.list();
					}
				});

		String vehicleIds = "";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && !"".equals(list.get(i))) {
					vehicleIds = vehicleIds + "," + list.get(i).toString();
				}
			}
		}

		return vehicleIds;
	}

	@Override
	public List getVehicleOfNoAgent(String vidOfNoAgent) {
		List list = new ArrayList();
		if (vidOfNoAgent != null && !"".equals(vidOfNoAgent)) {// 如果代理商有车
			final String sqlstr2 = "select v.vid as vid,v.vno as vno,v.vname as vname,dv.dno as dno from"
					+ " tvehicle_info v left join tvv_band tv on tv.vid=v.vid left join devterm_info dv on dv.did=tv.did "
					+ "where v.vid in ("
					+ parseToSQLStringComma(vidOfNoAgent.split(",")) + ")";

			List vehicleList = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sqlstr2);
							return query.list();
						}
					});

			list = constructList3(vehicleList);
		}

		return list;
	}

	@Override
	public List getVehicleOfOneAgent(String user_id) {
		final String sqlstr1 = "select a.vid as vid from tagent a where a.user_id = "
				+ user_id;

		String vids = (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sqlstr1);
						return query.uniqueResult();
					}
				});

		List list = new ArrayList();
		if (vids != null && !"".equals(vids)) {// 如果代理商有车
			final String sqlstr2 = "select v.vid as vid,v.vno as vno,v.vname as vname,dv.dno as dno from"
					+ " tvehicle_info v left join tvv_band tv on tv.vid=v.vid left join devterm_info dv on dv.did=tv.did "
					+ "where v.vid in ("
					+ parseToSQLStringComma(vids.split(",")) + ")";

			List vehicleList = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sqlstr2);
							return query.list();
						}
					});

			list = constructList3(vehicleList);
		}

		return list;
	}

	private List constructList3(List<Object[]> list) {
		List vehicleList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setVid(getObj(obj[0]));
				vo.setVno(getObj(obj[1]));
				vo.setVname(getObj(obj[2]));
				vo.setDno(getObj(obj[3]));

				vehicleList.add(vo);
			}
		}
		return vehicleList;
	}

	/**
	 *
	 */
	@Override
	public void resetVehicles(String user_id, String ids, TagentUser tau) {
		tau.setDes("向右调往未代理" + ids);
		// 默认ids是已被选中的值
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String oids = null;
		try {
			conn = session.connection();
			String str1 = "select vid from tagent where user_id = " + user_id;
			ps = conn.prepareStatement(str1);
			rs = ps.executeQuery();
			rs.next();
			oids = rs.getString("vid");
		} catch (Exception e) {
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String[] idsarr = ids.split(",");
		String[] oidsarr = oids.split(",");
		StringBuilder sb = new StringBuilder();
		for (String oid : oidsarr) {
			boolean flag = true;
			for (String id : idsarr) {
				if (oid.equals(id)) // 如果idarr中包含oid，则说明该oid是要被排除的，sb中就不要添加它
					flag = false;
			}
			if (flag)
				sb.append(oid + ",");
		}

		ids = sb.toString();
		if (ids.length() > 0)
			ids = ids.substring(0, ids.length() - 1); // 含头不含尾

		String sqlstr1 = "update tagent a set a.vid = '" + ids
				+ "' where a.user_id = " + user_id;

		String tausql = "insert into tagentuser(ip,time,dengluId,beiId,des) value ('"
				+ tau.getIp()
				+ "','"
				+ tau.getTime()
				+ "','"
				+ tau.getDengluId()
				+ "','"
				+ tau.getBeiId()
				+ "','"
				+ tau.getDes() + "')";
		persistData(tausql);
		persistData(sqlstr1);
	}

	/**
	 *
	 */
	@Override
	public void resetVehicles2(String user_id, String ids, TagentUser tau) {
		tau.setDes("向左调往已代理" + ids);
		String sqlstr1 = "update tagent a set a.vid = '" + ids
				+ "' where a.user_id = " + user_id;
		String tausql = "insert into tagentuser(ip,time,dengluId,beiId,des) value ('"
				+ tau.getIp()
				+ "','"
				+ tau.getTime()
				+ "','"
				+ tau.getDengluId()
				+ "','"
				+ tau.getBeiId()
				+ "','"
				+ tau.getDes() + "')";
		persistData(tausql);
		persistData(sqlstr1);
	}

	/**
	 * 查询代理商车辆
	 */
	@Override
	public String queryVidOfAgent(UserVO uservo) {
		final String sqlstr1 = "select a.vid as vid from tagent a where a.user_id = "
				+ uservo.getUserId();
		String vidOfAgent = "''";
		String vids = (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sqlstr1);
						session.close();
						return query.uniqueResult();
					}
				});
		if (vids != null && !"".equals(vids)) {
			vidOfAgent = vids;
		}

		return vidOfAgent;
	}

	@Override
	public List queryAgentList(UserVO uservo) {
		List list = new ArrayList();
		if (uservo.getTorgTable().getOrgId() == 1) {// 系统管理员即admin用户
			final String sql = "select a.user_id as user_id,a.agent_name as agent_name from tagent a";
			list = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sql);
							return query.list();
						}
					});
			return constructList6(list);
		} else if (!uservo.getRoleName().equals("代理商用户")) {// 非代理商用户
			final String sql = "select a.user_id as user_id,a.agent_name as agent_name from tuser u "
					+ "inner join tagent a on a.user_id = u.user_id where u.org_id = "
					+ uservo.getTorgTable().getOrgId();
			list = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sql);
							return query.list();
						}
					});
			return constructList6(list);
		} else {// 代理商用户查找
			List list2 = queryParentId(uservo);
			String str2 = "";
			for (int i = 0; i < list2.size(); i++) {
				if (i == list2.size() - 1) {
					str2 += list2.get(i);
				} else {
					str2 += list2.get(i) + ",";
				}
			}
			String str = "";
			if (list2 != null && list2.size() > 0) {
				str = " and u.user_id in (" + str2 + ")";
				final String sql = "select a.user_id as user_id,a.agent_name as agent_name from tuser u "
						+ "inner join tagent a on a.user_id = u.user_id where u.org_id = "
						+ uservo.getTorgTable().getOrgId() + str;
				list = (List) this.getHibernateTemplate().execute(
						new HibernateCallback() {
							@Override
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException {
								Query query = session.createSQLQuery(sql);
								return query.list();
							}
						});
				return constructList6(list);
			} else {
				return null;
			}
		}
	}

	private List constructList6(List<Object[]> list) {
		if (list.size() > 0) {
			List agentList = new ArrayList();
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setAgent_name(getObj(obj[1]));
				agentList.add(vo);
			}
			list = agentList;
		}
		return list;
	}

	/**
	 * 添加地图标注
	 *
	 * @author Jnhuy
	 */
	@Override
	public int addRemarker(RemarkerVO remarker) {

		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		Transaction transaction = session.beginTransaction();
		conn = session.connection();

		String str1 = "Insert into tremarker(remarkerLat,remarkerLng,remarkerInfo,remarkerId,remarkerTitle,USER_ID) value ('"
				+ remarker.getRemarkerLat()
				+ "','"
				+ remarker.getRemarkerLng()
				+ "','"
				+ remarker.getRemarkerInfo()
				+ "','"
				+ remarker.getRemarkerId()
				+ "','"
				+ remarker.getRemarkerTitle()
				+ "',"
				+ remarker.getTuser().getUserId() + ")";
		try {
			ps = conn.prepareStatement(str1);
			i = ps.executeUpdate();
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;

	}

	/**
	 * 删除地图标注
	 *
	 * @author Jnhuy
	 */
	@Override
	public int removeRemarker(RemarkerVO remarker) {
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Transaction transaction = session.beginTransaction();
		conn = session.connection();
		int i = 0;

		String str1 = "delete from tremarker where remarkerId="
				+ remarker.getRemarkerId();
		try {
			ps = conn.prepareStatement(str1);
			i = ps.executeUpdate();
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	/**
	 * 查询地图标注
	 *
	 * @author Jnhuy
	 */
	@Override
	public List<RemarkerVO> queryRemarker(int userId) {
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = session.connection();
		List<RemarkerVO> list = null;

		String str1 = "select * from tremarker where USER_ID = " + userId;
		try {
			ps = conn.prepareStatement(str1);
			rs = ps.executeQuery();
			list = new ArrayList<RemarkerVO>();
			while (rs.next()) {
				RemarkerVO remarker = new RemarkerVO();
				remarker.setId(rs.getInt("id"));
				remarker.setRemarkerLat(rs.getString("remarkerLat"));
				remarker.setRemarkerLng(rs.getString("remarkerLng"));
				remarker.setRemarkerInfo(rs.getString("remarkerInfo"));
				remarker.setRemarkerId(rs.getString("remarkerId"));
				remarker.setRemarkerTitle(rs.getString("remarkerTitle"));
				list.add(remarker);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/****************** 尹志刚;完成hnzx整机列表任务 **********************/
	@Override
	public List queryAgentList2(UserVO uservo) {
		List list = new ArrayList();
		if (uservo.getTorgTable().getOrgId() == 1) {// 系统管理员即admin用户
			final String sql = "select a.user_id as user_id,a.agent_name as agent_name ,a.agent_id as agent_id from tagent_tuser a";
			list = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sql);
							return query.list();
						}
					});
			return constructList6_2(list);
		} else if (!uservo.getRoleName().equals("代理商用户")) {// 非代理商用户,即普通用户
			final String sql = "select a.user_id as user_id,a.agent_name as agent_name,a.agent_id as agent_id from tuser u "
					+ "inner join tagent_tuser a on a.user_id = u.user_id where u.org_id = "
					+ uservo.getTorgTable().getOrgId();
			list = (List) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createSQLQuery(sql);
							return query.list();
						}
					});
			return constructList6_2(list);
		} else {// 代理商用户查找
			List list2 = queryParentId2(uservo);
			String str2 = "";
			for (int i = 0; i < list2.size(); i++) {
				if (i == list2.size() - 1) {
					str2 += list2.get(i);
				} else {
					str2 += list2.get(i) + ",";
				}
			}
			String str = "";
			if (list2 != null && list2.size() > 0) {
				str = " and u.user_id in (" + str2 + ")";
				final String sql = "select a.user_id as user_id,a.agent_name as agent_name,a.agent_id as agent_id from tuser u "
						+ "inner join tagent_tuser a on a.user_id = u.user_id where u.org_id = "
						+ uservo.getTorgTable().getOrgId() + str;
				list = (List) this.getHibernateTemplate().execute(
						new HibernateCallback() {
							@Override
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException {
								Query query = session.createSQLQuery(sql);
								return query.list();
							}
						});
				return constructList6_2(list);
			} else {
				return null;
			}
		}
	}

	private List constructList6_2(List<Object[]> list) {
		if (list.size() > 0) {
			List agentList = new ArrayList();
			for (Object[] obj : list) {
				AgentVO vo = new AgentVO();
				vo.setUser_id(getObj(obj[0]));
				vo.setAgent_name(getObj(obj[1]));
				vo.setAgent_id((Integer) obj[2]);
				agentList.add(vo);
			}
			list = agentList;
		}
		return list;
	}

	public List queryParentId2(UserVO uservo) {
		List user_ids = new ArrayList();
		List list = new ArrayList();
		String str = "from tuser u "
				+ "left join tagent_tuser a on u.user_id = a.user_id inner join tuser_role r on "
				+ "u.user_id = r.use_user_id where r.role_id = 3";
		final String sql = "select u.user_id as user_id,u.user_code as user_code,"
				+ "a.agent_name as agent_name,a.level as level,"
				+ "a.parent_id as parent_id " + str;
		list = constructList_3((List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						session.close();
						return query.list();
					}
				}));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AgentVO agentVO = (AgentVO) list.get(i);
				String[] pids = agentVO.getParent_id().split(",");
				for (String pid : pids) {
					int pid2 = Integer.parseInt(pid);
					if (pid2 == uservo.getUserId()) {
						user_ids.add(agentVO.getUser_id());
					}
				}
			}
		} else {
			user_ids = null;
		}
		return user_ids;
	}

	// 根据用户userid,来查询tagent_tuser表
	@Override
	public List getAgentListByUserId(String userId) {
		final String sql = "select * from tagent_tuser where user_id = '"
				+ userId + "'";
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tagent_Tuser.class);
						session.close();
						return query.list();
					}
				});
		return list;
	}

	// 根据用户查询子代的总数
	@Override
	public List getSubTotal(VehicleListPO vlp, UserVO uservo) {
		List tagentId = getTagentId(uservo);
		List subTagentId = getSubTagentId(uservo);
		return queryAllVehicleList(vlp, tagentId, subTagentId);
	}

	// 根据userId查询tagent_tuser表，主要得到tagent_tuser表的agent_id
	public List getTagentId(UserVO uservo) {
		final String sql = "select * from tagent_tuser where user_id = "
				+ uservo.getUserId().toString();
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tagent_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	// 根据userId查询tagent_tuser,主要看子代理用户下面是否还有子代
	public List getSubTagentId(UserVO uservo) {
		final String sql2 = "select * from tagent_tuser where find_in_set('"
				+ uservo.getUserId().toString() + "',parent_id)";
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql2).addEntity(
								Tagent_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	// 根据得到的agent表的id,vehicle_list表查询数据
	public List queryAllVehicleList(VehicleListPO vlp, List list, List list2) {
		final String ss;
		String sql3 = "select * from vehicle_list where 1=1";
		if (vlp != null) {
			if (vlp.getVname_list() != null
					&& vlp.getVname_list().trim().length() > 0) {
				sql3 += " and vname like '%" + vlp.getVname_list().trim()
						+ "%'";
			}
			if (vlp.getDno_list() != null
					&& vlp.getDno_list().trim().length() > 0) {
				sql3 += " and dno like '%" + vlp.getDno_list().trim() + "%'";
			}
			if (vlp.getAgent_id_list() != null
					&& vlp.getAgent_id_list().trim().length() > 0) {
				if (!vlp.getAgent_id_list().equals("-1")) {
					sql3 += " and agent_id = " + vlp.getAgent_id_list().trim();
				}
			}
			if (vlp.getOffcount() != null && vlp.getOffcount() != 0) {
				if ((vlp.getOffcount() > 0 && vlp.getOffcount() < 3)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == 3)) {
					sql3 += " and offcount >= " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == -1)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
			}
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Tagent_Tuser t = (Tagent_Tuser) list.get(i);
				sql3 += " and ( agent_id = " + t.getAgent_id();
			}
		}
		if (list2 != null && list2.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				Tagent_Tuser t = (Tagent_Tuser) list2.get(i);
				sql3 += " or agent_id = " + t.getAgent_id();
			}
		}
		sql3 += " ) ";
		ss = sql3;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(ss).addEntity(
								VehicleListPO.class);
						return query.list();
					}
				});
		return l;
	}

	// 子代理的列表获取数据 和 execle下载
	@Override
	public List<VehicleListPO> getVehicleListByUserVo(
			PageRequestBean pageRequestBean, VehicleListPO vlp, UserVO uservo,
			Integer firstResult, Integer maxResults) {
		List tagentId = getTagentId(uservo);
		List subTagentId = getSubTagentId(uservo);

		final String ss;
		String sql3 = "select * from vehicle_list where 1=1 ";
		if (vlp != null) {
			if (vlp.getVname_list() != null
					&& vlp.getVname_list().trim().length() > 0) {
				sql3 += " and vname like '%" + vlp.getVname_list().trim()
						+ "%'";
			}
			if (vlp.getDno_list() != null
					&& vlp.getDno_list().trim().length() > 0) {
				sql3 += " and dno like '%" + vlp.getDno_list().trim() + "%'";
			}
			if (vlp.getAgent_id_list() != null
					&& vlp.getAgent_id_list().trim().length() > 0) {
				if (!vlp.getAgent_id_list().equals("-1")) {
					sql3 += " and agent_id = " + vlp.getAgent_id_list().trim();
				}
			}
			if (vlp.getOffcount() != null && vlp.getOffcount() != 0) {
				if ((vlp.getOffcount() > 0 && vlp.getOffcount() < 3)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == 3)) {
					sql3 += " and offcount >= " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == -1)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
			}
		}
		if (tagentId != null && tagentId.size() > 0) {
			for (int i = 0; i < tagentId.size(); i++) {
				Tagent_Tuser t = (Tagent_Tuser) tagentId.get(i);
				sql3 += " and (agent_id = " + t.getAgent_id();
			}
		}
		if (subTagentId != null && subTagentId.size() > 0) {
			for (int i = 0; i < subTagentId.size(); i++) {
				Tagent_Tuser t = (Tagent_Tuser) subTagentId.get(i);
				sql3 += " or agent_id = " + t.getAgent_id();
			}
		}
		sql3 += " ) ";
		if (pageRequestBean != null && pageRequestBean.getSort().equals("time")) {
			pageRequestBean.setSort("update_time");
		}
		if (pageRequestBean != null) {
			sql3 += " order by " + pageRequestBean.getSort() + " "
					+ pageRequestBean.getOrder();
		} else {
			sql3 += " order by " + " update_time DESC ";
		}
		if (firstResult != null && maxResults != null) {
			sql3 += " limit " + firstResult + "," + maxResults;
		}

		ss = sql3;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(ss).addEntity(
								VehicleListPO.class);
						return query.list();
					}
				});
		return l;
	}

	// 根据用户查询总代的总数
	@Override
	public List getAllTotal(VehicleListPO vlp, String orgId) {
		final String ss;
		String sql3 = "select * from vehicle_list where 1=1 ";
		if (vlp != null) {
			if (vlp.getVname_list() != null
					&& vlp.getVname_list().trim().length() > 0) {
				sql3 += " and vname like '%" + vlp.getVname_list().trim()
						+ "%'";
			}
			if (vlp.getDno_list() != null
					&& vlp.getDno_list().trim().length() > 0) {
				sql3 += " and   dno like '%" + vlp.getDno_list().trim() + "%'";
			}
			if (vlp.getAgent_id_list() != null
					&& vlp.getAgent_id_list().trim().length() > 0) {
				if (!vlp.getAgent_id_list().equals("-1")) {
					sql3 += " and agent_id = " + vlp.getAgent_id_list().trim();
				}
			}
			if (vlp.getOffcount() != null && vlp.getOffcount() != 0) {
				if ((vlp.getOffcount() > 0 && vlp.getOffcount() < 3)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == 3)) {
					sql3 += " and offcount >= " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == -1)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
			}
		}
		if (orgId != null) {
			sql3 += " and org_id= '" + orgId + "'";
		}
		ss = sql3;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(ss).addEntity(
								VehicleListPO.class);
						return query.list();
					}
				});
		return l;
	}

	// 总代理的列表获取数据 和 execle下载
	@Override
	public List<VehicleListPO> getVehicleListByOrgId(
			PageRequestBean pageRequestBean, VehicleListPO vlp, String orgId,
			Integer firstResult, Integer maxResults) {
		final String ss;
		String sql3 = "select * from vehicle_list where 1=1 ";
		if (vlp != null) {
			if (vlp.getVname_list() != null
					&& vlp.getVname_list().trim().length() > 0) {
				sql3 += " and vname like '%" + vlp.getVname_list().trim()
						+ "%'";
			}
			if (vlp.getDno_list() != null
					&& vlp.getDno_list().trim().length() > 0) {
				sql3 += " and   dno like '%" + vlp.getDno_list().trim() + "%'";
			}
			if (vlp.getAgent_id_list() != null
					&& vlp.getAgent_id_list().trim().length() > 0) {
				if (!vlp.getAgent_id_list().equals("-1")) {
					sql3 += " and agent_id = " + vlp.getAgent_id_list().trim();
				}
			}
			if (vlp.getOffcount() != null && vlp.getOffcount() != 0) {
				if ((vlp.getOffcount() > 0 && vlp.getOffcount() < 3)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == 3)) {
					sql3 += " and offcount >= " + vlp.getOffcount();
				}
				if ((vlp.getOffcount() == -1)) {
					sql3 += " and offcount = " + vlp.getOffcount();
				}
			}
		}
		if (orgId != null) {
			sql3 += " and org_id= '" + orgId + "'";
		}
		if (pageRequestBean != null && pageRequestBean.getSort().equals("time")) {
			pageRequestBean.setSort("update_time");
		}
		if (pageRequestBean != null) {
			sql3 += " order by " + pageRequestBean.getSort() + " "
					+ pageRequestBean.getOrder();
		} else {
			sql3 += " order by " + " update_time DESC ";
		}
		if (firstResult != null && maxResults != null) {
			sql3 += " limit " + firstResult + "," + maxResults;
		}

		ss = sql3;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(ss).addEntity(
								VehicleListPO.class);
						return query.list();
					}
				});
		return l;
	}

	// 把list里面的对象封装到map中，并把有map在封装到新的list中。
	public List changePO(List list) {
		List p = new ArrayList();
		Iterator i = list.iterator();
		while (i.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			Object[] obj = (Object[]) i.next();
			map.put("id", obj[0].toString());
			map.put("vname", obj[1].toString());
			map.put("dno", obj[2].toString());
			p.add(map);
		}
		return p;
	}

	/****************** 尹志刚;完成hnzx整机列表任务 **********************/
	@Override
	public List getAgentListByUserId2(String userId) {
		final String sql = "select * from tagent_tuser where user_id = '"
				+ userId + "'";
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tagent_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public Tuser getUserByUserId(String userid) {
		Integer user_id = Integer.parseInt(userid);
		SessionFactory sessionFactory = this.getHibernateTemplate()
				.getSessionFactory();
		org.hibernate.classic.Session session = sessionFactory.openSession();
		String sql = "select USER_CODE from tuser where USER_ID=" + user_id;
		Tuser u = null;
		try {
			String uniqueResult = (String) session.createSQLQuery(sql)
					.uniqueResult();
			u = new Tuser();
			u.setUserCode(uniqueResult);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return u;
	}

	@Override
	public Tuser getUserByUserCode(String user_code) {
		SessionFactory sessionFactory = this.getHibernateTemplate()
				.getSessionFactory();
		org.hibernate.classic.Session session = sessionFactory.openSession();
		String sql = "select USER_ID from tuser where USER_CODE='" + user_code
				+ "'";
		Tuser u = null;
		try {
			Integer uniqueResult = (Integer) session.createSQLQuery(sql)
					.uniqueResult();
			u = new Tuser();
			u.setUserId(uniqueResult);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return u;
	}

	@Override
	public VehicleListPO findUserInDevice(Tuser user) {
		String sql = "select * from vehicle_list where agent_name = '"
				+ user.getUserCode() + "'";
		SessionFactory sessionFactory = this.getHibernateTemplate()
				.getSessionFactory();
		org.hibernate.classic.Session session = sessionFactory.openSession();
		List<VehicleListPO> device = null;
		try {
			device = session.createSQLQuery(sql)
					.addEntity(VehicleListPO.class).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		if (device.size() > 0) {
			return device.get(0);
		} else {
			return null;
		}
	}
}
