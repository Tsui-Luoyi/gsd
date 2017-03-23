package com.jsd.web.authority.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.authority.dao.AuUserDAO;
import com.jsd.web.po.Tuser;

public class AuUserDAOImpl extends HibernateDaoSupport implements AuUserDAO{

	@Override
	@SuppressWarnings("rawtypes")
	public List queryVehicleOrgRelListByUser(String userId) {
		//查询用户信息
		String sql = "from Tuser u where u.userId="+userId;// and u.torgTable.orgId"//根据主键id查询用户
		List usrList = this.getHibernateTemplate().find(sql);
		if(usrList!=null && usrList.size()>0){
			Tuser tuser = (Tuser)usrList.get(0);
			String orgsql = "from TvOrgRel tv where tv.orgId="+tuser.getTorgTable().getOrgId();//根据orgid查询tv_org_rel表的list
			 List vehicleList = this.getHibernateTemplate().find(orgsql);
			 return vehicleList;
		}
		return null;
	}



}
