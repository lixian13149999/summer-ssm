package com.bcdbook.summer.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.pojo.User;

/**
 * @author lason 拦截器的类,使用spring的AOP用来拦截事物处理相关的事项
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
//	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	private static Logger logger = Logger.getLogger(CommonInterceptor.class);
	
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
		String aa = req.getRequestURL().toString();
//		System.out.println("====================="+aa);
		logger.info("请求路径:"+aa);
		
		//从session中获取user对象
		User user = (User) req.getSession().getAttribute(Global.ONLINE_USER);
//		System.out.println(user);
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
		
		return true;
		
		
		//拦截器中,可以直接获取request中的值
//		System.out.println(request.getParameter("type"));
//		System.out.println(request.getPathInfo());
//		String path = request.getContextPath();
//		
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"aa="+aa;
//		System.out.println("进入拦截器preHandle");
//		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
//		return false;
////		if ("GET".equalsIgnoreCase(request.getMethod())) {
////			RequestUtil.saveRequest();
////		}
//		log.info("==============执行顺序: 1、preHandle================");
//		String requestUri = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String url = requestUri.substring(contextPath.length());
//
//		log.info("requestUri:" + requestUri);
//		log.info("contextPath:" + contextPath);
//		log.info("url:" + url);
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
