package com.jsd.web.login.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.login.dao.LoginHistoryDAO;
import com.jsd.web.po.TloginHistory;

public class LoginHistoryDAOImpl extends HibernateDaoSupport implements LoginHistoryDAO {

	@Override
	public int addLoginHistory(TloginHistory loginHistory) {
		this.getHibernateTemplate().save(loginHistory);
		return 1;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List queryLoginHistory(String userCode,String today) {
		String tmpSql = "";

		if(today!=null && !"".equals(today)){
			tmpSql= "from TloginHistory u where u.userCode='"+userCode+"' and loginDate > '"+today+"' order by loginDate desc";
		}else{
			tmpSql = "from TloginHistory u where u.userCode='"+userCode+"' order by loginDate desc";
		}
		final String sql = tmpSql;
		List rstList = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(sql);
						return query.list();
					}
		});
		return rstList;
	}
}
