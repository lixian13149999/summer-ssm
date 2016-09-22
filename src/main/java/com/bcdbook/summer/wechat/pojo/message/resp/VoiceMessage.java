package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 用于回复的语音消息
 * @author lason
 * @date 2016年9月19日
 */
public class VoiceMessage extends WechatRespMessage {
	private Voice voice;//语音素材
	
	//空参构造
	public VoiceMessage() {
		super();
	}
	//全参构造
	public VoiceMessage(Voice voice) {
		super();
		this.voice = voice;
	}

	//getter and setter
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	//toString
	@Override
	public String toString() {
		return "WechatVoiceMessage [voice=" + voice + "]";
	}
}
