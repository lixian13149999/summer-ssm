package com.bcdbook.summer.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdbook.summer.wechat.pojo.message.WechatMessage;

/**
 * @Description: 微信消息处理的控制器
 * @author lason
 * @date 2016年9月20日
 */
@Controller
@RequestMapping("/wechat/message")
public class WechatMessageController {
	private static Logger logger = Logger.getLogger(WechatMessageController.class);
	
//	@Resource
//	private WechatService wechatService;
	
	/**
	 * @Description: 处理消息的接口
	 * @param @param req
	 * @param @param resp   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("进入消息处理的接口  /wechat/message");
		//定义返回值
		String backMsg = "success";
		
		@SuppressWarnings("unchecked")
		Map<String,String> reqMapMsg = (Map<String, String>) req.getAttribute("reqMapMsg");
		
		//获取消息的类型
		String msgType = reqMapMsg.get("MsgType");
		//根据不同的消息类型,选用不同的处理器进行处理
		switch (msgType) {
			case WechatMessage.TEXT:
				//解析文本消息
				backMsg = processTextMsg(reqMapMsg);
				break;
			case WechatMessage.IMAGE:
				//解析图片消息
				backMsg = processImageMsg(reqMapMsg);
				break;
			case WechatMessage.VOICE:
				//解析语音消息
				backMsg = processVoiceMsg(reqMapMsg);
				break;
			case WechatMessage.VIDEO:
				//解析视频消息
				backMsg = processVideoMsg(reqMapMsg);
				break;
			case WechatMessage.SHORT_VIDEO:
				//解析短视频消息
				backMsg = processShortvideoMsg(reqMapMsg);
				break;
			case WechatMessage.LOCATION:
				//解析位置消息(通过系统自带的地理位置发送方式发送的地理位置)
				backMsg = processLocationMsg(reqMapMsg);
				break;
			case WechatMessage.LINK:
				//解析连接消息
				backMsg = processLinkMsg(reqMapMsg);
				break;
			default:
				//解析无法识别类型的消息
				backMsg = "success";
				break;
		}
		logger.info("消息处理完毕,将要返回==>"+backMsg);
		req.setAttribute("backMsg", backMsg);
	}
	
	private String processTextMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processImageMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processVoiceMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processVideoMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processShortvideoMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processLocationMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processLinkMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
}
