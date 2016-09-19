package com.bcdbook.summer.wechat.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.wechat.service.WechatMenuService;
import com.bcdbook.summer.wechat.service.WechatService;

/**
 * 
 * @Description: 微信栏目处理的相关接口
 * @author lason
 * @date 2016年9月19日
 */
@Controller
@RequestMapping("/wechat/menu")
public class WechatMenuController {
	@Resource
	private WechatService wechatService;
	@Resource
	private WechatMenuService wechatMenuService;
	
	/**
	 * @Description: 创建微信栏目的方法
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	@RequestMapping(value="create",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String create(){
		
		return wechatMenuService.createMenu(wechatService.getAccessToken());
		
	}
	
	/**
	 * @Description: 查看栏目的接口
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	@RequestMapping(value="get",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String get(){
		return wechatMenuService.getMenu(wechatService.getAccessToken());
	}
}
