package com.bcdbook.summer.system.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.CrudDao;
import com.bcdbook.summer.system.pojo.Menu;


@Repository(value="menuDao")
public interface MenuDao extends CrudDao<Menu> {

	Menu get2(String id);
//	int insert(Menu menu);
}
