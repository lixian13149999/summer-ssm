package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 微信,用来回复消息的基类
 * @author lason
 * @date 2016年9月19日
 */
public class WechatRespMessage {
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private String CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	
	//空参构造
	public WechatRespMessage() {
		super();
	}
	//全参构造
	public WechatRespMessage(String toUserName, String fromUserName,
			String createTime, String msgType) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
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
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	//toString
	@Override
	public String toString() {
		return "WechatMessage [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + "]";
	}
	
}
