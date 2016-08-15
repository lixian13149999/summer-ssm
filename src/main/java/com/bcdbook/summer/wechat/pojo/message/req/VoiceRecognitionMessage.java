package com.bcdbook.summer.wechat.pojo.message.req;

public class VoiceRecognitionMessage extends BaseMessage {
	/**
	 * 媒体ID
	 */
	private String MediaId;
	/**
	 * 语音格式
	 */
	private String Format;
	/**
	 * 微信识别后的结果
	 */
	private String Recognition;
	
	
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
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	
}
