package com.bcdbook.summer.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdbook.summer.wechat.pojo.WechatMessage;
import com.bcdbook.summer.wechat.pojo.event.WechatEvent;

@Controller
@RequestMapping("/wechat/event")
public class WechatEventController {
	
	private static Logger logger = Logger.getLogger(WechatEventController.class);
	
//	@Resource
//	private WechatService wechatService;
	
	/**
	 * @Description: 处理事件的接口
	 * @param @param req
	 * @param @param resp   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("进入事件处理的接口 /wechat/event");
		@SuppressWarnings("unchecked")
		Map<String,String> reqMapMsg = (Map<String, String>) req.getAttribute("reqMapMsg");
		
		//定义返回值
		String backMsg = "success";
		
		//获取消息的类型
		String msgType = reqMapMsg.get("MsgType");
		
		if(msgType.equals(WechatMessage.EVENT)){
			//获取事件类型
			String event = reqMapMsg.get("Event");
			
			//根据不同的消息类型,选用不同的处理器进行处理
			switch (event) {
			case WechatEvent.SUBSCRIBE:
				//解析关注事件
				backMsg = processSubscribeEvent(reqMapMsg);
				break;
			case WechatEvent.UNSUBSCRIBE:
				//解析取消关注事件
				backMsg = processUnsubscribeEvent(reqMapMsg);
				break;
			case WechatEvent.SCAN:
				//解析扫描
				backMsg = processSCANEvent(reqMapMsg);
				break;
			case WechatEvent.LOCATION:
				//解析地理位置事件(系统自动发送)
				backMsg = processLOCATIONEvent(reqMapMsg);
				break;
			case WechatEvent.CLICK:
				//解析点击事件
				backMsg = processCLICKEvent(reqMapMsg);
				break;
			case WechatEvent.VIEW:
				//解析连接事件
				backMsg = processVIEWEvent(reqMapMsg);
				break;
			case WechatEvent.SCANCODE_PUSH:
				//解析扫描(连接自动进入)事件
				backMsg = processScancodePushEvent(reqMapMsg);
				break;
			case WechatEvent.SCANCODE_WAITMSG:
				//解析扫描(带提示)事件
				backMsg = processScancodeWaitmsgEvent(reqMapMsg);
				break;
			case WechatEvent.PIC_SYSPHOTO:
				//解析手机拍照,发送照片事件
				backMsg = processPicSysphotoEvent(reqMapMsg);
				break;
			case WechatEvent.PIC_SYSPHOTO_OR_ALBUM:
				//解析手机拍照或系统相册,发送照片事件
				backMsg = processPicPhotoOrAlbumEvent(reqMapMsg);
				break;
			case WechatEvent.PIC_WEIXIN:
				//解析发送微信照片事件
				backMsg = processPicWeixinEvent(reqMapMsg);
				break;
			case WechatEvent.LOCATION_SELECT:
				//解析按钮点击发送地理位置事件
				backMsg = processLocationSelectEvent(reqMapMsg);
				break;
			case WechatEvent.MEDIA_ID:
				//解析下发消息事件
				backMsg = processMediaIdEvent(reqMapMsg);
				break;
			case WechatEvent.VIEW_LIMITED:
				//解析自动跳转,素材中固定连接的时间
				backMsg = processViewLimitedEvent(reqMapMsg);
				break;
			default:
				//解析无法识别类型的事件
				backMsg = "success";
				break;
			}
		}
		logger.info("事件处理完毕,将要返回==>"+backMsg);
		req.setAttribute("backMsg", backMsg);
	}
	
	private String processSubscribeEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processUnsubscribeEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processSCANEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processLOCATIONEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processCLICKEvent(Map<String, String> reqMapMsg) {
		System.out.println("processCLICKEvent  start");
		System.out.println(reqMapMsg.get("Event"));
		System.out.println(reqMapMsg.get("EventKey"));
		System.out.println("processCLICKEvent  end");
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processVIEWEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processScancodePushEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processScancodeWaitmsgEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processPicSysphotoEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processPicPhotoOrAlbumEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processPicWeixinEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processLocationSelectEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processMediaIdEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processViewLimitedEvent(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
}
