package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 用于接收连接消息的类
 * @author lason
 * @date 2016年9月19日
 */
public class LinkMessage extends WechatReqMessage {
	private String Title;//消息标题
	private String Description;//消息描述
	private String Url;//消息链接
	//空参
	public LinkMessage() {
		super();
	}
	//全参
	public LinkMessage(String title, String description, String url) {
		super();
		Title = title;
		Description = description;
		Url = url;
	}
	//getter and setter
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
	//toString
	@Override
	public String toString() {
		return "WechatLinkMessage ["
				+ (Title != null ? "Title=" + Title + ", " : "")
				+ (Description != null ? "Description=" + Description + ", "
						: "") + (Url != null ? "Url=" + Url : "") + "]";
	}
}
