package com.bcdbook.summer.wechat.pojo.message;

/**
 * @Description: 微信,消息的基类
 * @author lason
 * @date 2016年9月19日
 */
public class WechatMessage {
	
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
	
	
	private String ToUserName;//发送方帐号（一个OpenID）
	private String FromUserName;//开发者微信号
	private long CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	
	//空参构造
	public WechatMessage() {
		super();
	}
	//全参构造
	public WechatMessage(String toUserName, String fromUserName,
			long createTime, String msgType) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}
	//getter and setter
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	//toString
	@Override
	public String toString() {
		return "WechatMessage [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + "]";
	}
	
}
