package com.bcdbook.summer.system.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class Message extends DataEntity<Message> {

	private static final long serialVersionUID = 3193191701584403695L;

	private String title;//消息title
	private String content;//消息内容
	private String msgFrom;//消息发送者
	private String msgTo;//消息接收者
	private int readType;//消息阅读状态
	
	public Message() {
		super();
	}
	public Message(String title, String content, String msgFrom, String msgTo,
			int readType) {
		super();
		this.title = title;
		this.content = content;
		this.msgFrom = msgFrom;
		this.msgTo = msgTo;
		this.readType = readType;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}
	public String getMsgTo() {
		return msgTo;
	}
	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}
	public int getReadType() {
		return readType;
	}
	public void setReadType(int readType) {
		this.readType = readType;
	}
}
