package com.bcdbook.summer.system.menu.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.system.menu.pojo.Menu;


@Repository(value="menuDao")
public interface MenuDao {
	int insert(Menu menu);
}
