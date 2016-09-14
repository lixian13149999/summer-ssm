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
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String aa = request.getRequestURL().toString();
//		System.out.println("====================="+aa);
		logger.info("请求路径:"+aa);
		
		User user = (User) request.getSession().getAttribute(Global.ONLINE_USER);
		if(user==null||StringUtils.isNull(user.getUserName())){
			logger.info("拦截路径:"+aa);
			response.sendRedirect("signin");
			return false;
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
//
//		String username = (String) request.getSession().getAttribute("user");
//		if (username == null) {
//			log.info("Interceptor：跳转到login页面！");
//			return false;
//		} else
//			return true;
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
