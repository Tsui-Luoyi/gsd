package com.jsd.web.login.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.login.dao.UserDAO;
import com.jsd.web.po.Tprivilege;
import com.jsd.web.po.Tuser;
import com.jsd.web.po.TuserOther;
import com.jsd.web.po.UserMac;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@Override
	public Tuser queryUserInfo(Tuser user) {
		/*
		final String hql = "From Tuser u where u.userCode=? and u.userPassword=?";
		Object[] args = new Object[]{"ly007","123456"};
		@SuppressWarnings("rawtypes")
		List rstList = this.getHibernateTemplate().find(hql, args);
		*/

		@SuppressWarnings("rawtypes")
		List rstList = this.getHibernateTemplate().findByExample(user);

		if(rstList!=null){
			if(rstList.size()>0){
				Tuser user1 = (Tuser)rstList.get(0);
				return user1;
			}
		}
		return null;
	}
	/**
	 *
	 */
	@Override
	public int updateUserInfo(Tuser user) {
		try{
			this.getHibernateTemplate().saveOrUpdate(user);
			return 0;
		}catch(RuntimeException e){
			e.printStackTrace();
			return 1;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Tuser queryUserInfoByCode(String userCode) {
		final String sql = "from Tuser u where u.userCode='"+ userCode +"'";
		List rstList = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(sql);
						return query.list();
					}
		});
		if(rstList!=null){
			if(rstList.size()>0){
				Tuser user1 = (Tuser)rstList.get(0);
				return user1;
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getPrivileges(String privilegeValue) {
		List<String> list=new ArrayList<String>();
		final String sql = "from Tprivilege u where u.privilegeId in("+privilegeValue+")";
		List<Tprivilege> rstList = (List<Tprivilege>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(sql);
						return query.list();
					}
		});
		if(rstList!=null&&rstList.size()>0){
			for(Tprivilege tprivilege:rstList){
				list.add(tprivilege.getPrivilegeName());
			}
			return list;
		}else{
			return null;
		}
	}
	@Override
	public List<UserMac> queryUserMac(Tuser rstUser) {
		UserMac um = new UserMac();
		um.setUid(rstUser.getUserId());
		@SuppressWarnings("rawtypes")
		List list = this.getHibernateTemplate().findByExample(um);
		List<UserMac> lret = new ArrayList<UserMac>();
		if(list == null || list.isEmpty())
			return lret;

		for(Object obj : list)
			lret.add((UserMac)obj);

		return lret;
	}
	@Override
	public void insertUserMac(String mac, Integer uid, String cpuNum) {
		UserMac um = new UserMac();
		um.setMac(mac);
		um.setUid(uid);
		um.setCpuNum(cpuNum);
		this.getHibernateTemplate().save(um);
	}
	@Override
	public void updateUserMac(String mac, Integer uid, String cpuNum) {
		// TODO Auto-generated method stub
		UserMac um = new UserMac();
		um.setMac(mac);
		um.setCpuNum(cpuNum);
		this.getHibernateTemplate().save(um);
	}
	@Override
	public TuserOther findOtherUserInfo(Integer userId) {
		TuserOther user = new TuserOther();
		user.setTuserId(userId);
		@SuppressWarnings("rawtypes")
		List list = this.getHibernateTemplate().findByExample(user);
		if(list == null || list.isEmpty())
			return null;

		user = (TuserOther)list.get(0);

		return user;
	}

}
