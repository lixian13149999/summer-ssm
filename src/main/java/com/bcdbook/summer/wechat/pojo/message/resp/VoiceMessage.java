package com.bcdbook.summer.wechat.pojo.message.resp;
/**
 * 
     * @Title: VoiceMessage.java    
     * @Description: 回复的语音消息
     * @author lason       
     * @created 2016年5月25日 下午12:16:52
 */
public class VoiceMessage extends BaseMessage {
	/**
	 * 语音消息对象
	 */
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}
	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
}
