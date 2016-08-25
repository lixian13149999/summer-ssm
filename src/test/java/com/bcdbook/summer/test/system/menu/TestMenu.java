package com.bcdbook.summer.test.system.menu;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.service.MenuService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMenu {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private MenuService menuService = null;
	
	@Test
	public void testGetMenu(){
		String id = "123";
		logger.info("将要执行查询");
		Menu menu = menuService.getMenu(id);
		System.out.println(menu);
	}
}
