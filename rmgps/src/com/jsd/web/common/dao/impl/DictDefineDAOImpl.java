package com.jsd.web.common.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.common.dao.DictDefineDAO;
import com.jsd.web.po.DictDefine;

public class DictDefineDAOImpl extends HibernateDaoSupport implements DictDefineDAO {

	@SuppressWarnings("unchecked")
	@Override
	public String queryDefNamebyId(String id,String groupId) {
		String sql = "from DictDefine u where groupId="+ groupId +" and defId="+id;
		List<DictDefine> list = this.getHibernateTemplate().find(sql);
		if(list!=null){
			for(DictDefine def:list){
				String name = def.getName();
				return name;
			}
		}
		return "";
	}

}
