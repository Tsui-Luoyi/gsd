package com.jsd.web.vehicle.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.vehicle.dao.VehicleManageDao;
import com.jsd.web.vehicle.po.Tagent_Tuser;
import com.jsd.web.vehicle.po.Tvehicle_Tuser;

@SuppressWarnings({ "serial", "rawtypes","unchecked" })
public class VehicleManageDaoImpl extends HibernateDaoSupport implements
		VehicleManageDao, Serializable {

	// 已代理的
	@Override
	public List querySubList(String agent_id) {
		final String sql = "select * from tvehicle_tuser where agent_id = "
				+ agent_id;
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tvehicle_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	// 未代理的
	@Override
	public List queryTotalList(String userId) {
		final String s = "select * from tagent_tuser where user_id = " + userId;
		List l = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(s);
						return query.list();
					}
				});
		Object[] object = (Object[]) l.get(0);
		final String sql = "select * from tvehicle_tuser  where agent_id = "
				+ object[1];
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tvehicle_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public List queryTotalList2(String orgId) {
		final String s = "select * from tvehicle_tuser where agent_id = '0' and org_no = '"
				+ orgId + "'";
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(s).addEntity(
								Tvehicle_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public List manageSubmit11(String agent_id, String vname1, String vno1) {
		final String sql;
		String s = "select * from tvehicle_tuser where agent_id = " + agent_id;

		if (vname1 != null && vname1.length() > 0) {
			s += " and vname like '%" + vname1.trim() + "%'";
		}
		if (vno1 != null && vno1.length() > 0) {
			s += " and vno like '%" + vno1.trim() + "%'";
		}
		sql = s;
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tvehicle_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	public Tagent_Tuser getAgentId(String user_id) {
		final String sql = "select * from tagent_tuser where user_id = "
				+ user_id;
		Tagent_Tuser t = (Tagent_Tuser) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sql)
								.addEntity(Tagent_Tuser.class).uniqueResult();
					}
				});
		return t;
	}

	@Override
	public List manageSubmit21(String user_id, String vname2, String vno2) {
		Tagent_Tuser t = getAgentId(user_id);
		final String sql;
		String ss = "select * from tvehicle_tuser  where agent_id = "
				+ t.getAgent_id();
		if (vname2 != null && vname2.length() > 0) {
			ss += " and vname like '%" + vname2.trim() + "%'";
		}
		if (vno2 != null && vno2.length() > 0) {
			ss += " and vno like '%" + vno2.trim() + "%'";
		}
		sql = ss;
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tvehicle_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public List manageSubmit22(String orgId, String vname2, String vno2) {
		final String sql;
		String s = "select * from tvehicle_tuser where agent_id = '0' and org_no = '"
				+ orgId + "'";
		if (vname2 != null && vname2.length() > 0) {
			s += " and vname like '%" + vname2.trim() + "%'";
		}
		if (vno2 != null && vno2.length() > 0) {
			s += " and vno like '%" + vno2.trim() + "%'";
		}
		sql = s;
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								Tvehicle_Tuser.class);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public void toRight(String ids1, String userId) {
		String str;
		String[] split = ids1.split(",");
		for (int i = 0; i < split.length; i++) {
			str = split[i];
			String s = "select * from tvehicle_tuser where id = '" + str + "'";
			final String sql = s;
			Tvehicle_Tuser tvehicle = (Tvehicle_Tuser) this
					.getHibernateTemplate().execute(new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							return session.createSQLQuery(sql)
									.addEntity(Tvehicle_Tuser.class)
									.uniqueResult();
						}
					});
			Tagent_Tuser tagent = getAgentId(userId);
			tvehicle.setAgent_id(tagent.getAgent_id());
			this.getHibernateTemplate().update(tvehicle);
		}
	}

	@Override
	public void toRight2(String ids1) {
		String str;
		String[] split = ids1.split(",");
		for (int i = 0; i < split.length; i++) {
			str = split[i];
			String s = "select * from tvehicle_tuser where id = '" + str + "'";
			final String sql = s;
			Tvehicle_Tuser tvehicle = (Tvehicle_Tuser) this
					.getHibernateTemplate().execute(new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							return session.createSQLQuery(sql)
									.addEntity(Tvehicle_Tuser.class)
									.uniqueResult();
						}
					});
			tvehicle.setAgent_id(0);
			this.getHibernateTemplate().update(tvehicle);
		}
	}

	@Override
	public void toLeft(String ids2, String agent_id) {
		String str;
		String[] split = ids2.split(",");
		for (int i = 0; i < split.length; i++) {
			str = split[i];
			String s = "select * from tvehicle_tuser where id = '" + str + "'";
			final String sql = s;
			Tvehicle_Tuser tvehicle = (Tvehicle_Tuser) this
					.getHibernateTemplate().execute(new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							return session.createSQLQuery(sql)
									.addEntity(Tvehicle_Tuser.class)
									.uniqueResult();
						}
					});
			tvehicle.setAgent_id(Integer.valueOf(agent_id));
			this.getHibernateTemplate().update(tvehicle);
		}
	}
}
