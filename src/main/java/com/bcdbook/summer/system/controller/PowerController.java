package com.bcdbook.summer.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.service.PowerService;




@Controller
@RequestMapping("/power")
public class PowerController {
	@Resource
	private PowerService powerService;
	
	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String listForeground(HttpServletRequest req,HttpServletResponse resp){
		String menuId = req.getParameter("menuId");
		if(StringUtils.isNull(menuId)){
			return BackMsg.error("menuId is null");
		}
		
		Power power = new Power();
		power.setMenuId(menuId);
		
		List<Power> powerList = powerService.findList(power);
		
		//创建值域对象,设置要返回到页面的值
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("powers", powerList);
		//生成要返回的代码块
		String html = JadeUtil.getBodyView("pc/system/power/au_list.jade", model);
		
		System.out.println(html);
		//返回相应的值到前台
		return BackMsg.success(html, "get backer power success");
	}
	
//	/**
//	 * @Description: 获取前台栏目的集合(只有栏目部分的数据)
//	 * @param @param req
//	 * @param @param resp
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年10月12日
//	 */
//	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="f")
//	@ResponseBody
//	public String listForeground(HttpServletRequest req,HttpServletResponse resp){
//		return this.listMenus(Power.PLACE_FOREGROUND,"pc/system/power/au_list.jade");
//	}
//
//	/**
//	 * @Description: 获取后台栏目的集合(只有栏目部分的数据)
//	 * @param @param req
//	 * @param @param resp
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年10月12日
//	 */
//	@RequestMapping(value="/list",method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="b")
//	@ResponseBody
//	public String listBack(HttpServletRequest req,HttpServletResponse resp){
//		return this.listMenus(Power.PLACE_BACKER,"pc/system/power/au_list.jade");
//	}
//	
//	
//	/**
//	 * @Description: 获取前台栏目(栏目整个页面)
//	 * @param @param req
//	 * @param @param resp
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年10月9日
//	 */
//	@RequestMapping(method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="f")
//	@ResponseBody
//	public String foreground(HttpServletRequest req,HttpServletResponse resp){
//		return this.listMenus(Power.PLACE_FOREGROUND,"pc/system/power/list.jade");
//	}
//	
//	//,params="fn=saveUsers"
//	/**
//	    * @Discription 获取后台栏目列表的方法(栏目整个页面)
//	    * @author lason       
//	    * @created 2016年10月11日 下午8:56:58     
//	    * @param req
//	    * @param resp
//	    * @return
//	 */
//	@RequestMapping(method={RequestMethod.GET},produces = "application/json; charset=UTF-8",params="b")
//	@ResponseBody
//	public String back(HttpServletRequest req,HttpServletResponse resp){
//		return this.listMenus(Power.PLACE_BACKER,"pc/system/power/list.jade");
//	}
//	
//	/**
//	 * @Description: 根据传入的栏目类型,前台栏目/后台栏目以及要渲染的页面链接地址,返回组合好的页面
//	 * @param @param placeForeground
//	 * @param @param jadeUrl
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年12月14日
//	 */
//	private String listMenus(Integer placeForeground,String jadeUrl){
//		//创建栏目对象,用于封装查询条件
//		Power power = new Power();
//		power.setDelFlag(Global.DEL_FLAG_NORMAL);//设置删除标识为未删除
//		
//		//判断是获取前台栏目还是后台栏目,并根据传入值不同,获取不同的栏目集合
//		if(placeForeground == Power.PLACE_BACKER){
//			power.setPlace(Power.PLACE_BACKER);//设置前后台区分为后台
//		}else if(placeForeground == Power.PLACE_FOREGROUND){
//			power.setPlace(Power.PLACE_FOREGROUND);//设置前后台区分为前台
//		}else{
//			return BackMsg.error("not is foreground or back please check the input value");
//		}
//		
//		power.setParentId(Power.FIRST_MENU);//设置栏目等级为一级栏目
//		List<Power> menus = menuService.findList(power);//根据封装好的条件查询出一级栏目的集合
//		//验证返回值的合法性
//		if(menus==null)
//			return BackMsg.error("get menus error");
//
//		//创建值域对象,设置要返回到页面的值
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("menus", menus);
//		//生成要返回的代码块
//		String html = JadeUtil.getBodyView(jadeUrl, model);
//		
//		//返回相应的值到前台
//		return BackMsg.success(html, "get backer power success");
//	}
//	
//	
//	
//	/**
//	 * @Description: 栏目排序的方法
//	 * @param @param req
//	 * @param @param resp
//	 * @param @param menus
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年12月9日
//	 */
////	,produces = "application/json; charset=UTF-8"
//	@RequestMapping(value="/sequence",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public String sequence(HttpServletRequest req,HttpServletResponse resp,@RequestBody List<Power> menus){
//		//验证参数的合法性
//		if(menus==null||menus.size()<1)
//			return BackMsg.error("menus is null");
//		
//		//循环其中栏目相关信息,并修改
//		for (Power power : menus) {
//			if(StringUtils.isNull(power.getId()))
//				continue;
//			
//			//创建一个新的栏目对象,用于修改排序,过滤掉其他无用的信息
//			Power auMenu = new Power();
//			auMenu.setId(power.getId());//设置id
//			auMenu.setSort(power.getSort());//设置顺序
//			
//			//执行更新操作
//			menuService.update(auMenu);
//		}
//		
//		//返回操作成功的相关信息
//		return BackMsg.success("栏目从新排序完成");
//	}
//
//	/**
//	 * @Description: 添加栏目的方法
//	 * @param @param req
//	 * @param @param resp
//	 * @param @param power
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年10月8日
//	 */
//	@RequestMapping(value="/add",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public String add(HttpServletRequest req,HttpServletResponse resp){
//		//获取表单中封装的数据
//		String menuStr = req.getParameter("power");
//		//验证参数的合法性
//		if(StringUtils.isNull(menuStr))
//			return BackMsg.error("request value is null");
//		
//		//把获取到的数据转成json
//		JSONObject menuJson = JSON.parseObject(menuStr);
//		if(menuJson==null)
//			return BackMsg.error("menuJson is null");
//		
//		Power power = new Power();
//		int place = menuJson.getInteger("foregroundOrBack");
//		power.setPlace(place);//前台还是后台
//		
//		int laver = menuJson.getInteger("laver");//栏目的级别
//		//根据栏目的级别,设置栏目的父级id
//		if(laver==1){
//			power.setParentId(Power.FIRST_MENU);
//		}else if(laver==2){
//			power.setParentId(menuJson.getString("parentId"));
//		}
//		
//		power.setSort(menuJson.getInteger("sort"));//栏目顺序
//		
//		power.setName(menuJson.getString("name"));//栏目名称
//		power.setIcon(menuJson.getString("icon"));//栏目图标
//		power.setPermission(menuJson.getString("permission"));//栏目的权限标识
//		power.setHref(menuJson.getString("href"));//连接地址
//		power.setDescription(menuJson.getString("description"));//栏目简介
//		power.setIsShow(menuJson.getInteger("isShow"));//是否显示
//		
//		System.out.println(power);
//		
//		int addMenuOk = menuService.add(power);
//
//		String redirectUrl = null;
//		if(place==Power.PLACE_FOREGROUND){
//			redirectUrl = "power/list?f";
//		}else if(place==Power.PLACE_BACKER){
//			redirectUrl = "power/list?b";
//		}
//		
//		//如果添加栏目正常,则返回新刷新的列表
//		if(addMenuOk==1){
//			try {
//				resp.sendRedirect(ContextParameter.getContextPath()+redirectUrl);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		//如果添加不成功,则返回错误信息
//		return BackMsg.error("add power error");
//	}
//	
//	/**
//	 * @Description: 修改栏目的方法
//	 * @param @param req
//	 * @param @param resp
//	 * @param @param power
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年10月8日
//	 */
//	@RequestMapping(value="/update",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public String update(HttpServletRequest req,HttpServletResponse resp){
//		//获取表单中封装的数据
//		String menuStr = req.getParameter("power");
//		//验证参数的合法性
//		if(StringUtils.isNull(menuStr))
//			return BackMsg.error("request value is null");
//		
//		//把获取到的数据转成json
//		JSONObject menuJson = JSON.parseObject(menuStr);
//		if(menuJson==null)
//			return BackMsg.error("menuJson is null");
//		
//		Power power = new Power();
//		int place = menuJson.getInteger("foregroundOrBack");
//		power.setPlace(place);//前台还是后台
//		power.setId(menuJson.getString("id"));//设置栏目的id
//		
//		int laver = menuJson.getInteger("laver");//栏目的级别
//		//根据栏目的级别,设置栏目的父级id
//		if(laver==1){
//			power.setParentId(Power.FIRST_MENU);
//		}else if(laver==2){
//			power.setParentId(menuJson.getString("parentId"));
//		}
//		
//		power.setSort(menuJson.getInteger("sort"));//栏目顺序
//		
//		power.setName(menuJson.getString("name"));//栏目名称
//		power.setIcon(menuJson.getString("icon"));//栏目图标
//		power.setPermission(menuJson.getString("permission"));//栏目的权限标识
//		power.setHref(menuJson.getString("href"));//连接地址
//		power.setDescription(menuJson.getString("description"));//栏目简介
//		power.setIsShow(menuJson.getInteger("isShow"));//是否显示
//		
//		System.out.println(power);
//		
//		int updateOk = menuService.update(power);
//		
//		String redirectUrl = null;
//		if(place==Power.PLACE_FOREGROUND){
//			redirectUrl = "power/list?f";
//		}else if(place==Power.PLACE_BACKER){
//			redirectUrl = "power/list?b";
//		}
//		
//		//如果添加栏目正常,则返回新刷新的列表
//		if(updateOk==1){
//			try {
//				resp.sendRedirect(ContextParameter.getContextPath()+redirectUrl);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		//如果添加不成功,则返回错误信息
//		return BackMsg.error("add power error");
//	}
//	
//	/**
//	 * @Description: 删除栏目的方法
//	 * @param @param req
//	 * @param @param resp
//	 * @param @param power
//	 * @param @return   
//	 * @return String  
//	 * @throws
//	 * @author lason
//	 * @date 2016年10月8日
//	 */
//	@RequestMapping(value="/delete",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public String delete(HttpServletRequest req,HttpServletResponse resp,String menuId){
//		//验证参数的合法性
//		if(StringUtils.isNull(menuId))
//			return BackMsg.error("power is null");
//		
//		//执行删除操作,并判断删除操作是否执行OK
//		int deleteOk = menuService.delete(menuId);
//		if(deleteOk!=1)
//			return BackMsg.error("delete power error");
//		
//		try {
//			resp.sendRedirect(ContextParameter.getContextPath()+"power/list?b");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return BackMsg.success("delete menu success");
//	}
}
