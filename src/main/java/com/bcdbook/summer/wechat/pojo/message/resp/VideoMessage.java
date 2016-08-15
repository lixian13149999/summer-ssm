package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
 * @Title: VideoMessage.java
 * @Description: 回复的视频消息
 * @author lason
 * @created 2016年5月25日 下午12:21:01
 */
public class VideoMessage extends BaseMessage {
	/**
	 * 视频消息对象
	 */
	private Video Video;

	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}

}
