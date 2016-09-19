package com.bcdbook.summer.wechat.pojo.message.resp;
/**
 * 
     * @Title: Music.java    
     * @Description: 音乐素材
     * @author lason       
     * @created 2016年5月25日 上午9:59:37
 */
public class Music {
	
	private String Title;//音乐标题
	private String Description;//音乐描述
	private String MusicUrl;//音乐链接
	private String HQMusicUrl;//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String ThumbMediaId;//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	
	//空参
	public Music() {
		super();
	}
	//全参
	public Music(String title, String description, String musicUrl,
			String hQMusicUrl, String thumbMediaId) {
		super();
		Title = title;
		Description = description;
		MusicUrl = musicUrl;
		HQMusicUrl = hQMusicUrl;
		ThumbMediaId = thumbMediaId;
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
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	//toString
	@Override
	public String toString() {
		return "WechatMusic [Title=" + Title + ", Description=" + Description
				+ ", MusicUrl=" + MusicUrl + ", HQMusicUrl=" + HQMusicUrl
				+ ", ThumbMediaId=" + ThumbMediaId + "]";
	}
}
