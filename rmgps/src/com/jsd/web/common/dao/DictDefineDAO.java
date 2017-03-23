package com.jsd.web.common.dao;

public interface DictDefineDAO {

	/**
	 * 根据定义id查出定义名称
	 * @param id
	 * @return
	 */
	public String queryDefNamebyId(String id,String groupId);
}
