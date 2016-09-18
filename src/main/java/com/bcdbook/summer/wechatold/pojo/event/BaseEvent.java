package com.bcdbook.summer.wechatold.pojo.event;

/**
 * 
     * @Title: BaseEvent.java    
     * @Description: 事件消息的基类
     * @author lason       
     * @created 2016年5月27日 上午10:04:36
 */
public class BaseEvent {
	private String ToUserName;//开发者微信号(公众号id)
	private String FromUserName;//发送方帐号（一个OpenID）
	private long CreateTime;//消息创建时间
	private String MsgType;//消息类型,event
	private String Event;//事件类型
	
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
	
	
}
