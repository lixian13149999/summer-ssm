package com.bcdbook.summer.wechat.message.pojo.req;

/**
 * 
 * @Title: LinkMessage.java
 * @Description: 连接消息
 * @author lason
 * @created 2016年5月25日 上午9:07:02
 */
public class LinkMessage extends BaseMessage {
	/**
	 * 消息标题
	 */
	private String Title;
	/**
	 * 消息描述
	 */
	private String Description;
	/**
	 * 消息链接
	 */
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
