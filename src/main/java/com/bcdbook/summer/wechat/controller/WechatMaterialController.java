package com.bcdbook.summer.wechat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.pojo.message.WechatMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.WechatRespMessage;
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
	 * @Description: 添加素材到本地数据库的方法
	 * @param @param req
	 * @param @param resp
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	@RequestMapping(value="add",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req, HttpServletResponse resp){
//		String accessToken,String mediaId,String type,String keyword,int offset, int count
		
		/*
		 * 获取前台传入的相关参数
		 */
		String type = req.getParameter("type");//获取要添加的素材类型
		String keyword = req.getParameter("keyword");//获取关键字
		String mediaId = req.getParameter("mediaId");//获取素材id
		String content = req.getParameter("content");
		//验证参数的合法性
		if(StringUtils.isNull(type)
				||StringUtils.isNull(keyword))
			return BackMsg.error("type or keyword is null,this port nede 6 parameters type(1), keyword(1), mediaId(0), content(0), offset(0) and count(0)");
		
		//检查关键字是否存在
		if(wechatMaterialService.keywordIsExist(keyword))
			return BackMsg.error("keyword is exist please chenge keyword or delete dbMaterial");
		
		String accessToken = wechatService.getAccessToken();//获取accessToken
		//验证accessToken的合法性
		if(StringUtils.isNull(accessToken))
			return BackMsg.error("accessToken is null please check methed wechatService.getAccessToken()");
		
		//根据不同的消息类型,选用不同的处理器添加素材
		switch (type) {
			case WechatMaterial.TEXT:
				//解析文本消息
//				backMsg = processTextMsg(reqMapMsg);
				break;
			case WechatMaterial.IMAGE:
				//解析图片消息
//				backMsg = processImageMsg(reqMapMsg);
				break;
			case WechatMaterial.VOICE:
				//解析语音消息
//				backMsg = processVoiceMsg(reqMapMsg);
				break;
			case WechatMaterial.VIDEO:
				//解析视频消息
//				backMsg = processVideoMsg(reqMapMsg);
				break;
			case WechatMaterial.MUSIC:
				//解析短视频消息
//				backMsg = processShortvideoMsg(reqMapMsg);
				break;
			case WechatMaterial.NEWS:
				//解析位置消息(通过系统自带的地理位置发送方式发送的地理位置)
//				backMsg = processLocationMsg(reqMapMsg);
				break;
			default:
				//解析无法识别类型的消息
				return BackMsg.error("the type of you pass con't be identify");
		}
		
//		//如果要保存的消息是文本类型
//		if(type.equals(WechatMaterial.TEXT)){
//			if(StringUtils.isNull(content))
//				return BackMsg.error("if type is text,the parameter of content can't be null");
//			
//			
//		}else if(type.equals(WechatMaterial.IMAGE)){
//			if(StringUtils.isNull(content))
//				return BackMsg.error("if type is text,the parameter of content can't be null");
//			
//			
//		}else if(type.equals(WechatMaterial.VOICE)){
//			if(StringUtils.isNull(content))
//				return BackMsg.error("if type is text,the parameter of content can't be null");
//			
//			
//		}else if(type.equals(WechatMaterial.VIDEO)){
//			if(StringUtils.isNull(content))
//				return BackMsg.error("if type is text,the parameter of content can't be null");
//			
//			
//		}else{
//			return BackMsg.error("the type of you pass con't be identify");
//		}
		
		/*
		 * 通过list方式添加时需要查询的起始值和查询数量
		 */
		String offsetStr = req.getParameter("offset");//获取起始值
		String countStr = req.getParameter("count");//想要获取素材的数量
		//把获取到的参数进行转换成int类型
		int offsetSource = offsetStr==null?0:Integer.parseInt(offsetStr);
		int countSource = countStr==null?20:Integer.parseInt(countStr);
		
		//为了防止参数错误,对参数进行强制处理
		int offset = offsetSource<0?0:offsetSource;
		int count = countSource<1||countSource>20?20:countSource;
		
		
				
		return null;
	}
	
	
	
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
	 * 请求时需要三个参数
	 * 1. type:想要获取的素材列表的的类型
	 * 2. offset:获取的起始位置(类似分页查询中的起始值)
	 * 3. count:想要获取的素材的条数
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
		String offsetStr = req.getParameter("offset");//获取起始值
		String countStr = req.getParameter("count");//想要获取素材的数量
		
		if(StringUtils.isNull(type))
			return BackMsg.error("type is null,this port nede 3 parameters type,offset and count");
		
		//把获取到的参数进行转换成int类型
		int offsetSource = offsetStr==null?0:Integer.parseInt(offsetStr);
		int countSource = countStr==null?20:Integer.parseInt(countStr);
		
		//为了防止参数错误,对参数进行强制处理
		int offset = offsetSource<0?0:offsetSource;
		int count = countSource<1||countSource>20?20:countSource;
		
		return wechatMaterialService.list(wechatService.getAccessToken(),type,offset,count);
	}
	
	/**
	 * @Description: 从微信中获取素材,并存储到本地数据库的方法
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(value="getAndSave",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getAndSave(HttpServletRequest req, HttpServletResponse resp){
		//从请求中获取参数
		String mediaId = req.getParameter("mediaId");//获取素材id
		String type = req.getParameter("type");//获取类型
		String keyword = req.getParameter("keyword");//获取想要设置的关键字
		if(StringUtils.isNull(mediaId)
				||StringUtils.isNull(type)
				||StringUtils.isNull(keyword))
			return BackMsg.error("this port nede 3 parameters mediaId,type and keyword");
		
		if(!type.equals(WechatMaterial.NEWS))
			return BackMsg.error("this port need type is news or video");
		
		return wechatMaterialService.getAndSave(wechatService.getAccessToken(),mediaId,type,keyword);
	}
	
	/**
	 * @Description: 从微信中获取单个素材方法
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(value="get",method = { RequestMethod.GET },produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest req, HttpServletResponse resp){
		//从请求中获取参数
		String mediaId = req.getParameter("mediaId");//获取素材的id
		if(StringUtils.isNull(mediaId))
			return BackMsg.error("mediaId is null");
		
		return wechatMaterialService.get(wechatService.getAccessToken(),mediaId);
	}
}
