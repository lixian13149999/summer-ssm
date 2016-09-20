package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * @Description: 用于回复的图片消息
 * @author lason
 * @date 2016年9月19日
 */
public class ImageMessage extends WechatRespMessage {
	private Image Image;//Image

	//空参构造
	public ImageMessage() {
		super();
	}
	//全参构造
	public ImageMessage(Image image) {
		super();
		Image = image;
	}
	
	//getter and setter
	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		Image = image;
	}

	//toString
	@Override
	public String toString() {
		return "WechatImageMessage [Image=" + Image + "]";
	}
	
}
