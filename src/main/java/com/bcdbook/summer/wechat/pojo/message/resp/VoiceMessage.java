package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 用于回复的语音消息
 * @author lason
 * @date 2016年9月19日
 */
public class VoiceMessage extends Message {
	private Voice wechatVoice;//语音素材
	
	//空参构造
	public VoiceMessage() {
		super();
	}
	//全参构造
	public VoiceMessage(Voice wechatVoice) {
		super();
		this.wechatVoice = wechatVoice;
	}

	//getter and setter
	public Voice getWechatVoice() {
		return wechatVoice;
	}
	public void setWechatVoice(Voice wechatVoice) {
		this.wechatVoice = wechatVoice;
	}

	//toString
	@Override
	public String toString() {
		return "WechatVoiceMessage [wechatVoice=" + wechatVoice + "]";
	}
}
