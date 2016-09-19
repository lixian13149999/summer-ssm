package com.bcdbook.summer.wechat.pojo.event;

/**
 * 
     * @Title: LocationEvent.java    
     * @Description: 位置事件的类
     * @author lason       
     * @created 2016年5月27日 上午10:05:40
 */
public class LocationEvent extends Event {
	private String Latitude;//地理位置纬度
	private String Longitude;//地理位置经度
	private String Precision;//地理位置精度
	
	//空参构造
	public LocationEvent() {
		super();
	}
	//全参构造
	public LocationEvent(String latitude, String longitude, String precision) {
		super();
		Latitude = latitude;
		Longitude = longitude;
		Precision = precision;
	}

	//getter and setter
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
	
	//toString
	@Override
	public String toString() {
		return "LocationEvent [Latitude=" + Latitude + ", Longitude="
				+ Longitude + ", Precision=" + Precision + "]";
	}
}
