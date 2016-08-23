package com.bcdbook.summer.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.system.dao.MenuDao;
import com.bcdbook.summer.system.pojo.Menu;


@Service("menuService")
public class MenuService{
	@Resource
	private MenuDao menuDao;
	
//	public String addMenu(Menu menu) {
//		menuDao.insert(menu);
//		// TODO Auto-generated method stub
//		return null;
//	}
}
