package com.jsd.web.vehicle.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.common.PageInfo;
import com.jsd.web.po.DevtermInfo;
import com.jsd.web.po.GpsLocateCurrent;
import com.jsd.web.vehicle.dao.MnDao;
import com.jsd.web.vehicle.vo.MnCommonVo;

@SuppressWarnings({"unchecked","rawtypes"})
public class MnDaoImpl extends HibernateDaoSupport implements MnDao {
	private int queryCount(final String countSql) {
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(countSql);
				return Integer.parseInt(query.uniqueResult().toString());
			}

		});
	}

	private List queryPagingListBySql(int count, PageInfo pagination, final String sql) {
		pagination.setTotalRowsCount(count);
		pagination.pageRenew();
		final int startRow = pagination.getStartRow()-1;
		final int pageSize = pagination.getPageSize();

		return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
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

	@Override
	public GpsLocateCurrent findCurrentInfo(String vno) {
		GpsLocateCurrent vo = null;
		String hql = "from GpsLocateCurrent gc where gc.id.vno = '" + vno + "'";
		List list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			vo = (GpsLocateCurrent)list.get(0);
		}
		return vo;
	}

	@Override
	public List findCmdHistoryInfo(MnCommonVo vo, PageInfo pagination) {
		String sql = "select vno,user_id,cmd_name,cmd_type,send_time,receive_time,receive_result from mn_cmd_history ch where " +
				"ch.vno = '" + vo.getVno() + "' and ch.user_id = " + vo.getUser_id();
		String countSql = "select count(vno) from mn_cmd_history ch where " +
				"ch.vno = '" + vo.getVno() + "' and ch.user_id = " + vo.getUser_id();
		String querySql = addQueryCondition("", vo);
		sql += querySql + " order by ch.send_time desc";
		countSql += querySql;

		int count = queryCount(countSql);
		if(count > 0){
			return queryPagingListBySql(count, pagination, sql);
		}

		return null;
	}

	private String addQueryCondition(String sql, MnCommonVo vo) {
		if(vo.getCmd_name() != null && !"".equals(vo.getCmd_name())){
			sql += " and ch.cmd_name like '%" + vo.getCmd_name() + "%'";
		}
		if(vo.getCmd_type() != null && !"0".equals(vo.getCmd_type())){
			sql += " and ch.cmd_type = '" + vo.getCmd_type() + "'";
		}
		if(vo.getStart_time() != null && !"".equals(vo.getStart_time())){
			sql += " and ch.send_time >= '" + vo.getStart_time() + "'";
		}
		if(vo.getEnd_time() != null && !"".equals(vo.getEnd_time())){
			sql += " and ch.receive_time <= '" + vo.getEnd_time() + "'";
		}

		return sql;
	}

	@Override
	public List findLoginHistoryInfo(MnCommonVo vo, PageInfo pagination) {
		String sql = "select vno,login_time,locate_flag,longitude,latitude,gsm_strong,lac,cellid,addr from mn_login_history lh where lh.vno = '" + vo.getVno() + "'";
		String countSql = "select count(vno) from mn_login_history lh where lh.vno = '" + vo.getVno() + "'";
		String querySql = addQueryCondition_2("", vo);
		sql += querySql + " order by lh.login_time desc";
		countSql += querySql;

		int count = queryCount(countSql);
		if(count > 0){
			return queryPagingListBySql(count, pagination, sql);
		}

		return null;
	}

	private String addQueryCondition_2(String sql, MnCommonVo vo) {
		if(vo.getStart_time() != null && !"".equals(vo.getStart_time())){
			sql += " and lh.login_time >= '" + vo.getStart_time() + "'";
		}
		if(vo.getEnd_time() != null && !"".equals(vo.getEnd_time())){
			sql += " and lh.login_time <= '" + vo.getEnd_time() + "'";
		}

		return sql;
	}

	@Override
	public List findCmdInfo1(String vno) {
		final String sql = "select * from lyb_rundata crd where crd.vno = '" + vno + "' order by crd.revtime desc";

		org.hibernate.classic.Session openSession = this.getHibernateTemplate().getSessionFactory().openSession();
		List list = openSession.createSQLQuery(sql).list();
		openSession.clear();
		openSession.close();
		return list;
	}
	@Override
	public List findCmdInfo2(String vno) {
		final String sql = "select * from mn_cmd_result_lyb crd where crd.vno = '" + vno + "'";

		org.hibernate.classic.Session openSession = this.getHibernateTemplate().getSessionFactory().openSession();
		List list = openSession.createSQLQuery(sql).list();
		openSession.clear();
		openSession.close();
		return list;
	}

	@Override
	public List findCmdQueueInfo(String vno) {
		final String sql = "select * from mn_cmd_send_queue csq where csq.vno = '" + vno + "' order by csq.send_time desc";

		return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}

	@Override
	public int findDeviceType(String vno) {
		String sql = "select * from devterm_info where dno = '"+vno+"'";
		Session openSession = this.getHibernateTemplate().getSessionFactory().openSession();
		List<DevtermInfo> devtermInfo = openSession.createSQLQuery(sql).addEntity(DevtermInfo.class).list();
		openSession.clear();
		openSession.close();
		return devtermInfo.get(0).getDevtermGroup().getVgId();
	}

}
