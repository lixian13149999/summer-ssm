package com.bcdbook.summer.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.Menu;


@Repository(value="menuDao")
public interface MenuDao extends CrudDao<Menu> {

	List<Menu> listChilds(String parentId);
	
	Menu get2(String id);
//	int insert(Menu menu);
}
