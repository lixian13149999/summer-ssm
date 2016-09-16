package com.bcdbook.summer.common.email.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bcdbook.summer.common.email.pojo.Mail2;



public class MailService2 {
	public String send(Mail2 mail) throws MessagingException, UnsupportedEncodingException{
		// 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mail.getHost());
        // 发件人的账号
        props.put("mail.user", mail.getUsername());
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", mail.getPassword());

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        
        // 设置发件人
        InternetAddress from = new InternetAddress(mail.getUsername(), mail.getNickName());
        message.setFrom(from);
        
        //设置sender
//        InternetAddress sender = new InternetAddress(mail.getSender());
//        message.setSender(sender);
        
        // 设置收件人
        InternetAddress to = new InternetAddress(mail.getReceiver());
        message.setRecipient(RecipientType.TO, to);

//        // 设置抄送
//        InternetAddress cc = new InternetAddress("xianforwork@163.com");
//        message.setRecipient(RecipientType.CC, cc);
//
//        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("952870487@qq.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject(mail.getSubject());

        //设置简介文字
        message.setText(mail.getText());
        
        // 设置邮件的内容体
        message.setContent(mail.getContent(), "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
		return "success";
	}
	
	public String send(String to,String subject,String text,String content){
		Mail2 mail = new Mail2();
		mail.setHost("smtp.163.com");
		mail.setSender("xianforwork@163.com");
		mail.setNickName("BcdBook");
		mail.setUsername("bcdbook@163.com");
		mail.setPassword("li95287048");
		
		mail.setReceiver(to);
		mail.setSubject(subject);
		mail.setText(text);
		mail.setContent(content);
		
		try {
			send(mail);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		MailService2 ms = new MailService2();
		ms.send("xianforwork@163.com", "Bcdbook验证", "text信息", "邮件的主要内容,写点什么好呢?");
	}
}
