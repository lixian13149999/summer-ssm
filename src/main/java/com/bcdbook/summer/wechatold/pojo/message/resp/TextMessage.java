package com.bcdbook.summer.wechatold.pojo.message.resp;

/**
 * 
     * @Title: TextMessage.java    
     * @Description: 文本消息
     * @author lason       
     * @created 2016年5月25日 上午9:53:19
 */
public class TextMessage extends BaseMessage {
	/**
	 * 回复的消息内容
	 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
