package com.bcdbook.summer.wechat.pojo.message.resp;

import com.bcdbook.summer.wechat.pojo.message.resp.Video;

/**
 * @Description: 用于接收视频消息
 * @author lason
 * @date 2016年9月19日
 */
public class VideoMessage extends WechatRespMessage{
	private Video Video;//视频对象
	
	//空参构造
	public VideoMessage() {
		super();
	}
	//全参构造
	public VideoMessage(Video Video) {
		super();
		this.Video = Video;
	}

	//getter and setter
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video Video) {
		this.Video = Video;
	}

	//toString
	@Override
	public String toString() {
		return "WechatVideoMessage [Video=" + Video + "]";
	}
	
}
