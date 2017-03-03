package com.bcdbook.summer.system.controller;

import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.backmsg.Resp;
import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.context.ContextParameter;
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
	 * @Description: 获取前台栏目的集合(只有栏目部分的数据)
	 * @param @param req
	 * @param @param resp
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月12日
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="f")
	@ResponseBody
	public String listForeground(HttpServletRequest req,HttpServletResponse resp){
		return this.listMenus(Menu.PLACE_FOREGROUND,"pc/system/menu/au_list.jade");
	}

	/**
	 * @Description: 获取后台栏目的集合(只有栏目部分的数据)
	 * @param @param req
	 * @param @param resp
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月12日
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="b")
	@ResponseBody
	public String listBack(HttpServletRequest req,HttpServletResponse resp){
		return this.listMenus(Menu.PLACE_BACKER,"pc/system/menu/au_list.jade");
	}
	
	
	/**
	 * @Description: 获取前台栏目(栏目整个页面)
	 * @param @param req
	 * @param @param resp
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年10月9日
	 */
	@RequestMapping(method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="f")
	@ResponseBody
	public String foreground(HttpServletRequest req,HttpServletResponse resp){
		return this.listMenus(Menu.PLACE_FOREGROUND,"pc/system/menu/list.jade");
	}
	
	//,params="fn=saveUsers"
	/**
	    * @Discription 获取后台栏目列表的方法(栏目整个页面)
	    * @author lason       
	    * @created 2016年10月11日 下午8:56:58     
	    * @param req
	    * @param resp
	    * @return
	 */
	@RequestMapping(method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="b")
	@ResponseBody
	public String back(HttpServletRequest req,HttpServletResponse resp){
		return this.listMenus(Menu.PLACE_BACKER,"pc/system/menu/list.jade");
	}
	
	/**
	 * @Description: 根据传入的栏目类型,前台栏目/后台栏目以及要渲染的页面链接地址,返回组合好的页面
	 * @param @param placeForeground
	 * @param @param jadeUrl
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年12月14日
	 */
	private String listMenus(Integer placeForeground,String jadeUrl){
		//创建栏目对象,用于封装查询条件
		Menu menu = new Menu();
		menu.setDelFlag(Global.DEL_FLAG_NORMAL);//设置删除标识为未删除
		
		//判断是获取前台栏目还是后台栏目,并根据传入值不同,获取不同的栏目集合
		if(placeForeground == Menu.PLACE_BACKER){
			menu.setPlace(Menu.PLACE_BACKER);//设置前后台区分为后台
		}else if(placeForeground == Menu.PLACE_FOREGROUND){
			menu.setPlace(Menu.PLACE_FOREGROUND);//设置前后台区分为前台
		}else{
			return Resp.error("not is foreground or back please check the input value");
		}
		
		menu.setParentId(Menu.FIRST_MENU);//设置栏目等级为一级栏目
		List<Menu> menus = menuService.findList(menu);//根据封装好的条件查询出一级栏目的集合
		//验证返回值的合法性
		if(menus==null)
			return Resp.error("get menus error");

		//创建值域对象,设置要返回到页面的值
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("menus", menus);
		//生成要返回的代码块
		String html = JadeUtil.getBodyView(jadeUrl, model);
		
		//返回相应的值到前台
		return Resp.success(html, "get backer menu success");
	}
	
	
	
	/**
	 * @Description: 栏目排序的方法
	 * @param @param req
	 * @param @param resp
	 * @param @param menus
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年12月9日
	 */
