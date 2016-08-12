package com.bcdbook.summer.wechat.message.pojo.req;

/**
 * 
 * @Title: ImageMessage.java
 * @Description: 图片消息类
 * @author lason
 * @created 2016年5月25日 上午9:05:49
 */
public class ImageMessage extends BaseMessage {
	/**
	 * 图片的连接地址(由系统生成)
	 */
	private String PicUrl;
	
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String MediaId;

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

	
}
