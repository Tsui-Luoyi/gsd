package com.jsd.web.vehicle.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.util.PageRequestBean;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.dao.VehicleMnDao;
import com.jsd.web.vehicle.po.History;
import com.jsd.web.vehicle.po.Tagent_Tuser;
import com.jsd.web.vehicle.vo.AgentVO;

@SuppressWarnings({"rawtypes","unchecked"})
public class VehicleMnDaoImpl extends HibernateDaoSupport implements VehicleMnDao{

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

	//根据用户查询子代的总数
	@Override
	public List getTotal(History h,UserVO uservo) {
		List tagentId = getTagentId(uservo);
		List subTagentId = getSubTagentId(uservo);
		return getEmergentList(h,tagentId,subTagentId,uservo);
	}
	//根据userId查询tagent表，主要得到tagent表的user_id
	public List getTagentId(UserVO uservo){
		final String sql = "select * from tagent_tuser where user_id = "+uservo.getUserId().toString();
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(Tagent_Tuser.class);
						return query.list();
					}
				});
		return list;
	}
	//根据userId查询tagent,主要看子代理用户下面是否还有子代
	public List getSubTagentId(UserVO uservo){
		final String sql2 = "select * from tagent_tuser where find_in_set('" + uservo.getUserId().toString() + "',parent_id)";
		List list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql2).addEntity(Tagent_Tuser.class);
				return query.list();
			}
		});
		return list;
	}

	//根据得到的agent_tuser表的user_id,查询mn_cmd_history_2表数据
	public List getEmergentList(History h,List list, List list2,UserVO uservo){
		final String ss;
		String sql3 = "select m.*,t.user_name from mn_cmd_history_2 m " +
				"inner join tuser t on m.user_id = t.user_id where devterm_state = '1' ";
		if(h!=null){
			if(h.getVno() !=null && h.getVno().trim().length()>0){
				sql3 += " and m.vno like '%" + h.getVno() + "%'";
			}
			if(h.getUser_id() !=null){
				sql3 += " and m.user_id =" + h.getUser_id();
			}
		}
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Tagent_Tuser t = (Tagent_Tuser) list.get(i);
				sql3 += " and ( m.user_id = " + t.getUser_id();
			}
		}else{
			sql3 += " and ( m.user_id = " + uservo.getUserId();
		}
		if(list2!=null && list2.size()>0){
			for(int i=0;i<list2.size();i++){
				Tagent_Tuser t = (Tagent_Tuser) list2.get(i);
				sql3 += " or m.user_id = " + t.getUser_id();
			}
		}
		sql3 += " ) ";
		ss = sql3;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(ss).addEntity(History.class);
						return query.list();
					}
				});
		return l;
	}

	@Override
	public List getEmergentListByUserVo(PageRequestBean pageRequestBean,
			History h, UserVO uservo,Integer firstResult, Integer maxResults) {
		List userId = getTagentId(uservo);
		List subUserId = getSubTagentId(uservo);

		final String ss;
		String sql3 = "select m.*,t.user_name from mn_cmd_history_2 m " +
				"inner join tuser t on m.user_id = t.user_id where devterm_state = '1' ";
		if(h!=null){
			if(h.getVno() !=null && h.getVno().trim().length()>0){
				sql3 += " and m.vno like '%" + h.getVno() + "%'";
			}
			if(h.getUser_id() !=null){
				sql3 += " and m.user_id =" + h.getUser_id();
			}
		}

		if(userId !=null && userId.size()>0){
			for(int i=0;i<userId.size();i++){
				Tagent_Tuser t = (Tagent_Tuser) userId.get(i);
				sql3 += " and ( m.user_id = " + t.getUser_id();
			}
		}else{
			sql3 += " and ( m.user_id = " + uservo.getUserId();
		}

		if(subUserId!=null && subUserId.size()>0){
			for(int i=0;i<subUserId.size();i++){
				Tagent_Tuser t = (Tagent_Tuser) subUserId.get(i);
				sql3 += " or m.user_id = " + t.getUser_id();
			}
		}
		sql3 += " ) ";
		if(pageRequestBean!=null && pageRequestBean.getSort().equals("sendtime")){
			pageRequestBean.setSort("send_time");
		}
		if(pageRequestBean!=null ){
			sql3 += " order by " + pageRequestBean.getSort() + " " + pageRequestBean.getOrder();
		}else{
			sql3 += " order by " + " send_time DESC ";
		}
		if(firstResult != null && maxResults != null){
			sql3 += " limit " + firstResult + "," + maxResults;
		}

		ss = sql3;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(ss).addEntity(History.class);
						return query.list();
					}
				});
		return l;
	}

}
