package com.bcdbook.summer.system.menu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.system.menu.dao.MenuDao;
import com.bcdbook.summer.system.menu.pojo.Menu;
import com.bcdbook.summer.system.menu.service.MenuService;


@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDao menuDao;
	
	@Override
	public String addMenu(Menu menu) {
		menuDao.insert(menu);
		// TODO Auto-generated method stub
		return null;
	}
}
