package com.bcdbook.summer.wechat.pojo.event;

/**
 * 
     * @Title: ScanEvent.java    
     * @Description: 扫描事件(分为关注前的事件和关注后的事件)
     * @author lason       
     * @created 2016年5月27日 上午10:06:14
 */
public class ScanEvent extends BaseEvent{
	private String EventKey; //事件KEY值，qrscene_为前缀，后面为二维码的参数值
	private String Ticket;//二维码的ticket，可用来换取二维码图片
	
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
