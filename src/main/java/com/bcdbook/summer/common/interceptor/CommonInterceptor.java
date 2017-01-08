package com.bcdbook.summer.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.util.PhoneUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.pojo.User;

/**
 * @author lason 拦截器的类,使用spring的AOP用来拦截事物处理相关的事项
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
//	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	private static Logger logger = Logger.getLogger(CommonInterceptor.class);
	//修改gd一点东西测试一下
	
	public static final String LAST_PAGE = "com.alibaba.lastPage";

	/*
	 * 利用正则映射到需要拦截的路径
	 * 
	 * private String mappingURL;
	 * 
	 * public void setMappingURL(String mappingURL) { this.mappingURL =
	 * mappingURL; }
	 */
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest req,
			HttpServletResponse resp, Object handler) throws Exception {
		
		String userAgent = req.getHeader("User-Agent");
		boolean isMobile = PhoneUtil.isMobile(userAgent.toLowerCase());
		
		/*
		 * 服务器的操作系统信息  
		 */
		String osName = System.getProperty("os.name");//win2003竟然是win XP？  
		String osVersion = System.getProperty("os.version");//系统版本  
		String osArch = System.getProperty("os.arch");//底层结构(x86)

		/*
		 * 获取请求中相关参数
		 */
		String getContextPath = req.getContextPath();// 
		String getParameter = req.getParameter("type");// 
		String getPathInfo = req.getPathInfo();// 
		String getScheme = req.getScheme();// 
		String getMethod = req.getMethod();//获得客户端向服务器端传送数据的方法有GET、POST、PUT等类型  
		String getRequestURI = req.getRequestURI();//获得发出请求字符串的客户端地址  
		String getServletPath = req.getServletPath();//获得客户端所请求的脚本文件的文件路径  
		String getServerName = req.getServerName();//获得服务器的名字,若失败，则返回服务器端电脑的IP地址 
		int getServerPort = req.getServerPort();//获得服务器的端口号
		
		/*
		 * 获取客户端的相关信息
		 */
		String getRemoteAddr = req.getRemoteAddr();//获得客户端的IP地址  
		String getRemoteHost = req.getRemoteHost();//获得客户端电脑的名字,若失败，则返回客户端电脑的IP地址  
		String getProtocol = req.getProtocol();//获取请求协议
		Enumeration<String> getHeaderNames = req.getHeaderNames();//返回所有request header的名字，结果集是一个Enumeration（枚举）类的实例  
		

		
		String aa = req.getRequestURL().toString();
		logger.info("请求路径:"+aa);
		
		//从session中获取user对象
		User user = (User) req.getSession().getAttribute(Global.ONLINE_USER);
		
		if(!isMobile){
			//判断user是否为空
			if(user==null||StringUtils.isNull(user.getUserName())){
				logger.info("拦截路径:"+aa);
				//如果user为空,则转发到登录界面
				resp.sendRedirect(Global.getProjPash()+"signin");
				return false;
			}
			
			//判断用户是否为锁定状态
			if(user.getIsLock()==User.LOCK){
				//如果是锁定状态
				//- TODO 锁定状态需要做对应的处理
			}
			
			//判断用户的邮箱是否为绑定状态
			if(user.getEmailState()==User.UNBOUND){
				//如果是未绑定状态
				//邮箱未绑定状态,需要做特殊的处理
				resp.sendRedirect(Global.getProjPash()+"user/verifyEmailPage");
				return false;
			}
			
			if(user.getWechatState()==User.UNBOUND){
				//如果微信是未绑定状态
				//- TODO 如果微信未绑定,需要做的特殊处理
			}
		}else{
			//判断user是否为空
			if(user==null||StringUtils.isNull(user.getUserName())){
				logger.info("拦截路径:"+aa);
				//如果user为空,则转发到登录界面
				resp.sendRedirect(Global.getProjPash()+"m/signin");
				return false;
			}
			
			//判断用户是否为锁定状态
			if(user.getIsLock()==User.LOCK){
				//如果是锁定状态
				//- TODO 锁定状态需要做对应的处理
			}
			
			//判断用户的邮箱是否为绑定状态
			if(user.getEmailState()==User.UNBOUND){
				//如果是未绑定状态
				//邮箱未绑定状态,需要做特殊的处理
				resp.sendRedirect(Global.getProjPash()+"user/verifyEmailPage");
				return false;
			}
			
			if(user.getWechatState()==User.UNBOUND){
				//如果微信是未绑定状态
				//- TODO 如果微信未绑定,需要做的特殊处理
			}
		}
		return true;
		
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
////		log.info("==============执行顺序: 2、postHandle================");
////		if (modelAndView != null) { // 加入当前时间
////			modelAndView.addObject("var", "测试postHandle");
////		}
//	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		log.info("==============执行顺序: 3、afterCompletion================");
//	}
}
