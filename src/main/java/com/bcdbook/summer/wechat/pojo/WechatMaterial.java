package com.bcdbook.summer.wechat.pojo;

import java.util.List;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

/**
     * @Title: WechatMaterial.java    
     * @Description: 素材本地保存基础类
     * 此素材类包含了微信中可维护的所有素材
     * 并且把文图消息中的单个文章也维护在一起了
     * 所以产生了自关联
     * 使用的时候有可能产生混淆,但为了便于开发和维护,故选择了此种做法
     * 若后期业务拓展较快,此维护方式会发生改变
     * @author lason       
     * @created 2016年9月20日 下午9:35:04
 */
public class WechatMaterial extends DataEntity<WechatMaterial> {

	private static final long serialVersionUID = 472956021907896157L;
	
	//素材类型
	public static final String IMAGE = "image";//图片
	public static final String VIDEO = "video";//视频
	public static final String VOICE = "voice";//语音
	public static final String NEWS = "news";//文图消息
	public static final String MUSIC = "music";//音乐
	
	/**
	 * 用户识别及维护使用的字段
	 */
	private String msgType;//素材类型
	private String keyword;//客户获取素材的关键字(用来匹配用户发送过来的消息)
	
	/**
	 * 公用的素材字段
	 */
	private String mediaId;// 素材id(用于处理非文字消息)
	//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	//同时也用于文本消息的内容
	private String content;
	//文件名称
	//同时也用于媒体消息的名称
	private String name;
	//图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
	//音乐链接,普通音乐(和url通用)
	//同时其他素材的url也通用
	private String url;
	private String description;//文件描述
	
	/**
	 * 文图消息素材特有的字段
	 */
	private String parentMediaId;//单个文章所属的文图文图消息的id
	private String title;//图文消息的标题
	private String thumbMediaId;//图文消息的封面图片素材id（必须是永久mediaID）
	private int showCoverPic;//是否在文章内部显示封面，0为false，即不显示，1为true，即显示
	private String author;//作者
	private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	private String contentSourceUrl;//图文消息的原文地址，即点击“阅读原文”后的URL
	private int sort;//文图消息的顺序,以便记录多文图消息的先后值
	
	/**
	 * 音乐素材特有的字段
	 */
//	private String musicUrl;//音乐链接,普通音乐和url通用
	private String hqMusicUrl;//高质量音乐链接，WIFI环境优先使用该链接播放音乐

	private List<WechatMaterial> wechatMaterials;//文图消息时,但个文章的集合
	
	//空参构造
	public WechatMaterial() {
		super();
	}
	//全参构造
	public WechatMaterial(String msgType, String keyword, String mediaId,
			String content, String name, String url, String description,
			String parentMediaId, String title, String thumbMediaId,
			int showCoverPic, String author, String digest,
			String contentSourceUrl, int sort, String hqMusicUrl) {
		super();
		this.msgType = msgType;
		this.keyword = keyword;
		this.mediaId = mediaId;
		this.content = content;
		this.name = name;
		this.url = url;
		this.description = description;
		this.parentMediaId = parentMediaId;
		this.title = title;
		this.thumbMediaId = thumbMediaId;
		this.showCoverPic = showCoverPic;
		this.author = author;
		this.digest = digest;
		this.contentSourceUrl = contentSourceUrl;
		this.sort = sort;
		this.hqMusicUrl = hqMusicUrl;
	}
	/**
	 * @Description: 便于封装素材初始值的构造方法
	 * @param @param msgType
	 * @param @param keyword
	 * @param @param mediaId   
	 * @return WechatMaterial  
	 * @throws
	 * @author yokoboy
	 * @date 2016年9月21日
	 */
	public WechatMaterial(String msgType, String keyword, String mediaId) {
		super();
		this.msgType = msgType;
		this.keyword = keyword;
		this.mediaId = mediaId;
	}
	//getter and setter
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParentMediaId() {
		return parentMediaId;
	}
	public void setParentMediaId(String parentMediaId) {
		this.parentMediaId = parentMediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public int getShowCoverPic() {
		return showCoverPic;
	}
	public void setShowCoverPic(int showCoverPic) {
		this.showCoverPic = showCoverPic;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getContentSourceUrl() {
		return contentSourceUrl;
	}
	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}
	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}
	public List<WechatMaterial> getWechatMaterials() {
		return wechatMaterials;
	}
	public void setWechatMaterials(List<WechatMaterial> wechatMaterials) {
		this.wechatMaterials = wechatMaterials;
	}
	
	//toString
	@Override
	public String toString() {
		return "WechatMaterial [msgType=" + msgType + ", keyword=" + keyword
				+ ", mediaId=" + mediaId + ", content=" + content + ", name="
				+ name + ", parentMediaId=" + parentMediaId + ", title="
				+ title + ", thumbMediaId=" + thumbMediaId + ", showCoverPic="
				+ showCoverPic + ", author=" + author + ", digest=" + digest
				+ ", url=" + url + ", contentSourceUrl=" + contentSourceUrl
				+ ", description=" + description + ", sort=" + sort
				+ ", hqMusicUrl=" + hqMusicUrl + "]";
	}
}