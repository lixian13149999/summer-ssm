package com.bcdbook.summer.system.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.common.util.SessionUtil;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.pojo.Role;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.system.service.MenuService;
import com.bcdbook.summer.system.service.UserService;

/**
 * @Description: 项目层面的控制器
 * @author lason
 * @date 2016年9月1日
 */
@Controller
@RequestMapping("/")
public class BaseController {
	
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	
	
	@RequestMapping(method = {RequestMethod.GET}) 
	public ModelAndView index(HttpServletRequest req,HttpServletResponse resp,Model model){
//		Menu menu = new Menu();
//		menu.setParentId("first");
//		List<Menu> userMenus = menuService.findList(menu);
		
		ModelAndView mv = JadeUtil.getView("index.jade");
//		mv.addObject("onlineMenus", userMenus);
		
		return mv;
	}
	
	/**
	 * @Description: 获取登陆页面
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	@RequestMapping(value="/signin",method={RequestMethod.GET})
	public ModelAndView signin(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		Map<String, Boolean> pageData = new HashMap<String, Boolean>();
		pageData.put("hasUser", true);
		
		ModelAndView mv = JadeUtil.getView("system/sign/signin.jade",pageData);
		return mv;
	}
	
	/**
	 * @Description: 执行登陆操作
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	@RequestMapping(value="/signin",method={RequestMethod.POST})
	@ResponseBody
	public String doSignin(HttpServletRequest req,HttpServletResponse resp,User user){
//		System.out.println(user);
		if(user==null)
			return BackMsg.error("request user is null");
		
		User onlineUser = userService.signin(user);
		if(onlineUser==null)
			return BackMsg.error("signin error");
		
		if(!SessionUtil.refresh(req, Global.ONLINE_USER, onlineUser))
			return BackMsg.error("signin error");
		
		HttpSession session = req.getSession();
//		List<User> userList = userService.findList(user);
//		if(userList.size()!=1)
//			return BackMsg.error("user not exist or too more");
//		
//		User onlineUser = userService.getByCondition(user);
//		if(onlineUser==null)
//			return BackMsg.error("user not exist");
		
//		List<Role> roles = userService.listRoleByUser(onlineUser.getId());
//		Set<Menu> menus = userService.listMenuByUser(onlineUser.getId());
//		Set<Power> powers = userService.listPowerByUser(onlineUser.getId());
		
		
//		System.out.println(user);
		return BackMsg.success("signin success");
	}
	
	/**
	 * @Description: 获取注册页面
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	@RequestMapping(value="/signup",method={RequestMethod.GET})
	public ModelAndView signup(HttpServletRequest req,HttpServletResponse resp,Model model){

		Map<String, Boolean> pageData = new HashMap<String, Boolean>();
		pageData.put("hasUser", false);
		
		ModelAndView mv = JadeUtil.getView("system/sign/signup.jade",pageData);
		return mv;
	}
	/**
	 * @Description: 执行注册操作
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	@RequestMapping(value="/signup",method={RequestMethod.POST})
	@ResponseBody
	public String doSignup(HttpServletRequest req,HttpServletResponse resp,User user){
		
		User newUser = userService.signup(user);
		
		if(newUser==null)
			return BackMsg.error("signup error");
		
		return BackMsg.success(JSON.toJSONString(newUser), "signup success");
	}
	
	/**
	    * @Discription 404的错误页面
	    * @author lason       
	    * @created 2016年9月15日 下午10:07:38     
	    * @param req
	    * @param resp
	    * @param model
	    * @return
	 */
	@RequestMapping(value="/error/404",method={RequestMethod.GET})
	public ModelAndView error404(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		ModelAndView mv = JadeUtil.getView("common/error/404.jade");
		return mv;
	}
	
	/**
	    * @Discription 500的错误页面
	    * @author lason       
	    * @created 2016年9月15日 下午10:07:49     
	    * @param req
	    * @param resp
	    * @param model
	    * @return
	 */
	@RequestMapping(value="/error/500",method={RequestMethod.GET})
	public ModelAndView error500(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		ModelAndView mv = JadeUtil.getView("common/error/500.jade");
		return mv;
	}
	
	
	/*
	 * 一下是测试时使用的方法,正式上线之前需要删除
	 */
	
	@RequestMapping(value="/signin2",method={RequestMethod.GET})
	public ModelAndView signin2(HttpServletRequest req,HttpServletResponse resp,Model model){
		ModelAndView mv = JadeUtil.getView("signin2.jade");
		return mv;
	}
	
	@RequestMapping(value="/signup2",method={RequestMethod.GET})
	public ModelAndView signup2(HttpServletRequest req,HttpServletResponse resp,Model model){
		ModelAndView mv = JadeUtil.getView("signup2.jade");
		return mv;
	}
	
	@RequestMapping(method = {RequestMethod.POST}) 
	@ResponseBody
	public String toLogin(){
		return "SUCCESS";
	}
	
}
