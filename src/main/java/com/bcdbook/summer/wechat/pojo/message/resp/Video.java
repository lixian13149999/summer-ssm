package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
     * @Title: Video.java    
     * @Description: 回复的视频消息基类
     * @author lason       
     * @created 2016年5月25日 下午12:19:09
 */
public class Video {
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id
	 */
	private String MediaId;
	/**
	 * 视频消息的标题
	 */
	private String Title;
	/**
	 * 视频消息的描述
	 */
	private String Description;
	
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
	
	
}
