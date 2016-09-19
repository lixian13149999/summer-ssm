package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 用于回复的文本消息
 * @author lason
 * @date 2016年9月19日
 */
public class TextMessage extends Message {
	private String Content;//文本消息内容

	//空参构造
	public TextMessage() {
		super();
	}
	//全参构造
	public TextMessage(String content) {
		super();
		Content = content;
	}

	//getter and setter
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	@Override
	public String toString() {
		return "WechatTextMessage ["
				+ (Content != null ? "Content=" + Content : "") + "]";
	}
}
