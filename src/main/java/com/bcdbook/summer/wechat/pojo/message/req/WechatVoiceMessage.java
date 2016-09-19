package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 用于接收的语音消息
 * @author lason
 * @date 2016年9月19日
 */
public class WechatVoiceMessage extends WechatMessage {
	private String MediaId;//语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format;//语音格式，如amr，speex等
	//空参构造
	public WechatVoiceMessage() {
		super();
	}
	//全参构造
	public WechatVoiceMessage(String mediaId, String format) {
		super();
		MediaId = mediaId;
		Format = format;
	}
	//getter and setter
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	//toString
	@Override
	public String toString() {
		return "WechatVoiceMessage ["
				+ (MediaId != null ? "MediaId=" + MediaId + ", " : "")
				+ (Format != null ? "Format=" + Format : "") + "]";
	}
}
