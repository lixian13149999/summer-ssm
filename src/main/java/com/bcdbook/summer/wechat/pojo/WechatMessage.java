package com.bcdbook.summer.wechat.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class WechatMessage extends DataEntity<WechatMessage> {

	private static final long serialVersionUID = 472956021907896157L;
	
	/**
	 * 文本消息
	 */
	public static final String TEXT = "text";
	/**
	 * 图片消息
	 */
	public static final String IMAGE = "image";
	/**
	 * 语音消息
	 */
	public static final String VOICE = "voice";
	/**
	 * 视频消息
	 */
	public static final String VIDEO = "video";
	/**
	 * 短视频消息
	 */
	public static final String SHORT_VIDEO = "shortvideo";
	/**
	 * 地里位置消息
	 */
	public static final String LOCATION = "location";
	/**
	 * 连接消息
	 */
	public static final String LINK = "link";
	
	/**
	 * 音乐消息
	 */
	public static final String MUSIC = "music";
	/**
	 * 文图消息
	 */
	public static final String NEWS = "news";
	
	/**
	 * 事件消息
	 */
	public static final String EVENT = "event";
	
	private String name;// 消息的命名
	private String keyword;// 消息关键字(用来匹配用户发送过来的消息)
	private String msgType;// 消息类型
	private String mediaId;// 素材id(用于处理非文字消息)
	private String content;// 文本消息的内容
	
	private String title;// 消息的title(music,video类型的消息会用到的字段)
	private String description;// 消息的备注(music,video类型的消息会用到的字段)
	private String musicUrl;// 音乐的连接地址(music类型的消息会用到的字段)
	private String hqMusicUrl;// 高清音乐的连接地址(music类型的消息会用到的字段)
	
	public WechatMessage() {
		super();
	}

	public WechatMessage(String name, String keyword, String msgType, String mediaId,
			String content, String title, String description, String musicUrl,
			String hqMusicUrl) {
		super();
		this.name = name;
		this.keyword = keyword;
		this.msgType = msgType;
		this.mediaId = mediaId;
		this.content = content;
		this.title = title;
		this.description = description;
		this.musicUrl = musicUrl;
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}
  
}