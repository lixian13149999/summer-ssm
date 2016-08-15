package com.bcdbook.summer.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * html页面访问的相关例子
 * @author lason
 *
 */
@Controller
@RequestMapping("/demo/html")
public class HtmlController {
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
}
