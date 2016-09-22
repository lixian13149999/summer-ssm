package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 单个文图消息的基类
 * @author lason
 * @date 2016年9月20日
 */
public class Article {
	private String Title;//图文消息标题
	private String Description;//图文消息描述
	private String PicUrl;//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String Url;//点击图文消息跳转链接
	
	//空参构造
	public Article() {
		super();
	}
	//全参构造
	public Article(String title, String description, String picUrl, String url) {
		super();
		Title = title;
		Description = description;
		PicUrl = picUrl;
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
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
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
		return "Articles ["
				+ (Title != null ? "Title=" + Title + ", " : "")
				+ (Description != null ? "Description=" + Description + ", "
						: "")
				+ (PicUrl != null ? "PicUrl=" + PicUrl + ", " : "")
				+ (Url != null ? "Url=" + Url : "") + "]";
	}
}
