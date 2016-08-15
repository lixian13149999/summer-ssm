package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * 
     * @Title: VideoMessage.java    
     * @Description: 视频消息
     * @author lason       
     * @created 2016年5月25日 上午11:23:54
 */
public class VideoMessage extends BaseMessage {
	/**
	 * 视频id,可以调用多媒体文件下载接口拉取数据。
	 */
	private String MediaId;
	/**
	 * 视频缩略图id,可以调用多媒体文件下载接口拉取数据。
	 */
	private String ThumbMediaId;
	
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
	
}
