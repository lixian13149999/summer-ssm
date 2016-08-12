package com.bcdbook.summer.common.email.pojo;

public class Mail {
	public static final String ENCODEING = "UTF-8";
	
	public String host;//服务器地址
	public String sender;//发件人的邮箱
	public String receiver;//收件人的邮箱
	public String nickName;//发件人的昵称
	public String username;//发件的服务器账号(用于发送的邮箱)
	public String password;//发件的服务器的密码(用于验证)
	public String subject;//主题
	public String text;//简介信息
	public String content;//邮件主体内容
	
	public Mail() {
		super();
	}
	public Mail(String host, String sender, String receiver, String nickName,
			String username, String password, String subject, String text,
			String content) {
		super();
		this.host = host;
		this.sender = sender;
		this.receiver = receiver;
		this.nickName = nickName;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.text = text;
		this.content = content;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static String getEncodeing() {
		return ENCODEING;
	}
}
