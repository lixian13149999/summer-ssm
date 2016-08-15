package com.bcdbook.summer.test.common.config;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.wechat.service.ConnectService;

@RunWith(SpringJUnit4ClassRunner.class)
//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class GlobalTest {
	private static Logger logger = Logger.getLogger(Global.class);
	
	@Resource
	private ConnectService connectService = null;
	@Test
	public void testLoader(){
//		PropertiesLoader loader = (new Global()).
//		Global.
//		System.out.println("abc");
		System.out.println(Global.getConfig("wechat.a"));
	}
	
	@Test
	public void testGetAccessToken(){
//		System.out.println(connectService.makeAccessToken());
		logger.error("获取AccessToken出错");
		connectService.updateAccessToken(1);
	}
}
