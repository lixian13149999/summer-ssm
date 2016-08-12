package com.bcdbook.summer.system.user.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcdbook.summer.system.user.service.UserService;

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
