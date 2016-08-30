package com.bcdbook.summer.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.Power;

@Repository(value="powerDao")
public interface PowerDao extends CrudDao<Power> {
	
	public List<Power> findListByMenuId(String menuId);
}
