package com.bcdbook.summer.wechat.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

/**
     * @Title: Wechat.java    
     * @Description: 微信需要维护的字段的主体类
     * @author lason       
     * @created 2016年9月18日 下午7:09:52
 */
public class Wechat extends DataEntity<Wechat> {
	private static final long serialVersionUID = 291446736647879322L;

	public static final String APP_ID = "wx18d7b3cb67983626";
	public static final String APP_SECRET = "bd8194a6707b334583b363a8318b59f0";
	
	public static final String URL = "http://www.bcdbook.com/wechat";
	public static final String TOKEN = "solar";
	public static final String ENCODING_AES_KEY = "1BIqibAgB4GnC1izNv0wEab0YrErwiv3uaaRCmElBBV";
	
	public static final String PUBLIC_PARTNER = "1332229801";
	public static final String PUBLIC_PARTNER_KEY = "Youchoumiyao123456789youchoumiya";
	public static final String PAY_SUCCESS_BACK = "http://you.bcdbook.com/proj/payBack";
	public static final String PRE_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	public static final String KEY_ACCESS_TOKEN = "accessToken";
	
	
	private String wechatKey;//字段的key值,用来存储字段的唯一识别码
	private String wechatValue;//字段对应的值,用来存储所要使用的值
	private String refreshValue;//备用字段,以便于扩展
	
	//空参构造
	public Wechat() {
		super();
	}
	//全参构造
	public Wechat(String wechatKey, String wechatValue, String refreshValue) {
		super();
		this.wechatKey = wechatKey;
		this.wechatValue = wechatValue;
		this.refreshValue = refreshValue;
	}

	//getter and setter
	public String getWechatKey() {
		return wechatKey;
	}
	public void setWechatKey(String wechatKey) {
		this.wechatKey = wechatKey;
	}
	public String getWechatValue() {
		return wechatValue;
	}
	public void setWechatValue(String wechatValue) {
		this.wechatValue = wechatValue;
	}
	public String getRefreshValue() {
		return refreshValue;
	}
	public void setRefreshValue(String refreshValue) {
		this.refreshValue = refreshValue;
	}
	@Override
	public String toString() {
		return "Wechat [wechatKey=" + wechatKey + ", wechatValue="
				+ wechatValue + ", refreshValue=" + refreshValue + "]";
	}
}
