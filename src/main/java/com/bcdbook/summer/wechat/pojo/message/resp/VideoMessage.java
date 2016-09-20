package com.bcdbook.summer.wechat.pojo.message.resp;

import com.bcdbook.summer.wechatold.pojo.message.resp.Video;

/**
 * @Description: 用于接收视频消息
 * @author lason
 * @date 2016年9月19日
 */
public class VideoMessage extends WechatRespMessage{
	private Video video;//视频对象
	
	//空参构造
	public VideoMessage() {
		super();
	}
	//全参构造
	public VideoMessage(Video video) {
		super();
		this.video = video;
	}

	//getter and setter
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}

	//toString
	@Override
	public String toString() {
		return "WechatVideoMessage [video=" + video + "]";
	}
	
	
}
