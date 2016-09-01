package com.bcdbook.summer.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcdbook.summer.common.util.JadeUtil;

@Controller
@RequestMapping("/")
public class BaseController {
	
	@RequestMapping(method = {RequestMethod.GET}) 
	public ModelAndView getLoginPage(HttpServletRequest req,HttpServletResponse resp,Model model){
		ModelAndView mv = JadeUtil.getView("index.jade");
		return mv;
	}
	
	@RequestMapping(method = {RequestMethod.POST}) 
	@ResponseBody
	public String toLogin(){
		return "SUCCESS";
	}
}
