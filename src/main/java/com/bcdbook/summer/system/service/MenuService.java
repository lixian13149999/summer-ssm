package com.bcdbook.summer.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.config.Global;
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

	/**
	 * @Description: 获取后台栏目
	 * @param @return   
	 * @return List<Menu>  
	 * @throws
	 * @author lason
	 * @date 2016年10月18日
	 */
	public List<Menu> listBackMenus() {
		//创建栏目对象,用于封装查询条件
		Menu menu = new Menu();
		menu.setDelFlag(Global.DEL_FLAG_NORMAL);//设置删除标识为未删除
		menu.setPlace(Menu.PLACE_BACKER);//设置前后台区分为后台
		menu.setParentId(Menu.FIRST_MENU);//设置栏目等级为一级栏目
		List<Menu> menus = menuDao.findList(menu);//根据封装好的条件查询出一级栏目的集合
		return menus;
	}
	
}
