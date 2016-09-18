package com.bcdbook.summer.wechatold.pojo.message.req;

/**
 * 
 * @Title: VoiceMessage.java
 * @Description: 语音消息
 * @author lason
 * @created 2016年5月25日 上午9:08:19
 */
public class VoiceMessage extends BaseMessage {
	/**
	 * 媒体ID
	 */
	private String MediaId;
	/**
	 * 语音格式
	 */
	private String Format;

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
}
