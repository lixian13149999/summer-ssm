package com.bcdbook.summer.demo;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.system.pojo.User;

/**
 * 
     * @Title: TestController.java    
     * @Description: 测试类的主要控制器
     * @author lason       
     * @created 2016年7月8日 下午2:30:35
 */
@Controller
@RequestMapping("/demo/test")
public class TestController {
	/*
	 * 获取test.jsp页面
	 */
	@RequestMapping("/test")
	public String getUser(HttpServletRequest request,Model model){
		return "demo/test";
	}
	
	/*
	 * 获取testAjax.jsp页面
	 */
	@RequestMapping("/testAjax")
	public String testAjaxJsp(HttpServletRequest request,Model model){
		return "demo/testAjax";
	}
	
	/*
	 * 获取index.html页面
	 */
//	@RequestMapping("/index")
//	public void getIndex(HttpServletRequest request,HttpServletResponse response,Model model){
//		System.out.println("进入获取静态页面的方法");
//		try {
////			request.getRequestDispatcher("/WEB-INF/views/test/testhtml.html").forward(request, response);
//			
//			request.getRequestDispatcher("/static/demo/index.html").forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/*
	 * 获取testhtml.html页面
	 */
	@RequestMapping("/testhtml")
	public void getHtml(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
//			request.getRequestDispatcher("/WEB-INF/views/test/testhtml.html").forward(request, response);
			
			request.getRequestDispatcher("/static/demo/testhtml.html").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取ajax.html页面
	 */
	@RequestMapping("/ajax")
	public void getAjax(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
//			request.getRequestDispatcher("/WEB-INF/views/test/testhtml.html").forward(request, response);
//			System.out.println(response.toString());
			request.getRequestDispatcher("/static/demo/ajax.html").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 测试通过ajax方式调取后台数据
	 */
	@RequestMapping(value="/ajaxData", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxData(){
		User user = new User();
		user.setUserName("admin");
		user.setAge(2);
		user.setGender(0);
//		JSONObject jo = JSONObject.
		String jsonUser = JSON.toJSONString(user);
		JSONObject jo = JSON.parseObject(jsonUser);
		System.out.println(jo.getString("userName"));
//		System.out.println(jsonUser);
		return jsonUser;
	}
}
