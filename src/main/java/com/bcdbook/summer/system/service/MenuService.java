package com.bcdbook.summer.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.dao.MenuDao;
import com.bcdbook.summer.system.pojo.Menu;


@Service("menuService")
public class MenuService extends CrudService<MenuDao,Menu>{
	@Resource
	private MenuDao menuDao;

	/**
	 * @Description: 根据传入的栏目类型(前/后台) 获取栏目的集合,
	 * @param @param foregroundOrBack
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年12月9日
	 */
	public String listMenus(Integer placeForeground,String jadeUrl){
		//创建栏目对象,用于封装查询条件
		Menu menu = new Menu();
		menu.setDelFlag(Global.DEL_FLAG_NORMAL);//设置删除标识为未删除
		
		//判断是获取前台栏目还是后台栏目,并根据传入值不同,获取不同的栏目集合
		if(placeForeground == Menu.PLACE_BACKER){
			menu.setPlace(Menu.PLACE_BACKER);//设置前后台区分为后台
		}else if(placeForeground == Menu.PLACE_FOREGROUND){
			menu.setPlace(Menu.PLACE_FOREGROUND);//设置前后台区分为前台
		}else{
			return BackMsg.error("not is foreground or back please check the input value");
		}
		
		menu.setParentId(Menu.FIRST_MENU);//设置栏目等级为一级栏目
		List<Menu> menus = this.findList(menu);//根据封装好的条件查询出一级栏目的集合
		//验证返回值的合法性
		if(menus==null)
			return BackMsg.error("get menus error");

		//创建值域对象,设置要返回到页面的值
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("menus", menus);
		//生成要返回的代码块
		String html = JadeUtil.getBodyView(jadeUrl, model);
		
		//返回相应的值到前台
		return BackMsg.success(html, "get backer menu success");
	}
	
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
