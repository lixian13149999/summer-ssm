package com.bcdbook.summer.test.system;

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
	
	/**
	 * 
	 * @Description: 测试添加栏目的方法
	 * @param    
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年8月25日
	 */
	@Test
	public void testAddMenu(){
		Menu menu = new Menu("4713f725dcc7482ea257d6d0d779761a", "二级栏目1.2", "这是栏目的详细描述", "www.bcdbook.com", "_bank", "icon", 2, "user", 1);
		menu.preInsert();
		
		System.out.println(menuService.add(menu));
	}
	
	@Test
	public void testGetMenuById(){
		String id = "4713f725dcc7482ea257d6d0d779761a";
//		logger.info("将要执行查询");
		Menu menu = menuService.get(id);
		System.out.println(menu);
	}
	
	@Test
	public void testGetMenuByEntity(){
		String id = "4713f725dcc7482ea257d6d0d779761a";
//		logger.info("将要执行查询");
		Menu menu = new Menu();
		menu.setId(id);
//		menu.setIsShow(1);
//		menu.setIcon("icon");
		Menu menu2 = menuService.getByCondition(menu);
		System.out.println(menu2);
	}
	
	@Test
	public void testGetChilds(){
		String id = "4713f725dcc7482ea257d6d0d779761a";
		System.out.println(menuService.getChilds(id));
	}
}
