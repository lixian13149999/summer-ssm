package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 用于接收的,识别后的,语音消息
 * @author lason
 * @date 2016年9月19日
 */
public class WechatVoiceRecognitionMessage extends WechatVoiceMessage{
	private String Recognition;//语音识别结果，UTF8编码
	//空参
	public WechatVoiceRecognitionMessage() {
		super();
	}
	//全参
	public WechatVoiceRecognitionMessage(String recognition) {
		super();
		Recognition = recognition;
	}

	//getter and setter
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	
	//toString
	@Override
	public String toString() {
		return "WechatVoiceRecognitionMessage ["
				+ (Recognition != null ? "Recognition=" + Recognition : "")
				+ "]";
	}
	
}
