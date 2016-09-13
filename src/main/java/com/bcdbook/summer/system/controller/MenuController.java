package com.bcdbook.summer.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.bcdbook.summer.system.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	
	
	@RequestMapping("/add")
	public String addMenu(HttpServletRequest request,Model model){
//		int id = Integer.parseInt(request.getParameter("id"));
//		String name = request.getParameter("name");
//		String dis = request.getParameter("dis");
//		
//		Menu m = new Menu();
//		m.setId(id);
//		m.setName(name);
//		m.setDescription(dis);
//		
//		menuService.addMenu(m);
//		
//		JSON.parse(text)
		return "user/getUser";
	}

}
