package com.bcdbook.summer.wechat.pojo.event;

/**
 * 
     * @Title: BaseEvent.java    
     * @Description: 事件消息的基类
     * @author lason       
     * @created 2016年5月27日 上午10:04:36
 */
public class Event {
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private long CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型,event
	private String Event;//事件类型
	
	//空参构造
	public Event() {
		super();
	}
	//全参构造
	public Event(String toUserName, String fromUserName, long createTime,
			String msgType, String event) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Event = event;
	}

	//getter and setter
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	
	//toString
	@Override
	public String toString() {
		return "Event [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Event=" + Event + "]";
	}
}
