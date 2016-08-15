package com.bcdbook.summer.test.common.backmsg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.config.Global;

@RunWith(SpringJUnit4ClassRunner.class)
//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestBackMsg {
	private static Logger logger = Logger.getLogger(Global.class);
	
	private static BackMsg backMsg = new BackMsg();
	
	@Test
	public void testGetBackMsg(){
		List<String> list = new ArrayList<String>();
		list.add("abc");
		
		String msg = backMsg.getMsg(1, 2,list ,"error_msg");
		System.out.println(msg);
		System.out.println(backMsg.error());
		System.out.println(backMsg.error("这是错误提示信息"));
		System.out.println(backMsg.success());
		System.out.println(backMsg.success("这是成功提示信息"));
		System.out.println(backMsg.success(new Date()));
		System.out.println(backMsg.success("",""));
	}
	
//	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("abc");
//		
//		String msg = backMsg.getMsg(1, 2,list ,"error_msg");
//		System.out.println(msg);
//	}
}
