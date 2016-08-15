package com.bcdbook.summer.wechat.pojo.message.resp;

/**
 * 
     * @Title: ImageMessage.java    
     * @Description: 回复图片消息
     * @author lason       
     * @created 2016年5月25日 下午12:12:52
 */
public class ImageMessage extends BaseMessage {
	/**
	 * 回复的图片对象
	 */
	private Image Image;

	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		Image = image;
	}
	
	
}
