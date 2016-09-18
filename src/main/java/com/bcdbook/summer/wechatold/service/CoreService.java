//package com.bcdbook.summer.wechatold.service;
//
//import java.io.IOException;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.dom4j.DocumentException;
//import org.springframework.stereotype.Service;
//
//import com.bcdbook.summer.wechatold.common.util.WechatUtil;
//import com.bcdbook.summer.wechatold.pojo.message.Message;
//import com.bcdbook.summer.wechatold.service.CoreService;
//
///**
// * 
// * @Title: CoreService.java
// * @Description: 处理微信消息的核心业务类
// * @author lason
// * @created 2016年5月24日 下午10:11:28
// */
//@Service("coreService")
//public class CoreService {
//	@Resource
//	private MessageService messageService;
//	@Resource
//	private EventService eventService;
//	@Resource
//	private EncapsulationService encapsulationService;
//	
//
//	/**
//	 * 
//	 * @Discription 解析客户发送过来的消息
//	 * @author lason
//	 * @created 2016年5月24日 下午10:12:24
//	 * @param request
//	 * @return
//	 */
//	public String processRequest(HttpServletRequest request) {
//		String respMsg = null;
//		Map<String, String> sourceMapMsg = null;// 定义一个map对象,用来存放解析后的消息
//		Message message = null;
//		try {
//			// 通过解析的工具类,解析用户发送的消息,得到解析后的map对象
//			sourceMapMsg = WechatUtil.parseXml(request);
//
//			String msgType = sourceMapMsg.get("MsgType");// 获取消息的类型
//			//根据不同的消息类型,选用不同的处理器进行处理
//			switch (msgType) {
//			case "text":
//				//解析文本消息
//				message = messageService.processTextMsg(sourceMapMsg);
//				break;
//			case "image":
//				//解析图片消息
//				message = messageService.processImageMsg(sourceMapMsg);
//				break;
//			case "voice":
//				//解析语音消息
//				message = messageService.processVoiceMsg(sourceMapMsg);
//				break;
//			case "video":
//				//解析视频消息
//				message = messageService.processVideoMsg(sourceMapMsg);
//				break;
//			case "shortvideo":
//				//解析短视频消息
//				message = messageService.processShortvideoMsg(sourceMapMsg);
//				break;
//			case "location":
//				//解析位置消息
//				message = messageService.processLocationMsg(sourceMapMsg);
//				break;
//			case "link":
//				//解析连接消息
//				message = messageService.processLinkMsg(sourceMapMsg);
//				break;
//			case "event":
//				//解析事件消息
//				message = eventService.processEvent(sourceMapMsg);
//				break;
//			default:
//				//解析无法识别类型的消息
//				message = messageService.processUnknowMsg(sourceMapMsg);
//				break;
//			}
//			
//			//如果respMsg没有经过特殊的处理,依旧为原始值null
//			if(respMsg==null){
//				//如果没有根据关键字找到想要的回复信息,如果没有进入直接返回null,否则进入正常的回复
//				respMsg = (message==null) ? null: encapsulationService.encapsulationMsg(sourceMapMsg, message);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		
//		//如果最总返回的消息为空,说明1.消息无法识别,2. 数据库中没有对应的关键字,
//		//所以为了避免进入死循环,返回一条后台设定的写死的消息
//		if(respMsg==null){
//			//如果没有获取到相应的返回消息,则返回帮助消息列表
//			respMsg = encapsulationService.encapsulationNoMsg(sourceMapMsg);
//		}
//		//返回封装好的,要发给用户的消息(xml格式的字符串)
//		return respMsg;
//	}
//}
