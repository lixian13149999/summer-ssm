package com.bcdbook.summer.wechat.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class WechatMaterial extends DataEntity<WechatMaterial> {

	private static final long serialVersionUID = 472956021907896157L;
	
//	public static final String IMAGE = "image";
//	public static final String VIDEO = "video";
//	public static final String VOICE = "voice";
//	public static final String NEWS = "news";
	
	private String keyWord;//客户获取素材的关键字(用来匹配用户发送过来的消息)
	
	private String title;//图文消息的标题
	private String thumbMediaId;//图文消息的封面图片素材id（必须是永久mediaID）
	private String showCoverPic;//是否显示封面，0为false，即不显示，1为true，即显示
	private String author;//作者
	private String digest;//作者
	private String content;//作者
	private String url;//作者
	private String contentSourceUrl;//作者
	
//	private String name;// 消息的命名
//	private String keyword;// 消息关键字(用来匹配用户发送过来的消息)
//	private String msgType;// 消息类型
//	private String mediaId;// 素材id(用于处理非文字消息)
//	private String content;// 文本消息的内容
//	
//	private String title;// 消息的title(music,video类型的消息会用到的字段)
//	private String description;// 消息的备注(music,video类型的消息会用到的字段)
//	private String musicUrl;// 音乐的连接地址(music类型的消息会用到的字段)
//	private String hqMusicUrl;// 高清音乐的连接地址(music类型的消息会用到的字段)
	 
	
	/*
	 * total_count	该类型的素材的总数
		item_count	本次调用获取的素材的数量
		title	图文消息的标题
		thumb_media_id	图文消息的封面图片素材id（必须是永久mediaID）
		show_cover_pic	是否显示封面，0为false，即不显示，1为true，即显示
		author	作者
		digest	图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
		content	图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
		url	图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
		content_source_url	图文消息的原文地址，即点击“阅读原文”后的URL
		update_time		这篇图文消息素材的最后更新时间
		name	文件名称
		description		文件描述
		
		msgtype
		keyword
		
		MusicUrl		音乐链接
		HQMusicUrl		高质量音乐链接，WIFI环境优先使用该链接播放音乐
		ThumbMediaId 	缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
		
		PicUrl
	 */
}