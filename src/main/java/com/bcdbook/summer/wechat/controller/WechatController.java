package com.bcdbook.summer.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.util.SessionUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.common.util.XMLUtil;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.system.service.UserService;
import com.bcdbook.summer.wechat.pojo.Wechat;
import com.bcdbook.summer.wechat.pojo.message.WechatMessage;
import com.bcdbook.summer.wechat.service.WechatService;
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
	
	@Resource
	private WechatService wechatService;
	@Resource
	private UserService userService;
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
	
	/**
	 * @Description: 获取code的方法
	 * @param @param req
	 * @param @param resp   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年9月26日
	 */
	@RequestMapping(value="getCode" ,method = RequestMethod.GET)
	public void getCode(HttpServletRequest req, HttpServletResponse resp) {
		//获取微信重定向时附带的参数
		String state = req.getParameter("state");
		state = StringUtils.isNull(state)?"signin":state;
		//获取封装好的url
		String getCodeUrl = WechatUtil.packageGetCodeUrl(Wechat.REDIRECT_URL, Wechat.SNSAPI_BASE, state);
		if(getCodeUrl!=null){
			try {
				resp.sendRedirect(getCodeUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Description: 用于微信端登录的接口
	 * @param @param req
	 * @param @param resp   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年9月26日
	 */
	@RequestMapping(value="signin" ,method = RequestMethod.POST)
	public String signin(HttpServletRequest req, HttpServletResponse resp) {
		//和微信交互获取的code值
		String code = req.getParameter("code");
		//获取微信重定向时附带的参数
		String state = req.getParameter("state");
		if(StringUtils.isNull(code))
			return BackMsg.error("code is null");
		
		String backData = WechatUtil.getWebAccessTokenAndOpenId(code);
		//验证参数的合法性
		if(StringUtils.isNull(backData))
			return BackMsg.error("backData is null");
		
		//把通过code换取来的数据转换成json格式
		JSONObject jsonData = JSONObject.parseObject(backData);
		//如果返回的数据是错误的,则直接返回null
		if(!StringUtils.isNull(jsonData.getString("errmsg")))
			return BackMsg.error("get webAccessToken error");
		
		String openId = jsonData.getString("openid");
		//验证获取的openId的合法性
		if(StringUtils.isNull(openId))
			return BackMsg.error("openId is null");
		//创建user对象,以便于查询
		User user = new User();
		user.setOpenId(openId);//封装查询参数到用于查询的对象中
		List<User> userList = userService.findList(user);
		//检测获取到的用户集合是否合法
		if(userList==null||userList.size()!=1)
			return BackMsg.error("userList error");
		
		//从集合中获取一个登录验证成功的User对象
		User onlineUser = userList.get(0);
		//把User写入到session
		if(!SessionUtil.refresh(req, Global.ONLINE_USER, onlineUser))
			return BackMsg.error("refresh session error");
		
		return BackMsg.success("wechat signin success");
	}
}
