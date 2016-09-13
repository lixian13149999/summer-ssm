package com.bcdbook.summer.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.system.pojo.User;

/**
 * 常用的Demo的控制器
 * @author lason
 *
 */
@Controller
@RequestMapping("/demo/jade")
public class JadeController {
	
	@RequestMapping(method = {RequestMethod.GET}) 
	public ModelAndView getLoginPage(HttpServletRequest req,HttpServletResponse resp,Model model){
//		Menu menu = new Menu();
//		menu.setParentId("first");
//		List<Menu> userMenus = menuService.findList(menu);
		
		ModelAndView mv = JadeUtil.getView("index.jade");
		mv.addObject("key", "这是key的value值");
//		mv.addObject("onlineMenus", userMenus);
		
		return mv;
	}
	/*
	 * 获取index.jade页面
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String getUser(HttpServletRequest req,Model imodel){
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserName("testUser_userName"+i);
			user.setPwd("pwd"+i);
			users.add(user);
		}
//		users.add(new User("The Hitchhiker's Guide to the Galaxy","password", 5));
//		users.add(new User("Life, the Universe and Everything","password", 5));
//		users.add(new User("The Restaurant at the End of the Universe","password", 5));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users", users);
		model.put("pageName", "My Bookshelf");
		
		String path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(path);
		
		String html = JadeUtil.getBodyView(req, "/index1.jade", model);
//		try {
//			html = Jade4J.render("/Users/lason/java/tomcat7/webapps/summer/static/demo/index.jade", model);
//		} catch (JadeCompilerException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println(html);
		return html;
	}
	
	@RequestMapping("/index1")
	public ModelAndView test2(HttpServletRequest req){
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserName("userName"+i);
			user.setPwd("pwd"+i);
			users.add(user);
		}
		
		ModelAndView mv = JadeUtil.getSealedSessionView(req,"index1");
		
		HttpSession session = req.getSession();
//		session.getAttribute(arg0);
//		session.getAttributeNames();
		session.setAttribute("users", users);
		session.setAttribute("pageName", "I Page");
//		mv.addObject("users", users);
//		mv.addObject("pageName", "iy Bookshelf");
		mv.addObject("session", session);
		return mv;
	}

	@RequestMapping("/index2")
	public ModelAndView index2(HttpServletRequest req){
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserName("userName"+i);
			user.setPwd("pwd"+i);
			users.add(user);
		}
		
		ModelAndView mv = JadeUtil.getSealedSessionView(req,"index2");
		
		mv.addObject("users", users);
		mv.addObject("pageName", "iy Bookshelf");
		return mv;
	}
	
	@RequestMapping("/index3")
	public ModelAndView index3(HttpServletRequest req){
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserName("userName"+i);
			user.setPwd("pwd"+i);
			users.add(user);
		}

		User user = new User();
		user.setUserName("这是session中的用户名");
		HttpSession session = req.getSession();
		session.setAttribute("onlineUser", user);
		
		
		ModelAndView mv = JadeUtil.getSealedSessionView(req,"index3");
		
		mv.addObject("users", users);
		mv.addObject("pageName", "test jade session");
		return mv;
	}
	
}
