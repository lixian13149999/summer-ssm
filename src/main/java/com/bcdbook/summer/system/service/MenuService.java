package com.bcdbook.summer.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.MenuDao;
import com.bcdbook.summer.system.pojo.Menu;


@Service("menuService")
public class MenuService extends CrudService<MenuDao,Menu>{
	@Resource
	private MenuDao menuDao;
	
	public List<Menu> listChilds(String parentId){
		return menuDao.listChilds(parentId);
	}
	
}
