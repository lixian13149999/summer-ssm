package com.bcdbook.summer.wechat.pojo.message.req;

import com.bcdbook.summer.wechat.pojo.message.WechatMessage;

/**
 * @Description: 微信,用来接收消息的基类
 * @author lason
 * @date 2016年9月19日
 */
public class WechatReqMessage extends WechatMessage{
	private String MsgId;//消息id，64位整型
	
	//空参构造
	public WechatReqMessage() {
		super();
	}
	//全参构造
	public WechatReqMessage(String msgId) {
		super();
		MsgId = msgId;
	}
	
	//getter and setter
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	//toString
	@Override
	public String toString() {
		return "WechatReqMessage [" + (MsgId != null ? "MsgId=" + MsgId : "")
				+ "]";
	}
	
	
}