//	,produces = "application/json; charset=UTF-8"
	@RequestMapping(value="/sequence",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String sequence(HttpServletRequest req,HttpServletResponse resp,@RequestBody List<Menu> menus){
		//验证参数的合法性
		if(menus==null||menus.size()<1)
			return Resp.error("menus is null");
		
		//循环其中栏目相关信息,并修改
		for (Menu menu : menus) {
			if(StringUtils.isNull(menu.getId()))
				continue;
			
			//创建一个新的栏目对象,用于修改排序,过滤掉其他无用的信息
			Menu auMenu = new Menu();
			auMenu.setId(menu.getId());//设置id
			auMenu.setSort(menu.getSort());//设置顺序
			
			//执行更新操作
			menuService.update(auMenu);
		}
		
		//返回操作成功的相关信息
		return Resp.success("栏目从新排序完成");
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
		//获取表单中封装的数据
		String menuStr = req.getParameter("menu");
		//验证参数的合法性
		if(StringUtils.isNull(menuStr))
			return Resp.error("request value is null");
		
		//把获取到的数据转成json
		JSONObject menuJson = JSON.parseObject(menuStr);
		if(menuJson==null)
			return Resp.error("menuJson is null");
		
		Menu menu = new Menu();
		int place = menuJson.getInteger("foregroundOrBack");
		menu.setPlace(place);//前台还是后台
		
		int laver = menuJson.getInteger("laver");//栏目的级别
		//根据栏目的级别,设置栏目的父级id
		if(laver==1){
			menu.setParentId(Menu.FIRST_MENU);
		}else if(laver==2){
			menu.setParentId(menuJson.getString("parentId"));
		}
		
		menu.setSort(menuJson.getInteger("sort"));//栏目顺序
		
		menu.setName(menuJson.getString("name"));//栏目名称
		menu.setIcon(menuJson.getString("icon"));//栏目图标
		menu.setPermission(menuJson.getString("permission"));//栏目的权限标识
		menu.setHref(menuJson.getString("href"));//连接地址
		menu.setDescription(menuJson.getString("description"));//栏目简介
		menu.setIsShow(menuJson.getInteger("isShow"));//是否显示
		
		System.out.println(menu);
		
		int addMenuOk = menuService.add(menu);

		String redirectUrl = null;
		if(place==Menu.PLACE_FOREGROUND){
			redirectUrl = "menu/list?f";
		}else if(place==Menu.PLACE_BACKER){
			redirectUrl = "menu/list?b";
		}
		
		//如果添加栏目正常,则返回新刷新的列表
		if(addMenuOk==1){
			try {
				resp.sendRedirect(ContextParameter.getContextPath()+redirectUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//如果添加不成功,则返回错误信息
		return Resp.error("add menu error");
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
	public String update(HttpServletRequest req,HttpServletResponse resp){
		//获取表单中封装的数据
		String menuStr = req.getParameter("menu");
		//验证参数的合法性
		if(StringUtils.isNull(menuStr))
			return Resp.error("request value is null");
		
		//把获取到的数据转成json
		JSONObject menuJson = JSON.parseObject(menuStr);
		if(menuJson==null)
			return Resp.error("menuJson is null");
		
		Menu menu = new Menu();
		int place = menuJson.getInteger("foregroundOrBack");
		menu.setPlace(place);//前台还是后台
		menu.setId(menuJson.getString("id"));//设置栏目的id
		
		int laver = menuJson.getInteger("laver");//栏目的级别
		//根据栏目的级别,设置栏目的父级id
		if(laver==1){
			menu.setParentId(Menu.FIRST_MENU);
		}else if(laver==2){
			menu.setParentId(menuJson.getString("parentId"));
		}
		
		menu.setSort(menuJson.getInteger("sort"));//栏目顺序
		
		menu.setName(menuJson.getString("name"));//栏目名称
		menu.setIcon(menuJson.getString("icon"));//栏目图标
		menu.setPermission(menuJson.getString("permission"));//栏目的权限标识
		menu.setHref(menuJson.getString("href"));//连接地址
		menu.setDescription(menuJson.getString("description"));//栏目简介
		menu.setIsShow(menuJson.getInteger("isShow"));//是否显示
		
		System.out.println(menu);
		
		int updateOk = menuService.update(menu);
		
		String redirectUrl = null;
		if(place==Menu.PLACE_FOREGROUND){
			redirectUrl = "menu/list?f";
		}else if(place==Menu.PLACE_BACKER){
			redirectUrl = "menu/list?b";
		}
		
		//如果添加栏目正常,则返回新刷新的列表
		if(updateOk==1){
			try {
				resp.sendRedirect(ContextParameter.getContextPath()+redirectUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//如果添加不成功,则返回错误信息
		return Resp.error("add menu error");
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
			return Resp.error("menu is null");
		
		//执行删除操作,并判断删除操作是否执行OK
		int deleteOk = menuService.delete(menuId);
		if(deleteOk!=1)
			return Resp.error("delete menu error");
		
		try {
			resp.sendRedirect(ContextParameter.getContextPath()+"menu/list?b");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Resp.success("delete menu success");
	}
}
