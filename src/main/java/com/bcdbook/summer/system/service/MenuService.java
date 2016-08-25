package com.bcdbook.summer.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.common.persistence.pojo.DataEntity;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.MenuDao;
import com.bcdbook.summer.system.pojo.Menu;


@Service("menuService")
public class MenuService extends CrudService<MenuDao,Menu>{
	@Resource
	private MenuDao menuDao;
	
	public List<Menu> getChilds(String parentId){
		return menuDao.getChilds(parentId);
	}
//	public Menu getMenu(String id) {
//		return menuDao.get2(id);
//	}

//	public int addMenu(Menu menu) {
//		return menuDao.insert(menu);
//	}
	
//	public String addMenu(Menu menu) {
//		menuDao.insert(menu);
//		// TODO Auto-generated method stub
//		return null;
//	}
}
