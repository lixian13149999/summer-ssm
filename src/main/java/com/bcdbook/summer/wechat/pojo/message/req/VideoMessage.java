package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 用于接收视频消息
 * @author lason
 * @date 2016年9月19日
 */
public class VideoMessage extends Message{
	private String MediaId;//视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	//空参构造
	public VideoMessage() {
		super();
	}
	//全参构造
	public VideoMessage(String mediaId, String thumbMediaId) {
		super();
		MediaId = mediaId;
		ThumbMediaId = thumbMediaId;
	}
	//getter and setter
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	//toString
	@Override
	public String toString() {
		return "WechatVideoMessage ["
				+ (MediaId != null ? "MediaId=" + MediaId + ", " : "")
				+ (ThumbMediaId != null ? "ThumbMediaId=" + ThumbMediaId : "")
				+ "]";
	}
}
