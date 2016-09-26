package com.bcdbook.summer.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcdbook.summer.common.util.JadeUtil;

/**
 * 
 * @Description: 移动端的controller
 * @author lason
 * @date 2016年9月26日
 */
@Controller
@RequestMapping("/m")
public class MobileController {
	
	/**
	 * @Description: 登录(获取登录页面)
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月26日
	 */
	@RequestMapping(value="/signin",method={RequestMethod.GET})
	public ModelAndView signin(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		Map<String, Boolean> pageData = new HashMap<String, Boolean>();
//		pageData.put("hasUser", true);
		
		ModelAndView mv = JadeUtil.getView("mobile/system/sign/signin.jade",pageData);
		return mv;
	}
}
