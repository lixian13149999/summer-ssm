package com.bcdbook.summer.common.tools.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.common.tools.pojo.File;
import com.bcdbook.summer.common.tools.pojo.Mail;
import com.bcdbook.summer.common.tools.pojo.MailSenderInfo;
import com.bcdbook.summer.common.util.StringUtils;

public class MailUtil {
	/**
	 * @Description: 传入经过封装的邮件发送的基础类,执行发送邮件的操作
	 * @param @param mailSenderInfo
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月18日
	 */
	private static boolean send(MailSenderInfo mailSenderInfo){
		//验证传入的参数是否为空,如果参数验证不正确,直接返回false
		if(mailSenderInfo==null
				||mailSenderInfo.getMail()==null
				||StringUtils.isNull(mailSenderInfo.getUsername())
				||StringUtils.isNull(mailSenderInfo.getPassword()))
			return false;
		
		//创建一个密码验证器
		IAuthenticator iAuthenticator = new IAuthenticator(mailSenderInfo.getUsername(), mailSenderInfo.getPassword());
		//获取一个邮件回话属性
		Properties props = mailSenderInfo.getProperties();
		//对两者进行验证,如果获取到的对象不符合需求,返回false
		if(iAuthenticator==null||props==null)
			return false;
		
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session  
		Session sendMailSession = Session.getDefaultInstance(props,iAuthenticator);
		
		try {  
            // 根据session创建一个邮件消息  
            Message sendMailMessage = new MimeMessage(sendMailSession);  
            // 创建邮件发送者地址  
            Address from = new InternetAddress(mailSenderInfo.getUsername(), mailSenderInfo.getNickName());
            // 设置邮件消息的发送者  
            sendMailMessage.setFrom(from);  
              
            // 创建邮件接收者地址  
            List<String> tos = mailSenderInfo.getToAddress();
            if(tos != null && tos.size() > 0){  
                InternetAddress[] to = new InternetAddress[tos.size()];  
                // 设置邮件消息的发送者  
                for (int i = 0; i < tos.size(); i++) {  
                    to[i] = new InternetAddress(tos.get(i));  
                }  
                sendMailMessage.setRecipients(Message.RecipientType.TO, to);  
            }  
              
            // 设置邮件抄送者地址  
            List<String> toCCs = mailSenderInfo.getToCarbonCopyAddress();  
            if(toCCs != null && toCCs.size() > 0){  
                InternetAddress[] toCC = new InternetAddress[toCCs.size()];  
                // 设置邮件消息的发送者  
                for (int i = 0; i < toCCs.size(); i++) {  
                    toCC[i] = new InternetAddress(toCCs.get(i));  
                }  
                sendMailMessage.addRecipients(Message.RecipientType.CC, toCC);  
            }  
              
            // 设置邮件密送者地址  
            List<String> toBCCs = mailSenderInfo.getToBlindCarbonCopyAddress();  
            if(toBCCs != null && toBCCs.size() > 0){  
                InternetAddress[] toBCC = new InternetAddress[toBCCs.size()];  
                for (int i = 0; i < toBCCs.size(); i++) {  
                    toBCC[i] = new InternetAddress(toBCCs.get(i));  
                }  
                sendMailMessage.addRecipients(Message.RecipientType.BCC, toBCC);  
            }  
              
            // 设置邮件主题  
            sendMailMessage.setSubject(MimeUtility.encodeText(mailSenderInfo.getMail().getSubject(),"utf-8","B"));  
            //设置邮件文本内容内容  
            sendMailMessage.setText(mailSenderInfo.getMail().getText());
            
            Multipart multipart = new MimeMultipart();  
            // 邮件文本内容  
            if(mailSenderInfo.getMail().getContent() != null && !"".equals(mailSenderInfo.getMail().getContent())){  
                BodyPart part = new MimeBodyPart();  
                part.setContent(mailSenderInfo.getMail().getContent(), "text/html;charset=utf-8");//设置邮件文本内容  
                multipart.addBodyPart(part);  
            }  
              
            // 附件  
            List<File> attachFiles = mailSenderInfo.getMail().getFileList();  
            int leng = attachFiles == null ? 0 : attachFiles.size();  
            for (int i = 0; i < leng; i++) {  
                BodyPart part = new MimeBodyPart();  
                // 根据文件名获取数据源  
                DataSource dataSource = new FileDataSource(attachFiles.get(i).getSrc());
                DataHandler dataHandler = new DataHandler(dataSource);  
                // 得到附件本身并至入BodyPart  
                part.setDataHandler(dataHandler);  
                // 得到文件名同样至入BodyPart  
                part.setFileName(MimeUtility.encodeText(attachFiles.get(i).getName()));  
                multipart.addBodyPart(part);
            }  
            //设置邮件主体内容
            sendMailMessage.setContent(multipart);  
            // 设置邮件发送的时间  
            sendMailMessage.setSentDate(new Date());  
            // 发送邮件  
            //Transport.send(sendMailMessage);  
            Transport transport = sendMailSession.getTransport("smtp");  
            transport.connect(mailSenderInfo.getUsername(), mailSenderInfo.getPassword());
            transport.send(sendMailMessage,sendMailMessage.getAllRecipients());
            transport.close();
            return true;
              
        } catch (AddressException e) {  
            e.printStackTrace();  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
		return false;
	}
	
	/**
	 * @Description: 传入邮件的接收者,抄送者,密送者和邮件对象,执行发送邮件的操作
	 * @param @param toAddress
	 * @param @param toCarbonCopyAddress
	 * @param @param toBlindCarbonCopyAddress
	 * @param @param mail
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月18日
	 */
	public static boolean send(List<String> toAddress,List<String> toCarbonCopyAddress,List <String> toBlindCarbonCopyAddress,Mail mail){
		if(mail==null||toAddress==null||toAddress.size()<=0)
			return false;

		MailSenderInfo senderInfo = getServerMailSenderInfo();
		if(senderInfo==null)
			return false;
		
		senderInfo.setToAddress(toAddress);//设置接收者
		senderInfo.setToCarbonCopyAddress(toCarbonCopyAddress);//设置抄送者
		senderInfo.setToBlindCarbonCopyAddress(toBlindCarbonCopyAddress);//设置密送者
		
		senderInfo.setMail(mail);//设置要发送的邮件对象
		
		return send(senderInfo);
	}
	
	/**
	 * @Description: 传入邮件的接收者,邮件对象,执行发送邮件的操作
	 * @param @param toAddress
	 * @param @param mail
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月18日
	 */
	public static boolean send(String toAddress,Mail mail){
		if(mail==null||StringUtils.isNull(toAddress))
			return false;
		
		List<String> toAddressList = new ArrayList<String>();
		toAddressList.add(toAddress);
		
		return send(toAddressList, null, null, mail);
	}
	
	/**
	 * @Description: 根据传入的接收者,主题,文本内容,文件内容执行邮件发送操作
	 * @param @param to
	 * @param @param subject
	 * @param @param text
	 * @param @param content
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月18日
	 */
	public static boolean send(String to,String subject,String text,String content){
		if(StringUtils.isNull(to)
				||StringUtils.isNull(subject))
			return false;
		
		//创建邮件对象
		Mail mail = new Mail();
		//设置邮件基础对象的相关参数
		mail.setSubject(subject);//设置主题
		mail.setText(text);//设置文本内容
		mail.setContent(content);//设置邮件内容
		
		return send(to, mail);
	}
	
	private static MailSenderInfo getServerMailSenderInfo(){
		//通过系统配置类,获取系统配置文件中,邮件的相关配置信息
		String host = Global.getConfig("mail.host");
		String sender = Global.getConfig("mail.sender");
		String userName = Global.getConfig("mail.userName");
		String password = Global.getConfig("mail.password");
		String nickName = Global.getConfig("mail.nickName");
		
		//验证获取的参数是否合法,如果不合法,直接返回false
		if(StringUtils.isNull(host)
				||StringUtils.isNull(sender)
				||StringUtils.isNull(userName)
				||StringUtils.isNull(password))
			return null;
		
		//创建邮件的发送对象
		MailSenderInfo senderInfo = new MailSenderInfo();
		//设置相关参数中的参数
		senderInfo.setHost(host);//设置host
		senderInfo.setSender(sender);//设置发送者
		senderInfo.setUsername(userName);//设置用户名
		senderInfo.setPassword(password);//设置密码
		senderInfo.setNickName(nickName);//设置昵称
		
		return senderInfo;
	}
	
	public static void main(String[] args) {
//		send("lixian13149999@163.com", "测试新的邮件工具类", "文本内容", "主体内容");
		Mail mail = new Mail();
		mail.setSubject("邮件主题");
		mail.setText("邮件文本");
		mail.setContent("邮件内容");
		
		List<File> fileList = new ArrayList<File>();
		File file = new File();
		file.setName("用于测试的附件名称.jpg");
		file.setSrc("/Users/lason/Pictures/100403K54-10.jpg");
		
		fileList.add(file);
		
		mail.setFileList(fileList);
		//send("lixian13149999@163.com", mail);
		
		List<String> toAddress = new ArrayList<String>();
		List<String> toCarbonCopyAddress = new ArrayList<String>();
		List <String> toBlindCarbonCopyAddress = new ArrayList<String>();
		
		toAddress.add("lixian13149999@163.com");
		toCarbonCopyAddress.add("xianforwork@163.com");
		toBlindCarbonCopyAddress.add("952870487@qq.com");
		
		send(toAddress, toCarbonCopyAddress, toBlindCarbonCopyAddress, mail);
		
//		MailSenderInfo msi = new MailSenderInfo();
//		msi.setToAddress(toAddress);
//		msi.setToCarbonCopyAddress(toCarbonCopyAddress);
//		msi.setToBlindCarbonCopyAddress(toBlindCarbonCopyAddress);
//		msi.setMail(mail);
//		
//		System.out.println(send(msi));
		
	}
}
