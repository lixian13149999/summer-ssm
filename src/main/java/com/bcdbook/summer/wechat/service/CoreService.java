package com.bcdbook.summer.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Title: CoreService.java
 * @Description: 处理用户消息的核心方法类
 * @author lason
 * @created 2016年5月25日 下午1:21:37
 */
public interface CoreService {
	/**
	 * 
	 * @Discription 解析用户发来的所有请求
	 * @author lason
	 * @created 2016年5月25日 下午1:22:13
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request);
	
}
