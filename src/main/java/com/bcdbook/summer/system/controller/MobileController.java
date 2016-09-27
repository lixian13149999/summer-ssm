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
import com.bcdbook.summer.common.util.StringUtils;

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
		
		String auto_signin = null;
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		
		if(!StringUtils.isNull(code)
				&&!code.equals("0"))
			auto_signin = "auto_signin";
			
		
		Map<String, String> pageData = new HashMap<String, String>();
		pageData.put("code", code);
		pageData.put("state", state);
		pageData.put("auto_signin", auto_signin);
		
		ModelAndView mv = JadeUtil.getView("mobile/system/sign/signin.jade",pageData);
		return mv;
	}
	
	/**
	 * @Description: 微信获取到用户的openid后返回的页面
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月27日
	 */
	@RequestMapping(value="/wechatAutoSignin",method={RequestMethod.GET})
	public ModelAndView wechatAutoSignin(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		
		Map<String, String> pageData = new HashMap<String, String>();
		pageData.put("code", code);
		pageData.put("state", state);
		
		ModelAndView mv = JadeUtil.getView("mobile/system/sign/wechat_auto_signin.jade",pageData);
		return mv;
	}
	
	/**
	 * @Description: 注册(获取移动端的注册页面)
	 * @param @param req
	 * @param @param resp
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月26日
	 */
	@RequestMapping(value="/signup",method={RequestMethod.GET})
	public ModelAndView signup(HttpServletRequest req,HttpServletResponse resp,Model model){
		
		Map<String, String> pageData = new HashMap<String, String>();
		
		ModelAndView mv = JadeUtil.getView("mobile/system/sign/signup.jade",pageData);
		return mv;
	}
}
