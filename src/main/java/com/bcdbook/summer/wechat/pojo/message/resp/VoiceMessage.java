package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 用于回复的语音消息
 * @author lason
 * @date 2016年9月19日
 */
public class VoiceMessage extends WechatRespMessage {
	private Voice Voice;//语音素材
	
	//空参构造
	public VoiceMessage() {
		super();
	}
	//全参构造
	public VoiceMessage(Voice Voice) {
		super();
		this.Voice = Voice;
	}

	//getter and setter
	public Voice getVoice() {
		return Voice;
	}
	public void setVoice(Voice Voice) {
		this.Voice = Voice;
	}

	//toString
	@Override
	public String toString() {
		return "WechatVoiceMessage [Voice=" + Voice + "]";
	}
}
