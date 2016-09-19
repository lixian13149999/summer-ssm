package com.bcdbook.summer.wechat.pojo.message.resp;

/**
     * @Title: WechatVoice.java    
     * @Description: 微信语音素材
     * @author lason       
     * @created 2016年9月19日 下午7:14:32
 */
public class Voice {
	private String MediaId;//通过素材管理中的接口上传多媒体文件，得到的id。

	//空参构造
	public Voice() {
		super();
	}
	//全参构造
	public Voice(String mediaId) {
		super();
		MediaId = mediaId;
	}
	
	//getter and setter
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	//toString
	@Override
	public String toString() {
		return "WechatImage [MediaId=" + MediaId + "]";
	}
}
