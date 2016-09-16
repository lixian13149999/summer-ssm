package com.bcdbook.summer.common.tools.pojo;

import java.util.List;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class Mail extends DataEntity<Mail> {
	private static final long serialVersionUID = 1312476502075517610L;
	
	//收件人的相关信息
	private String subject;//[存入]--主题
	private String text;//[存入]--简介信息
	private String content;//[存入]--邮件主体内容
	
	//邮件模板及相关识别的设定
	private String modalUrl;//[存入]--邮件模型(页面)路径
	private String mailKey;//[存入]--邮件的获取标示,用于根据不同的需求获取对应邮件模板
	
	private List<File> fileList;//邮件中对应的附件列表
	
	//空参构造
	public Mail() {
		super();
	}
	//全参构造
	public Mail(String subject, String text, String content, String modalUrl,
			String mailKey, List<File> fileList) {
		super();
		this.subject = subject;
		this.text = text;
		this.content = content;
		this.modalUrl = modalUrl;
		this.mailKey = mailKey;
		this.fileList = fileList;
	}
	
	//getter and setter
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
	public String getModalUrl() {
		return modalUrl;
	}
	public void setModalUrl(String modalUrl) {
		this.modalUrl = modalUrl;
	}
	public String getMailKey() {
		return mailKey;
	}
	public void setMailKey(String mailKey) {
		this.mailKey = mailKey;
	}
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", text=" + text + ", content="
				+ content + ", modalUrl=" + modalUrl + ", mailKey=" + mailKey
				+ ", fileList=" + fileList + "]";
	}
	
}
