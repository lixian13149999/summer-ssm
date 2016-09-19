package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 微信,用来接收消息的基类
 * @author lason
 * @date 2016年9月19日
 */
public class Message {
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private String CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	private String MsgId;//消息id，64位整型
	
	//空参构造
	public Message() {
		super();
	}
	//全参构造
	public Message(String toUserName, String fromUserName,
			String createTime, String msgType, String msgId) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		MsgId = msgId;
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
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	@Override
	public String toString() {
		return "WechatMessage ["
				+ (ToUserName != null ? "ToUserName=" + ToUserName + ", " : "")
				+ (FromUserName != null ? "FromUserName=" + FromUserName + ", "
						: "")
				+ (CreateTime != null ? "CreateTime=" + CreateTime + ", " : "")
				+ (MsgType != null ? "MsgType=" + MsgType + ", " : "")
				+ (MsgId != null ? "MsgId=" + MsgId : "") + "]";
	}
}
