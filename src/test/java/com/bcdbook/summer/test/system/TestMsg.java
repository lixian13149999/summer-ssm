package com.bcdbook.summer.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
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
		Message message = new Message("消息标题1", "消息内容1", "发送者1", "接收者1", 2);
		msgService.add(message);
	}
	
	@Test 
	public void remove(){
		msgService.delete("5e79e095dc704e619f15edfd038bc4ae");
	}
	
	@Test 
	public void removeByCondition(){
		Message message = new Message();
//		message.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		message.setDelFlag(1);
		message.setTitle("消息标题");
//		message.setContent("消息内容1");
//		message.setMsgFrom("发送者1");
//		message.setMsgTo("接收者1");
//		message.setReadType(1);
		
		System.out.println(msgService.deleteByCondition(message));
	}
		
	
	@Test
	public void update(){	
		Message message = new Message();
		message.setId("e9173abf41af487aa4ed04d512b7dcd1");
//		message.setDelFlag(1);
		message.setTitle("消息标题1改");
//		message.setContent("消息内容1");
//		message.setMsgFrom("发送者1");
//		message.setMsgTo("接收者1");
//		message.setReadType(1);
		
		message.setCreateTime("createTime");
		message.setCreateBy("createBy");
		message.setUpdateTime("updateTime");
		message.setUpdateBy("updateBy");
		message.setRemark("remark");

		System.out.println(msgService.update(message));
	}
	
	@Test
	public void get(){
		Message message = msgService.get("e9173abf41af487aa4ed04d512b7dcd1");
		System.out.println(message);	
	}
	
	@Test
	public void getByCondition(){
		Message message = new Message();
//		message.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		message.setDelFlag(1);
		message.setTitle("消息标题1改");
//		message.setContent("消息内容1");
//		message.setMsgFrom("发送者1");
//		message.setMsgTo("接收者1");
//		message.setReadType(1);
		
		Message message2 = msgService.getByCondition(message);
		System.out.println(message2);
	}
	
	
	@Test
	public void findList(){
		Message message = new Message();
//		message.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		message.setDelFlag(1);
//		message.setTitle("消息标题1");
		message.setContent("消息内容2");
//		message.setMsgFrom("发送者1");
//		message.setMsgTo("接收者1");
//		message.setReadType(1);
		
		List<Message> list = msgService.findList(message);
		System.out.println(list.size());
		
		for (Message Message : list) {
			System.out.println(Message);
		}
	}
	
	@Test
	public void findListPage(){
		Message message = new Message();
//		message.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		message.setDelFlag(1);
//		message.setTitle("消息标题1");
//		message.setContent("消息内容1");
//		message.setMsgFrom("发送者1");
//		message.setMsgTo("接收者1");
		message.setReadType(1);
		
		Page<Message> page = new Page<Message>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<Message> page2 = msgService.findPage(page, message);
		System.out.println(page2);
	}
	
	@Test
	public void findAllList(){
//		Message message = new Message();
		List<Message> list = msgService.findAllList();
		for (Message Message : list) {
			System.out.println(Message);
		}
	}
}
