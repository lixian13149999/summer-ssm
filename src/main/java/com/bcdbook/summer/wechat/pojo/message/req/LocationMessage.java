package com.bcdbook.summer.wechat.pojo.message.req;

/**
 * @Description: 用于接频消息
 * @author lason
 * @date 2016年9月19日
 */
public class LocationMessage extends WechatReqMessage{
	private String Location_X;//地理位置维度
	private String Location_Y;//地理位置经度
	private String Scale;//地图缩放大小
	private String Label;//地理位置信息
	//空参构造
	public LocationMessage() {
		super();
	}
	//全参构造
	public LocationMessage(String location_X, String location_Y,
			String scale, String label) {
		super();
		Location_X = location_X;
		Location_Y = location_Y;
		Scale = scale;
		Label = label;
	}
	//getter and setter
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	//toString
	@Override
	public String toString() {
		return "WechatLocationMessage ["
				+ (Location_X != null ? "Location_X=" + Location_X + ", " : "")
				+ (Location_Y != null ? "Location_Y=" + Location_Y + ", " : "")
				+ (Scale != null ? "Scale=" + Scale + ", " : "")
				+ (Label != null ? "Label=" + Label : "") + "]";
	}
}
