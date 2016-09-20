package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
     * @Title: MusicMessage.java    
     * @Description: 用于回复的音乐消息
     * @author lason       
     * @created 2016年5月25日 上午9:59:01
 */
public class MusicMessage extends WechatRespMessage {
	
	private Music Music;//音乐对象

	//空参
	public MusicMessage() {
		super();
	}
	//全参
	public MusicMessage(Music music) {
		super();
		Music = music;
	}

	//getter and setter
	public Music getMusic() {
		return Music;
	}
	public void setMusic(Music music) {
		Music = music;
	}
	
	//toString
	@Override
	public String toString() {
		return "WechatMusicMessage [Music=" + Music + "]";
	}
}
