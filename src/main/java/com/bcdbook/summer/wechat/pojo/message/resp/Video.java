package com.bcdbook.summer.wechat.pojo.message.resp;

/**
     * @Title: WechatVideo.java    
     * @Description: 微信视频素材
     * @author lason       
     * @created 2016年9月19日 下午7:14:32
 */
public class Video {
	private String MediaId;//通过素材管理中的接口上传多媒体文件，得到的id。
	private String Title;//视频消息的标题
	private String Description;//视频消息的描述

	//空参构造
	public Video() {
		super();
	}
	//全参构造
	public Video(String mediaId, String title, String description) {
		super();
		MediaId = mediaId;
		Title = title;
		Description = description;
	}
	
	//getter and setter
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
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
	
	//toString
	@Override
	public String toString() {
		return "WechatVideo [MediaId=" + MediaId + ", Title=" + Title
				+ ", Description=" + Description + "]";
	}
	
}
