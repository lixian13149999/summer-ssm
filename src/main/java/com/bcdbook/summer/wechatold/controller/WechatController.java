//package com.bcdbook.summer.wechatold.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.bcdbook.summer.common.enums.BaseEnum.BackMsg;
//import com.bcdbook.summer.wechatold.pojo.message.Message;
//import com.bcdbook.summer.wechatold.service.ConnectService;
//import com.bcdbook.summer.wechatold.service.CoreService;
//import com.bcdbook.summer.wechatold.service.MaterialService;
//import com.bcdbook.summer.wechatold.service.MessageService;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//@Controller
//@RequestMapping("/wechatOld")
//public class WechatController {
//	@Resource
//	private ConnectService connectService;
//	@Resource
//	private CoreService coreService;
//	@Resource
//	private MaterialService materialService;
//	@Resource
//	private MessageService messageService;
//
//	private Logger log = Logger.getLogger(WechatController.class);
//
//	/**
//	 * 校验信息是否是从微信服务器发过来的。
//	 * 
//	 * @param weChat
//	 * @param out
//	 */
//	@RequestMapping(method = { RequestMethod.GET })
//	public void get(HttpServletRequest request, HttpServletResponse response) {
//		PrintWriter out = null;// 定义输出流
//		try {
//			out = response.getWriter();// 通过response获取输出流
//			// 执行正,并判断验证是否成功,如果验证能通过
//			if (connectService.safeuuid(request)) {
//				out.print(request.getParameter("echostr"));// 输出生成的echostr(生成后的秘钥)
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 刷出流中的信息,并关闭流
//			out.flush();
//			out.close();
//		}
//	}
//
//	/**
//	 * 微信消息的处理
//	 * 
//	 * @param request
//	 * @param out
//	 * @throws IOException
//	 */
//	@RequestMapping(method = RequestMethod.POST)
//	public void post(HttpServletRequest request, HttpServletResponse response) {
//		/* 
//		 * 消息的接收、处理、响应
//		 */
//		try {
//			request.setCharacterEncoding("UTF-8");// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		response.setCharacterEncoding("UTF-8");//设置输入的字符编码
//
//		// 调用核心业务类接收消息、处理消息,
//		// 并返回要发送给客户的信息
//		String respMessage = coreService.processRequest(request);
////		String respMessage2 = coreService.processRequest(request);
//		// log.info(respMessage);
//		// 响应消息
//		PrintWriter out = null;
//		try {
//			//获取流并输出要返回的信息
//			out = response.getWriter();
//			out.print(respMessage);
//			out.print(respMessage);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			out.flush();
//			out.close();
//			out = null;
//		}
//	}
//
//	/**
//	 * 
//	 * @Discription 获取登陆页面的controller
//	 * @author lason
//	 * @created 2016年6月6日 下午9:14:59
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/login", method = { RequestMethod.GET })
//	public String getLoginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
//		return "wechat/login";//直接返回到登陆页面
//	}
//
//	/**
//	 * 
//	 * @Discription 执行登陆的controller
//	 * @author lason
//	 * @created 2016年6月6日 下午9:15:17
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/login", method = { RequestMethod.POST })
//	@ResponseBody
//	public Object doLogin(HttpServletRequest request,
//			HttpServletResponse response) {
//		System.out.println("login post");
//		String openid = request.getParameter("openid");
//		String username = request.getParameter("username");
//		String pwd = request.getParameter("pwd");
//
//		// TODO--这里需要调用userService的登陆验证,进行用户名和密码的验证,验证通过后对微信的openid进行绑定操作
//		// 绑定完成后需要把user写入session,方便其他部分的权限验证
//
//		// System.out.println("进入login POST");
//		// // System.out.println(data);
//		// System.out.println("openid:"+openid);
//		// System.out.println("username:"+username);
//		// System.out.println("pwd:"+pwd);
//		// 执行成功后返回success,否则返回error
//		return BackMsg.SUCCESS.getValue();
//	}
//
//	/**
//	 * 
//	 * @Discription 用于模拟的用户登陆成功后的页面
//	 * @author lason
//	 * @created 2016年6月6日 下午9:18:05
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/home", method = { RequestMethod.GET })
//	public String getUserHome(HttpServletRequest request,
//			HttpServletResponse response) {
//		System.out.println("home");
//		return "wechat/userHome";
//	}
//
//	/**
//	 * 
//	 * @Discription 获取code并提示授权,授权后跳转到登陆界面
//	 * @author lason
//	 * @created 2016年6月6日 下午9:19:28
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/getCode")
//	public String getCode(HttpServletRequest request,
//			HttpServletResponse response) {
//		System.out.println("进入getCode");
//		String getCodeUrl = connectService.getCode();
//		try {
//			System.out.println("将要执行转发");
//			response.sendRedirect(getCodeUrl);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Discription 调用各种借口,反复换值和调用,获取用户详细信息的方法
//	 * @author lason
//	 * @created 2016年6月6日 下午9:18:31
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/getUserInfo", method = { RequestMethod.GET })
//	public String getUserInfo(HttpServletRequest request,
//			HttpServletResponse response, Model model) {
//		System.out.println("getUserInfo");
//		String code = request.getParameter("code");// 获取code
//		String state = request.getParameter("state");// 获取state
//
////		System.out.println(code);
////		System.out.println(state);
//		// 接收通过code交换得到的一些信息
//		String endorData = null;
//		// 用来解析交换后信息的json对象
//		JSONObject endorDataJone = null;
//		if (code != null) {
//			// "041EaVQ020c7B41D0YR02x1WQ02EaVQZ"
//			endorData = connectService.exchangeAccessToken(code);// 用code交换accesstoken
//			endorDataJone = JSON.parseObject(endorData);
//
//			String aT = endorDataJone.getString("access_token");
//			if (endorData != null && aT != null) {
//				// 获取交换得来的accessToken
//				String accessToken = endorDataJone.getString("access_token");
//				// 如果不为空,说明调用成功了
//				if (accessToken != null) {
//					String openId = endorDataJone.getString("openid");// 提取交换来的openid
//					String userInfo = connectService.getWechatUserInfo(
//							accessToken, openId);// 请微信接口,用于获取用户的详细信息
//					if (userInfo != null) {
//						JSONObject userJson = JSON.parseObject(userInfo);
//						String infoOpenId = userJson.getString("openid");
//						if (infoOpenId != null) {
////							System.out.println(code);
////							System.out.println(state);
////							System.out.println(infoOpenId);
////							System.out.println(userJson.getString("nickname"));
////							System.out.println(userJson.getString("sex"));
////							System.out.println(userJson.getString("sex"));
////							System.out.println(userJson.getString("city"));
////							System.out.println(userJson.getString("country"));
////							System.out.println(userJson.getString("headimgurl"));
//							model.addAttribute("code", code);
//							model.addAttribute("state", state);
//							model.addAttribute("openid", infoOpenId);
//							model.addAttribute("nickname",
//									userJson.getString("nickname"));
//							model.addAttribute("sex", userJson.getString("sex"));
//							model.addAttribute("city",
//									userJson.getString("city"));
//							model.addAttribute("country",
//									userJson.getString("country"));
//							model.addAttribute("headimgurl",
//									userJson.getString("headimgurl"));
//						}
//					}
//				}
//			}
//		}
//		return "wechat/login";
//	}
//
//	/**
//	 * 
//	 * @Discription 创建栏目的方法
//	 * @author lason
//	 * @created 2016年6月6日 下午9:19:17
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/createMenu")
//	@ResponseBody
//	public String getUser(HttpServletRequest request) {
//		connectService.createMenu();
//		return "user/getUser";
//	}
//
//	/**
//	 * 
//	 * @Discription 刷新素材的控制器
//	 * @author lason
//	 * @created 2016年6月6日 下午9:20:24
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/refreshMaterial")
//	@ResponseBody
//	public String refreshMaterialToLocal(HttpServletRequest request,
//			HttpServletResponse response) {
//		materialService.refreshMaterialToLocal();
//		return "refreshMaterial success";
//	}
//
//	/*
//	 * 消息处理相关方法================开始
//	 */
//	@RequestMapping(value = "/listMsg", method = { RequestMethod.GET })
//	public String listMsg(HttpServletRequest request,
//			HttpServletResponse response) {
//		String pageString = request.getParameter("page");// 页码
//		String sizeString = request.getParameter("size");// 每页的显示数量
//		String typeString = request.getParameter("type");// 消息类型
//		String keyword = request.getParameter("keyword");// 关键字(这里的关键字不仅仅对应keyword字段,同时还对应name和content字段)
//
//		int page = (pageString == null || pageString.equals("")) ? 0 : Integer
//				.parseInt(pageString);// 把页码转成int类型,默认为0
//		int size = (sizeString == null || sizeString.equals("")) ? 12 : Integer
//				.parseInt(sizeString);// 把每页的显示数量转成int类型,默认为12条数据
//
//		List<Message> messages = messageService.listMsgByCondition(page, size,
//				typeString, keyword);// TODO 这里需要实现
//		return "wechat/listMsg";
//	}
//
//	// 获取添加页面的方法(get请求)
//	@RequestMapping(value = "/addMsg", method = { RequestMethod.GET })
//	public String toAddMsg(HttpServletRequest request,
//			HttpServletResponse response) {
//		return "wechat/addMsg";
//	}
//
//	// TODO 执行消息添加的方法(post请求)
//	@RequestMapping(value = "/addMsg", method = { RequestMethod.POST })
//	public String addMsg(HttpServletRequest request,
//			HttpServletResponse response, Message message) {
//		// System.out.println(message.getName());
//		// 保存:此处有时间要添加验证，如果为空就不能被提交，--先在前端添加了js验证
//
//		messageService.addMsg(message);
//
//		try {
//			response.sendRedirect("wechat/listMsg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// //获取Message列表
//		// List<Message> msgList=messageService.getAllMsg();
//		// request.setAttribute("msgList", msgList);
//		// TODO 这里需要重点考虑
//		return "wechat/listMsg";
//	}
//
//	// TODO 删除一条，msg的方法(get请求)
//	@RequestMapping(value = "/deleteMsg", method = { RequestMethod.GET })
//	public void toDelMsg(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		// 向ajax请求返回
//		// System.out.println("ajax方法进入。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//		PrintWriter out = response.getWriter();
//		String msgId = request.getParameter("msgId");
//		messageService.removeById(Integer.valueOf(msgId));
//		// System.out.println(msgId);
//		out.print("1");
//		// return "wechat/listMsg";
//	}
//
//	// TODO 去修改msg的方法(get请求)
//	@RequestMapping(value = "/editMsg", method = { RequestMethod.GET })
//	public String toModifyMsg(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		// 获取要修改的msg 的id 查询到该id对应的那条记录
//		String msgId = request.getParameter("id");
//
//		Message msg = messageService.getMsgById(msgId);
//		request.setAttribute("msg", msg);
//		return "wechat/toModify";
//
//	}
//
//	// TODO 修改msg的方法(get请求)
//	@RequestMapping(value = "/editMsg", method = { RequestMethod.POST })
//	public String modifyMsg(HttpServletRequest request,
//			HttpServletResponse response, Message msg) throws IOException {
//		// 获取要修改的msg 的id 查询到该id对应的那条记录
//		messageService.updateMsg(msg);
//		return "wechat/listMsg";
//
//	}
//
//	// =======================用于测试的方法上线时需要删除=======================================================
//	@RequestMapping("/getTest")
//	public String getTest(HttpServletRequest request,
//			HttpServletResponse response) {
//		return "wechat/test";
//	}
//
//	@RequestMapping("/toTest")
//	public String toTest() {
//
//		connectService.addAccessToken();
//		return "test/testSuccess";
//	}
//
//	@RequestMapping("/html")
//	public void getHtml(HttpServletRequest request,
//			HttpServletResponse response, Model model) {
//		try {
//			request.getRequestDispatcher("/static/wechat/login.html").forward(
//					request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
