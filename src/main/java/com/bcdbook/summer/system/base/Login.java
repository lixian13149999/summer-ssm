package com.bcdbook.summer.system.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class Login {
	
	@RequestMapping(method = {RequestMethod.GET}) 
	public String getLoginPage(){
		return "login";
	}
	
	@RequestMapping(method = {RequestMethod.POST}) 
	@ResponseBody
	public String toLogin(){
		return "SUCCESS";
	}
}
