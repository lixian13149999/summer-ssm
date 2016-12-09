package com.bcdbook.summer.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.event.MenuListener;

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
	
	/**
	 * @Description: 登录成功后返回的主页面
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年12月9日
	 */
	@RequestMapping(method = {RequestMethod.GET}) 
	public ModelAndView index(HttpServletRequest req,HttpServletResponse resp,Model model){
//		Menu menu = new Menu();
//		menu.setParentId("first");
		/*
		 * 从session中获取用户的栏目集合,
		 * 这里的集合是属于当前用户的,并已去除重复
		 */
		List<Menu> userMenus = (List<Menu>) req.getSession().getAttribute(Global.USER_MENUS);
		
		ModelAndView mv = JadeUtil.getView("pc/index.jade");
		mv.addObject("userMenus", userMenus);
		
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
		
		ModelAndView mv = JadeUtil.getView("pc/system/sign/signin.jade",pageData);
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
	@RequestMapping(value="/signin",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doSignin(HttpServletRequest req,HttpServletResponse resp,User user){
//		System.out.println(user);
		//参数合法性验证
		if(user==null)
			return BackMsg.error("request user is null");
		
		//执行验证的相关操作,如果验证通过,返回一个user对象
		User onlineUser = userService.signin(user);
//		System.out.println(onlineUser);
		if(onlineUser==null)
			return BackMsg.error("signin error");
		
		if(!SessionUtil.refresh(req, Global.ONLINE_USER, onlineUser))
			return BackMsg.error("signin error");
		
		List<Menu> menuList = menuService.listBackMenus();
		SessionUtil.refresh(req, Global.USER_MENUS, menuList);
//		HttpSession session = req.getSession();
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
		
		ModelAndView mv = JadeUtil.getView("pc/system/sign/signup.jade",pageData);
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
	@RequestMapping(value="/signup",method={RequestMethod.POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doSignup(HttpServletRequest req,HttpServletResponse resp,User user){
		
		User newUser = userService.signup(user);
		
		if(newUser==null)
			return BackMsg.error("signup error");
		
		return BackMsg.success(JSON.toJSONString(newUser), "signup success");
	}
	
	/**
	 * @Description: 退出登录的方法
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年12月9日
	 */
	@RequestMapping(value="/logout",method={RequestMethod.GET})
	public ModelAndView logout(HttpServletRequest req,HttpServletResponse resp,Model model){
		//清除session中的用户
		SessionUtil.remove(req, Global.ONLINE_USER);
		
		
		//创建map集合,用于jade页面数据的设定
		Map<String, Boolean> pageData = new HashMap<String, Boolean>();
		//标注默认操作者是否有账号
		//默认为有时,返回登录页面,并在页面中显示"请注册"连接,)
		//否则显示注册页面,并显示请登录连接
		pageData.put("hasUser", true);
		
		ModelAndView mv = JadeUtil.getView("pc/system/sign/signin.jade",pageData);
		return mv;
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
		
		ModelAndView mv = JadeUtil.getView("pc/common/error/404.jade");
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
		
		ModelAndView mv = JadeUtil.getView("pc/common/error/500.jade");
		return mv;
	}
	
	
	/*
	 * 一下是测试时使用的方法,正式上线之前需要删除
	 */
}
