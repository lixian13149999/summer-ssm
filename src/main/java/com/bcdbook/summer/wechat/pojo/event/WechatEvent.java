package com.bcdbook.summer.wechat.pojo.event;

/**
 * 
     * @Title: BaseEvent.java    
     * @Description: 事件消息的基类
     * @author lason       
     * @created 2016年5月27日 上午10:04:36
 */
public class WechatEvent {
	/**
	 * 关注事件
	 */
	public static final String SUBSCRIBE = "subscribe";
	/**
	 * 取消关注事件
	 */
	public static final String UNSUBSCRIBE = "unsubscribe";
	/**
	 * 扫描事件
	 */
	public static final String SCAN = "SCAN";
	/**
	 * 地里位置事件,自动发送
	 */
	public static final String LOCATION = "LOCATION";
	/**
	 * 点击事件
	 */
	public static final String CLICK = "CLICK";
	/**
	 * 连接事件
	 */
	public static final String VIEW = "VIEW";
	
	
	/**
	 * 扫码,自动进入连接事件
	 */
	public static final String SCANCODE_PUSH = "scancode_push";
	/**
	 * 扫码,带提示事件
	 */
	public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
	
	/**
	 * 发送系统拍照事件
	 */
	public static final String PIC_SYSPHOTO = "pic_sysphoto";
	/**
	 * 发送系统拍照或相册事件
	 */
	public static final String PIC_SYSPHOTO_OR_ALBUM = "pic_photo_or_album";
	/**
	 * 发送微信照片事件
	 */
	public static final String PIC_WEIXIN = "pic_weixin";
	
	/**
	 * 发送地址事件,主动发送(可调整位置)
	 */
	public static final String LOCATION_SELECT = "location_select";
	
	/**
	 * 下发消息事件
	 */
	public static final String MEDIA_ID = "media_id";
	/**
	 * 跳转素材中连接固定事件
	 */
	public static final String VIEW_LIMITED = "view_limited";
	
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private long CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型,event
	private String Event;//事件类型
	
	//空参构造
	public WechatEvent() {
		super();
	}
	//全参构造
	public WechatEvent(String toUserName, String fromUserName, long createTime,
			String msgType, String event) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Event = event;
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
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	
	//toString
	@Override
	public String toString() {
		return "Event [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Event=" + Event + "]";
	}
}
