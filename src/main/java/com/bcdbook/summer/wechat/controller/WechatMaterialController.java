package com.bcdbook.summer.wechat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.service.WechatMaterialService;
import com.bcdbook.summer.wechat.service.WechatService;

/**
 * @Description: 微信素材管理的控制器
 * @author lason
 * @date 2016年9月20日
 */
@Controller
@RequestMapping("/wechat/material")
public class WechatMaterialController {
	private static Logger logger = Logger.getLogger(WechatMaterialController.class);
		
	@Resource
	private WechatService wechatService;
	@Resource
	private WechatMaterialService wechatMaterialService;
	
	/**
	 * @Description: 获取素材总量的方法
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(value="count",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String count(HttpServletRequest req, HttpServletResponse resp){
		return wechatMaterialService.count(wechatService.getAccessToken());
	}
	
	/**
	 * @Description: 获取素材列表的方法
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(value="list",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String list(HttpServletRequest req, HttpServletResponse resp){
		//从请求中获取参数
		String type = req.getParameter("type");//获取素材类型
		String countStr = req.getParameter("offset");//获取起始值
		String typeStr = req.getParameter("count");//想要获取素材的数量
		
		//把获取到的参数进行转换成int类型
		int offsetSource = countStr==null?0:Integer.parseInt(countStr);
		int countSource = typeStr==null?0:Integer.parseInt(typeStr);
		
		//为了防止参数错误,对参数进行强制处理
		int offset = offsetSource<0?0:offsetSource;
		int count = countSource<1||countSource>20?20:countSource;
		
		return wechatMaterialService.list(wechatService.getAccessToken(),type,offset,count);
	}
}