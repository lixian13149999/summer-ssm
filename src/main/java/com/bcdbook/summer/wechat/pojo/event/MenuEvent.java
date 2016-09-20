package com.bcdbook.summer.wechat.pojo.event;

/**
 * 
     * @Title: MenuEvent.java    
     * @Description: 自定义菜单点击事件
     * @author lason       
     * @created 2016年5月27日 上午10:05:54
 */
public class MenuEvent extends WechatEvent {
	
	private String EventKey;//事件KEY值，与自定义菜单接口中KEY值对应
	
	//空参构造
	public MenuEvent() {
		super();
	}
	//全参构造
	public MenuEvent(String eventKey) {
		super();
		EventKey = eventKey;
	}

	//getter and setter
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	//toString
	@Override
	public String toString() {
		return "MenuEvent [EventKey=" + EventKey + "]";
	}
}
