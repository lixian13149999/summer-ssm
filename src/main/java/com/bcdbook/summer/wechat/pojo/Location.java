package com.bcdbook.summer.wechat.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

/**
     * @Title: Location.java    
     * @Description: 微信用来处理关注者地址的类
     * @author lason       
     * @created 2016年9月20日 下午10:12:21
 */
public class Location extends DataEntity<Location>{

	private static final long serialVersionUID = -8295706207305160545L;

	private String openId;//发送者的openid
	private double latitude;//纬度
	private double longitude;//经度
	private double locPrecision;//地理位置的精度
	private String label;//地理位置信息
	
	//空参构造
	public Location() {
		super();
	}
	//全参构造
	public Location(String openId, double latitude, double longitude,
			double precision, String label) {
		super();
		this.openId = openId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locPrecision = precision;
		this.label = label;
	}
	
	//getter and setter
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLocPrecision() {
		return locPrecision;
	}
	public void setLocPrecision(double precision) {
		this.locPrecision = precision;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	//toString
	@Override
	public String toString() {
		return "Location [openId=" + openId + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", precision=" + locPrecision
				+ ", label=" + label + "]";
	}
}
