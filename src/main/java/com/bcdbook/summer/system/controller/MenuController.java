package com.bcdbook.summer.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	
	/**
	 * @Description: 获取前台栏目
	 * @param @param req
	 * @param @param resp
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月9日
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="f")
	@ResponseBody
	public String listForeground(HttpServletRequest req,HttpServletResponse resp){
		Menu menu = new Menu();
		menu.setDelFlag(Global.DEL_FLAG_NORMAL);
		menu.setPlace(Menu.PLACE_FOREGROUND);
		menu.setParentId(Menu.FIRST_MENU);
		List<Menu> menus = menuService.findList(menu);
		if(menus==null)
			return BackMsg.error("get foreground menu error");
		
		return BackMsg.success(menus, "get foreground menu success");
	}
	
	//,params="fn=saveUsers"
	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="b")
	@ResponseBody
	public String list(HttpServletRequest req,HttpServletResponse resp){
		Menu menu = new Menu();
		menu.setDelFlag(Global.DEL_FLAG_NORMAL);
		menu.setPlace(Menu.PLACE_BACKER);
		menu.setParentId(Menu.FIRST_MENU);
		List<Menu> menus = menuService.findList(menu);
		if(menus==null)
			return BackMsg.error("get backer menu error");
		
		for (Menu menu2 : menus) {
			System.out.println(menu2);
		}
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("menus", menus);
		
		String path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(path);
		
		String html = JadeUtil.getBodyView("pc/system/menu/list.jade", model);
		System.out.println(html);
		
		return BackMsg.success(html, "get backer menu success");
	}
	
	
//	,produces = "application/json; charset=UTF-8"
	@RequestMapping(value="/sequence",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String sequence(HttpServletRequest req,HttpServletResponse resp,@RequestBody List<Menu> menus){
		//验证参数的合法性
		if(menus==null||menus.size()<1)
			return BackMsg.error("menus is null");
		
		//循环其中栏目相关信息,并修改
		for (Menu menu : menus) {
			if(StringUtils.isNull(menu.getId()))
				continue;
			
			//创建一个新的栏目对象,用于修改排序,过滤掉其他无用的信息
			Menu auMenu = new Menu();
			auMenu.setId(menu.getId());//设置id
			auMenu.setSort(auMenu.getSort());//设置顺序
			
			//执行更新操作
			menuService.update(auMenu);
		}
		
		//返回操作成功的相关信息
		return BackMsg.success("栏目从新排序完成");
	}

	/**
	 * @Description: 添加栏目的方法
	 * @param @param req
	 * @param @param resp
	 * @param @param menu
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月8日
	 */
	@RequestMapping(value="/add",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req,HttpServletResponse resp){
		String menuStr = req.getParameter("menu");
		if(StringUtils.isNull(menuStr))
			return BackMsg.error("request value is null");
		
		JSONObject menuJson = JSONObject.parseObject(menuStr);
		if(menuJson==null)
			return BackMsg.error("menuJson is null");
		
		System.out.println(menuStr);
//		//验证参数的合法性
//		if(menu==null)
//			return BackMsg.error("menu is null");
//		
//		//执行添加栏目的方法,并返回栏目的id
//		String menuId = menuService.addBackId(menu);
//		if(StringUtils.isNull(menuId))
//			return BackMsg.error("add menu error");
//			
//		//根据传入的添加后返回的栏目id获取栏目对象
//		Menu dbMenu = menuService.get(menuId);
//		//如果根据返回的栏目id获取到的栏目对象为空,直接返回错误信息
//		if(dbMenu==null)
//			return BackMsg.error("add menu error");
//		
//		//如果返回的栏目信息不为空,返回栏目对象,并返回添加成功的信息
//		return BackMsg.success(dbMenu, "add menu success");
		
		return null;
	}
	
	/**
	 * @Description: 修改栏目的方法
	 * @param @param req
	 * @param @param resp
	 * @param @param menu
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月8日
	 */
	@RequestMapping(value="/update",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest req,HttpServletResponse resp,Menu menu){
		//验证参数的合法性
		if(menu==null)
			return BackMsg.error("menu is null");
		
		//执行栏目的更新方法
		int updateOk = menuService.update(menu);
		
		//如果执行更新操作出错,则返回错误信息
		if(updateOk!=1)
			return BackMsg.error("update menu error");
		
		return BackMsg.success(menu, "update menu success");
	}
	
	/**
	 * @Description: 删除栏目的方法
	 * @param @param req
	 * @param @param resp
	 * @param @param menu
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月8日
	 */
	@RequestMapping(value="/delete",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest req,HttpServletResponse resp,String menuId){
		//验证参数的合法性
		if(StringUtils.isNull(menuId))
			return BackMsg.error("menu is null");
		
		//执行删除操作,并判断删除操作是否执行OK
		int deleteOk = menuService.delete(menuId);
		if(deleteOk!=1)
			return BackMsg.error("delete menu error");
		
		return BackMsg.success("delete menu success");
	}
}
