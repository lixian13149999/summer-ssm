package com.bcdbook.summer.wechatold.pojo.message.resp;
/**
 * 
     * @Title: Music.java    
     * @Description: 音乐消息
     * @author lason       
     * @created 2016年5月25日 上午9:59:37
 */
public class Music {
	/**
	 * 音乐名称
	 */
	private String Title;
	
	/**
	 * 音乐描述
	 */
	private String Description;
	
	/**
	 * 音乐链接
	 */
	private String MusicUrl;
	
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	private String HQMusicUrl;

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

	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}
}
