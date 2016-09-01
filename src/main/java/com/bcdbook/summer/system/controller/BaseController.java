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

@Controller
@RequestMapping("/")
public class BaseController {
	@Resource
	private MenuService menuService;
	
	@RequestMapping(method = {RequestMethod.GET}) 
	public ModelAndView getLoginPage(HttpServletRequest req,HttpServletResponse resp,Model model){
		Menu menu = new Menu();
		menu.setParentId("first");
		List<Menu> userMenus = menuService.findList(menu);
		
		
		ModelAndView mv = JadeUtil.getView("index.jade");
		
		return mv;
	}
	
	@RequestMapping(method = {RequestMethod.POST}) 
	@ResponseBody
	public String toLogin(){
		return "SUCCESS";
	}
}
