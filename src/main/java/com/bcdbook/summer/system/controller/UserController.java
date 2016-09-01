package com.bcdbook.summer.system.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdbook.summer.system.pojo.Menu;
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
