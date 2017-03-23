package com.jsd.web.map.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.map.dao.MapDataDAO;

/**
 * 获取地图上实时数据
 * @author cuilupeng
 *
 */
@SuppressWarnings("unchecked")
public class MapDataDAOImpl extends HibernateDaoSupport implements MapDataDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public List getGpsLocateTrack(String vno, String startTime, String endTime) {
		final String sql = "from Mn_login_history g where g.vno='"+vno+
						"' and g.login_time>='"+startTime+"' and g.login_time<='"+endTime+
						"' and g.locate_flag!='V' order by g.login_time ASC";
		List rstList = (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				return query.list();
			}
		}
		);
		return rstList;
	}
}
