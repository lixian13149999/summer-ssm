package com.bcdbook.summer.common.tools.pojo;

import java.util.List;

public class MailSend {
	private String host;//服务器地址
	private String sender;//发件人的邮箱
	private String nickName;//发件人的昵称
	private String username;//发件的服务器账号(用于发送的邮箱)
	private String password;//发件的服务器的密码(用于验证)
	
	private List<String> toAddress;//收件人的邮箱
	private List<String> toCarbonCopyAddress;//抄送者邮箱
	private List<String> toBlindCarbonCopyAddress;//密送者邮箱
	
	private Mail mail;
}
