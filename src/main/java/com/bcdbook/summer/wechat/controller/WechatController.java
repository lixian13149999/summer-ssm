package com.bcdbook.summer.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdbook.summer.common.util.XMLUtil;
import com.bcdbook.summer.wechat.pojo.Wechat;
import com.bcdbook.summer.wechat.service.WechatService;
import com.bcdbook.summer.wechat.util.WechatUtil;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	private static Logger logger = Logger.getLogger(WechatController.class);
	
	@Resource
	private WechatService wechatService;

	/**
	 * 校验信息是否是从微信服务器发过来的。
	 * 
	 * @param weChat
	 * @param out
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public void get(HttpServletRequest req, HttpServletResponse resp) {
		//获取微信校验时传入的参数
		String signature = req.getParameter("signature");//获取加密签名
		String timestamp = req.getParameter("timestamp");//时间戳
		String nonce = req.getParameter("nonce");//随机数
		String echostr = req.getParameter("echostr");//随机字符串
		logger.info("进入wechat get:  "+signature+"    "+timestamp+"    "+nonce+"    "+echostr);
		
		PrintWriter out = null;// 定义输出流
		try {
			out = resp.getWriter();// 通过response获取输出流
			// 执行正,并判断验证是否成功,如果验证能通过
			if (WechatUtil.safeUuid(signature,Wechat.TOKEN, timestamp, nonce)) {
				out.print(echostr);// 输出生成的echostr(生成后的秘钥)
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				// 刷出流中的信息,并关闭流
				out.flush();
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * 微信消息的处理
	 * 
	 * @param request
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest req, HttpServletResponse resp) {
		/* 
		 * 消息的接收、处理、响应
		 */
		try {
			req.setCharacterEncoding("UTF-8");//将请求、响应的编码均设置为UTF-8（防止中文乱码）
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");//设置输入的字符编码

		// 调用核心业务类接收消息、处理消息,
		// 并返回要发送给客户的信息
		String respMessage = processWechat(req);
//		String respMessage2 = coreService.processRequest(request);
		// log.info(respMessage);
		// 响应消息
		PrintWriter out = null;
		try {
			//获取流并输出要返回的信息
			out = resp.getWriter();
			out.print(respMessage);
			out.print(respMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.flush();
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * @Description: 
	 * 1. 把微信发转发过来的信息进行解析(xml==>map)
	 * 2. 分辨是什么类型的数据,并调用对对应的处理器进行处理
	 * 3. 返回处理器的返回结果
	 * @param @param req
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	public String processWechat(HttpServletRequest req){
		String backMsg = "";
		
		/*
		 * 1. 把微信发转发过来的信息进行解析(xml==>map)
		 */
		//定义用来获取解析值的map对象
		Map<String, String> reqMapMsg = null;
		try {
			//把request中传入的值进行解析,获取解析后的map对象
			reqMapMsg = XMLUtil.parseXml(req.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if(reqMapMsg==null)
			return backMsg;
		
		/*
		 * 2. 分辨是什么类型的数据,并调用对对应的处理器进行处理
		 */
		String msgType = reqMapMsg.get("MsgType");// 获取消息的类型
		//根据不同的消息类型,选用不同的处理器进行处理
//		switch (msgType) {
//		case "text":
//			//解析文本消息
//			backMsg = messageService.processTextMsg(sourceMapMsg);
//			break;
//		case "image":
//			//解析图片消息
//			backMsg = messageService.processImageMsg(sourceMapMsg);
//			break;
//		case "voice":
//			//解析语音消息
//			backMsg = messageService.processVoiceMsg(sourceMapMsg);
//			break;
//		case "video":
//			//解析视频消息
//			backMsg = messageService.processVideoMsg(sourceMapMsg);
//			break;
//		case "shortvideo":
//			//解析短视频消息
//			backMsg = messageService.processShortvideoMsg(sourceMapMsg);
//			break;
//		case "location":
//			//解析位置消息
//			backMsg = messageService.processLocationMsg(sourceMapMsg);
//			break;
//		case "link":
//			//解析连接消息
//			backMsg = messageService.processLinkMsg(sourceMapMsg);
//			break;
//		case "event":
//			//解析事件消息
//			backMsg = eventService.processEvent(sourceMapMsg);
//			break;
//		default:
//			//解析无法识别类型的消息
//			backMsg = "";
//			break;
//		}
		
		return backMsg;
	}
	
	/**
	 * 以下是测试相关的处理,正式上线时需要删除
	 * 
	 */
//	@RequestMapping(method = { RequestMethod.GET })
//	public void get(HttpServletRequest req, HttpServletResponse resp,Model model){
//		System.out.println(req);
//
//		Model a = model;
//		System.out.println(model);
//	}
}
