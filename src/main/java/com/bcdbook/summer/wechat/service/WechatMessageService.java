package com.bcdbook.summer.wechat.service;

import org.springframework.stereotype.Service;

@Service("wechatMessageService")
public class WechatMessageService {

//	public String parseBackMsg(WechatMaterial dbWM) {
//		String backMsg = null;
//		
//		String type = dbWM.getMsgType();
//		
//		//根据不同的消息类型,选用不同的处理器进行处理
//		switch (type) {
//			case WechatMessage.TEXT:
//				//解析文本消息
//				backMsg = processTextMsg(reqMapMsg);
//				break;
//			case WechatMessage.IMAGE:
//				//解析图片消息
//				backMsg = processImageMsg(reqMapMsg);
//				break;
//			case WechatMessage.VOICE:
//				//解析语音消息
//				backMsg = processVoiceMsg(reqMapMsg);
//				break;
//			case WechatMessage.VIDEO:
//				//解析视频消息
//				backMsg = processVideoMsg(reqMapMsg);
//				break;
//			case WechatMessage.MUSIC:
//				//解析短视频消息
//				backMsg = processShortvideoMsg(reqMapMsg);
//				break;
//			case WechatMessage.NEWS:
//				//解析位置消息(通过系统自带的地理位置发送方式发送的地理位置)
//				backMsg = processLocationMsg(reqMapMsg);
//				break;
//			default:
//				//解析无法识别类型的消息
//				backMsg = WechatRespMessage.SUCCESS;
//				break;
//		}
//		
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}
