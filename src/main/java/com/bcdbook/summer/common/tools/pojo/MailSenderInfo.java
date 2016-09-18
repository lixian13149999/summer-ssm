package com.bcdbook.summer.common.tools.pojo;

import java.util.List;
import java.util.Properties;

public class MailSenderInfo {
	private String host;//服务器地址
	private String sender;//发件人的邮箱
	private String nickName;//发件人的昵称
	private String username;//发件的服务器账号(用于发送的邮箱)
	private String password;//发件的服务器的密码(用于验证)
	
	private List<String> toAddress;//收件人的邮箱
	private List<String> toCarbonCopyAddress;//抄送者邮箱
	private List<String> toBlindCarbonCopyAddress;//密送者邮箱
	
	private Mail mail;
	
//	// 表示SMTP发送邮件，需要进行身份验证
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.host", mail.getHost());
//    // 发件人的账号
//    props.put("mail.user", mail.getUsername());
//    // 访问SMTP服务时需要提供的密码
//    props.put("mail.password", mail.getPassword());
	/** 
     * 获得邮件会话属性 
     */  
    public Properties getProperties() {  
        Properties pro = new Properties();
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.host", this.getHost());  
        pro.put("mail.smtp.port", this.getUsername());  
        pro.put("mail.smtp.port", this.getPassword());  
        return pro;
    }

    //空参构造
	public MailSenderInfo() {
		super();
	}

	//全参构造
	public MailSenderInfo(String host, String sender, String nickName,
			String username, String password, List<String> toAddress,
			List<String> toCarbonCopyAddress,
			List<String> toBlindCarbonCopyAddress, Mail mail) {
		super();
		this.host = host;
		this.sender = sender;
		this.nickName = nickName;
		this.username = username;
		this.password = password;
		this.toAddress = toAddress;
		this.toCarbonCopyAddress = toCarbonCopyAddress;
		this.toBlindCarbonCopyAddress = toBlindCarbonCopyAddress;
		this.mail = mail;
	}

	//getter and setter
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

	public List<String> getToAddress() {
		return toAddress;
	}

	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}

	public List<String> getToCarbonCopyAddress() {
		return toCarbonCopyAddress;
	}

	public void setToCarbonCopyAddress(List<String> toCarbonCopyAddress) {
		this.toCarbonCopyAddress = toCarbonCopyAddress;
	}

	public List<String> getToBlindCarbonCopyAddress() {
		return toBlindCarbonCopyAddress;
	}

	public void setToBlindCarbonCopyAddress(List<String> toBlindCarbonCopyAddress) {
		this.toBlindCarbonCopyAddress = toBlindCarbonCopyAddress;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}
    
	
}
