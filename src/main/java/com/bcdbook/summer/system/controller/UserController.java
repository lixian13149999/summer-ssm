package com.bcdbook.summer.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.common.util.SessionUtil;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.system.service.MenuService;
import com.bcdbook.summer.system.service.UserService;


/**
 * 
     * @Title: UserController.java    
     * @Description: user的主控制器
     * @author lason       
     * @created 2016年5月18日 下午3:35:53
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	

	/**
	 * @Description: 检查用户名是否被占用的方法
	 * @param @param req
	 * @param @param resp
	 * @param @param user
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月13日
	 */
	@RequestMapping(value="/userNameCanUse",method = {RequestMethod.GET}) 
	@ResponseBody
	public String userNameCanUse(HttpServletRequest req,HttpServletResponse resp,User user){
		//根据前台传入的用户对象,从数据库中获取user
		User dbUser = userService.getByCondition(user);
//		System.out.println(user);
//		System.out.println(dbUser);
		//如果用户不存在,返回true,否则返回false
		return dbUser==null?"true":"false";
	}
	
	@RequestMapping(value="/userIsExist",method = {RequestMethod.GET}) 
	@ResponseBody
	public String userIsExist(HttpServletRequest req,HttpServletResponse resp,User user){
		if(user==null)
			return BackMsg.error("reqest user is null");
		
		//根据前台传入的用户对象,从数据库中获取user
		User dbUser = userService.getByCondition(user);
//		System.out.println(user);
//		System.out.println(dbUser);
		//如果用户不存在,返回true,否则返回false
		
		return dbUser==null?BackMsg.success(false):BackMsg.success(true);
	}
	
	@RequestMapping(value="/checkEmailIsSingle",method = {RequestMethod.GET}) 
	@ResponseBody
	public String checkEmailIsSingle(HttpServletRequest req,HttpServletResponse resp,User user){
		if(user==null)
			return "false";
		//根据前台传入的用户对象,从数据库中获取user
		User dbUser = userService.getByCondition(user);
//		System.out.println(user);
//		System.out.println(dbUser);
		//如果用户不存在,返回true,否则返回false
		
		return dbUser==null?"true":"false";
	}
	
	@RequestMapping(value="/verifyEmailPage",method={RequestMethod.GET})
	public ModelAndView verifyEmailPage(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		Map<String, Object> pageData = new HashMap<String, Object>();
		pageData.put("hasUser", false);
		
		User user = (User) SessionUtil.getObj(req, Global.ONLINE_USER);
//		System.out.println(user);
		pageData.put("user", user);
		
		ModelAndView mv = JadeUtil.getView("system/sign/verify_email.jade",pageData);
		return mv;
	}

	@RequestMapping(value="/verifyEmail",method={RequestMethod.GET})
	public ModelAndView verifyEmail(HttpServletRequest req,HttpServletResponse resp,User user){
		
//		Map<String, Object> pageData = new HashMap<String, Object>();
//		pageData.put("hasUser", false);
//		
//		User user = (User) SessionUtil.getObj(req, Global.ONLINE_USER);
////		System.out.println(user);
//		pageData.put("user", user);
//		
//		ModelAndView mv = JadeUtil.getView("system/sign/verify_email.jade",pageData);
//		return mv;
		return null;
	}
	
	/*
	 * 以下是测试使用的方法,正式上线前需要删除
	 */
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String testIndex(HttpServletRequest request,HttpServletResponse response,Model model){
		Menu menu = new Menu();
		menu.setParentId("first");
		List<Menu> userMenus = menuService.findList(menu);
		request.setAttribute("userMenus", userMenus);
		return "index";
	}
	
	
	/**
	 * 
	    * @Discription TODO
	    * @author lason       
	    * @created 2016年5月18日 下午3:39:55     
	    * @param request
	    * @param model
	    * @return String
	 */
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request,Model model){
		return "user/getUser";
	}
//	
//	@RequestMapping("/getAjax")
//	public String getAjax(HttpServletRequest request,Model model){
//		return "user/getUser";
//	}
	@RequestMapping("/ajax")
	public void getAjax(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
//			request.getRequestDispatcher("/WEB-INF/views/test/testhtml.html").forward(request, response);
			request.getRequestDispatcher("/static/ajax.html").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
