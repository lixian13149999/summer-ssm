package com.bcdbook.summer.wechat.pojo;

import com.bcdbook.summer.common.persistence.DateEntity;

public class Wechat extends DateEntity<Wechat> {
	
	private static final long serialVersionUID = -8519260952571337692L;
	
	private String accessToken;
	private String refreshToken;
	
	public Wechat() {
		super();
	}
	public Wechat(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
