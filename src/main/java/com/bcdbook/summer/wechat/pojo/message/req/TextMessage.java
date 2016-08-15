package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * 
 * @Title: TextMessage.java
 * @Description: 文本消息
 * @author lason
 * @created 2016年5月25日 上午9:04:34
 */
public class TextMessage extends BaseMessage {
	/**
	 * 接收到的消息内容
	 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
