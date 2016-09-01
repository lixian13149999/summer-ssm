package com.bcdbook.summer.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.service.MenuService;

/**
 * @Description: 项目层面的控制器
 * @author lason
 * @date 2016年9月1日
 */
@Controller
@RequestMapping("/")
public class BaseController {
	@Resource
	private MenuService menuService;
	
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
		ModelAndView mv = JadeUtil.getView("common/base/signin.jade");
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
	public ModelAndView doSignin(HttpServletRequest req,HttpServletResponse resp,Model model){
		return null;
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
		ModelAndView mv = JadeUtil.getView("common/base/signup.jade");
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
	public ModelAndView doSignup(HttpServletRequest req,HttpServletResponse resp,Model model){
		return null;
	}
	
	@RequestMapping(method = {RequestMethod.GET}) 
	public ModelAndView getLoginPage(HttpServletRequest req,HttpServletResponse resp,Model model){
		Menu menu = new Menu();
		menu.setParentId("first");
		List<Menu> userMenus = menuService.findList(menu);
		
		ModelAndView mv = JadeUtil.getView("index.jade");
		mv.addObject("onlineMenus", userMenus);
		
		return mv;
	}
	
	@RequestMapping(method = {RequestMethod.POST}) 
	@ResponseBody
	public String toLogin(){
		return "SUCCESS";
	}
}
