package com.bcdbook.summer.wechat.service;

import java.util.Map;

import com.bcdbook.summer.wechat.message.pojo.Message;

/**
 * 
 * @Title: EventService.java
 * @Description: 处理事件消息的service
 * @author lason
 * @created 2016年5月27日 上午10:36:04
 */
public interface EventService {

	/**
	 * 
	 * @Discription 处理事件消息的主控制器
	 * @author lason
	 * @created 2016年5月27日 上午10:10:11
	 * @param msg
	 * @return
	 */
	public Message processEvent(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理关注事件
	 * @author lason
	 * @created 2016年5月27日 上午10:48:33
	 * @param msg
	 * @return
	 */
	public Message processSubscribeEvent(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理取消关注事件
	 * @author lason
	 * @created 2016年5月27日 上午10:48:50
	 * @param msg
	 * @return
	 */
	public Message processUnSubscribeEvent(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理扫描事件
	 * @author lason
	 * @created 2016年5月27日 上午10:49:03
	 * @param msg
	 * @return
	 */
	public Message processSCANEvent(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理位置信息事件
	 * @author lason
	 * @created 2016年5月27日 上午10:49:25
	 * @param msg
	 * @return
	 */
	public Message processLOCATIONEvent(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理菜单栏按钮点击事件
	 * @author lason
	 * @created 2016年5月27日 上午10:49:58
	 * @param msg
	 * @return
	 */
	public Message processCLICKEvent(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理菜单栏,连接点击事件
	 * @author lason
	 * @created 2016年5月27日 上午10:50:19
	 * @param msg
	 * @return
	 */
	public Message processVIEWEvent(Map<String, String> msg);

}
