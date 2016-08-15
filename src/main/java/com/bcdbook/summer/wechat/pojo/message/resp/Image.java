package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
     * @Title: Image.java    
     * @Description: 图片消息基础的对象
     * @author lason       
     * @created 2016年5月25日 下午12:10:16
 */
public class Image {
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id
	 */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
