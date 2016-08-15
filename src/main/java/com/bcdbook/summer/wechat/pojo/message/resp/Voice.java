package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
     * @Title: Voice.java    
     * @Description: 回复的语音消息的基类
     * @author lason       
     * @created 2016年5月25日 下午12:14:53
 */
public class Voice {
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
