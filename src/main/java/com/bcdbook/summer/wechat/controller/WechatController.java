package com.bcdbook.summer.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdbook.summer.common.util.XMLUtil;
import com.bcdbook.summer.wechat.pojo.Wechat;
import com.bcdbook.summer.wechat.pojo.message.WechatMessage;
import com.bcdbook.summer.wechat.util.WechatUtil;

/**
 * @Description: 微信主控制器
 * @author lason
 * @date 2016年9月20日
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	private static Logger logger = Logger.getLogger(WechatController.class);
	
//	@Resource
//	private WechatService wechatService;
//	@Resource
//	private WechatMessageService wechatMessageService;
//	@Resource
//	private WechatEventService wechatEventService;

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
	 * 1. 接收消息
	 * 2. 把接收到的消息封装成map对象
	 * 3. 根据消息的类型不同,调用不同的接口处理相应的消息数据
	 * 4. 获取对应接口处理后的返回值
	 * 5. 输出给微信服务器
	 * @param request
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest req, HttpServletResponse resp) {
		//定义用于返回的数据
//		String respMessage = "success";
		
		/* 
		 * 消息的接收、处理、响应
		 */
		try {
			req.setCharacterEncoding("UTF-8");//将请求、响应的编码均设置为UTF-8（防止中文乱码）
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");//设置输入的字符编码

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
		
		//如果获取到的解析后的消息对象不为空,继续下面操作
		if(reqMapMsg!=null){
		
			/*
			 * 2. 把解析后的结果放到request中,以方便调用
			 */
			req.setAttribute("reqMapMsg", reqMapMsg);
			
			/*
			 * 3. 分辨是什么类型的数据,并调用对对应的处理器进行处理
			 */
			String msgType = reqMapMsg.get("MsgType");// 获取消息的类型
			
			if(!msgType.equals(WechatMessage.EVENT)){
				//如果是事件,则转发到事件的处理方法
				try {
					req.getRequestDispatcher("/wechat/message").forward(req,resp);
				} catch (ServletException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				//如果是消息,则转发到消息的处理方法
				try {
					req.getRequestDispatcher("/wechat/event").forward(req,resp);
				} catch (ServletException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		System.out.println("正常情况下,这条信息不应该被输出");
	}
}
