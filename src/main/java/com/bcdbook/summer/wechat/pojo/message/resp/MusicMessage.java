package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
     * @Title: MusicMessage.java    
     * @Description: 音乐消息
     * @author lason       
     * @created 2016年5月25日 上午9:59:01
 */
public class MusicMessage extends BaseMessage {
	/**
	 * 音乐
	 */
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
