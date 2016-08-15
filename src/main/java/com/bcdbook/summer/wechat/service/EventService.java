package com.bcdbook.summer.wechat.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.enums.WechatEnum.IWechatEnum;
import com.bcdbook.summer.wechat.pojo.message.Message;
import com.bcdbook.summer.wechat.service.EncapsulationService;
import com.bcdbook.summer.wechat.service.EventService;

/**
 * 
 * @Title: EventServiceImpl.java
 * @Description: 处理事件消息的方法
 * @author lason
 * @created 2016年5月27日 上午10:42:46
 */
@Service("eventService")
public class EventService {
	@Resource
	private EncapsulationService encapsulationService;
	@Resource
	private MessageService messageService;

	/**
	 * 
	 * @Discription 处理事件的主控制器,由主控制器来控制调用哪个方法,处理相应的事件
	 * @author lason
	 * @created 2016年5月27日 上午10:43:16
	 * @param msg
	 * @return
	 * @see com.bcdbook.summer.wechat.service.EventService#processEvent(java.util.Map)
	 */
	public Message processEvent(Map<String, String> msg) {

		Message backMsg = null;
		String event = msg.get("Event");
		
		switch (event) {
		case "subscribe":
			backMsg = processSubscribeEvent(msg);
			break;
		case "unsubscribe":
			backMsg = processUnSubscribeEvent(msg);
			break;
		case "SCAN":
			backMsg = processSCANEvent(msg);
			break;
		case "LOCATION":
			backMsg = processLOCATIONEvent(msg);
			break;
		case "CLICK":
			backMsg = processCLICKEvent(msg);
			break;
		case "VIEW":
			backMsg = processVIEWEvent(msg);
			break;

		default:
			break;
		}
		// TODO Auto-generated method stub
		return backMsg;
	}

	public Message processSubscribeEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message processUnSubscribeEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message processSCANEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message processLOCATIONEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

//	<ToUserName>gh_0e183f03e273<ToUserName/>
//	<FromUserName>o8yDuvoYJ-39Z1o9YRRjREfIfFL4<FromUserName/>
//	<CreateTime>1464935893<CreateTime/>
//	<MsgType>event<MsgType/>
//	<Event>CLICK<Event/>
//	<EventKey>getcode<EventKey/>
	/**
	 * 
	    * @Discription 解析点击事件
	    * @author lason       
	    * @created 2016年6月3日 下午4:41:33      
	    * @param msg
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.EventService#processCLICKEvent(java.util.Map)
	 */
	public Message processCLICKEvent(Map<String, String> msg) {
		String EventKey = null;
		Message message = null;
		
		if(msg!=null){
			EventKey = msg.get("EventKey");
			if(EventKey!=null){
				
				/**
				 * 如果点击事件的关键字是个人中心,则要单独处理
				 */
				if(EventKey.equals(IWechatEnum.MY_HOME.getValue())){
					message = toMyself(msg);
				}else{
					message = messageService.getMsgByKeyword(EventKey);
				}
			}
		}
		
		return message;
	}

	public Message processVIEWEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	    * @Discription 对个人中心信息的单独处理方法
	    * @author lason       
	    * @created 2016年6月3日 下午4:42:47     
	    * @param msg
	    * @return
	 */
	public Message toMyself(Map<String, String> msg){
		//TODO 这里需要查询数据库,以检测此用户是否已经认证并授权
		String openid = msg.get("FromUserName");
//		boolean isExist = userService.isExis(openid);
		
		/*
		 * 根据关键字获取相应的消息
		 * 注意:这里的关键字需要数据库中有对应关键字的消息存在
		 */
		Message message = messageService.getMsgByKeyword(IWechatEnum.NOT_ENDORSEMENT.getValue());
//		message.setRemark("notEndorsement");
		
		return message;
	}
}
