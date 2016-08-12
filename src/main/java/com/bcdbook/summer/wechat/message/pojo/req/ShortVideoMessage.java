package com.bcdbook.summer.wechat.message.pojo.req;

/**
 * 
     * @Title: ShortVideo.java    
     * @Description: 小视频消息
     * @author lason       
     * @created 2016年5月25日 上午11:25:53
 */
public class ShortVideoMessage extends BaseMessage {
	/**
	 * 小视频id,可以调用多媒体文件下载接口拉取数据。
	 */
	private String MediaId;
	/**
	 * 小视频缩略图id,可以调用多媒体文件下载接口拉取数据。
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
