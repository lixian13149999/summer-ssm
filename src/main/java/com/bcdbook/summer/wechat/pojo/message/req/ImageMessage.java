package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 用于接收的图片消息
 * @author lason
 * @date 2016年9月19日
 */
public class ImageMessage extends Message {
	private String PicUrl;//图片链接（由系统生成）
	private String MediaId;//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	
	//空参构造
	public ImageMessage() {
		super();
	}
	//全参构造
	public ImageMessage(String picUrl, String mediaId) {
		super();
		PicUrl = picUrl;
		MediaId = mediaId;
	}
	//getter and setter
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	//toString
	@Override
	public String toString() {
		return "WechatImageMessage ["
				+ (PicUrl != null ? "PicUrl=" + PicUrl + ", " : "")
				+ (MediaId != null ? "MediaId=" + MediaId : "") + "]";
	}
	
}
