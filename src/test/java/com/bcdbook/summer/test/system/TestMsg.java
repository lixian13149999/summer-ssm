package com.bcdbook.summer.test.system;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.system.pojo.Message;
import com.bcdbook.summer.system.service.MessageService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMsg {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private MessageService msgService = null;
	
//	private String title;//消息title
//	private String content;//消息内容
//	private String msgFrom;//消息发送者
//	private String msgTo;//消息接收者
//	private int readType;//消息阅读状态
	@Test
	public void add(){
		Message message = new Message("消息标题", "消息内容", "from_user_id", "to_user_id", 2);
		msgService.add(message);
	}
}
