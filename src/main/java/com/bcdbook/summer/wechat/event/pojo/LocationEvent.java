package com.bcdbook.summer.wechat.event.pojo;

/**
 * 
     * @Title: LocationEvent.java    
     * @Description: 位置消息的类
     * @author lason       
     * @created 2016年5月27日 上午10:05:40
 */
public class LocationEvent extends BaseEvent {
	private String Latitude;//地理位置纬度
	private String Longitude;//地理位置经度
	private String Precision;//地理位置精度
	
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	
}
