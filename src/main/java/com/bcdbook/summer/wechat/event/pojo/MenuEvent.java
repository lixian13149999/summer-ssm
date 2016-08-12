package com.bcdbook.summer.wechat.event.pojo;

/**
 * 
     * @Title: MenuEvent.java    
     * @Description: 自定义菜单消息
     * @author lason       
     * @created 2016年5月27日 上午10:05:54
 */
public class MenuEvent extends BaseEvent {
	private String EventKey;//事件KEY值，与自定义菜单接口中KEY值对应

	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
