package com.bcdbook.summer.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.service.UserService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestUser {
	private static Logger logger = Logger.getLogger(TestUser.class);
	
	@Resource
	private UserService userService = null;
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
	public void add(){
		User user = new User();
		
		user.setUserName("userName");
		user.setPwd("pwd");
		user.setAge(0);
		user.setGender(0);
		user.setPhone("phone");
		user.setPhoneState(0);
		user.setEmail("email");
		user.setEmailState(0);
		user.setOpenId("openId");
		user.setWechatState(0);
		user.setCompany(1);
		user.setDepartment(2);
		user.setPosition(3);
		user.setIsLock(4);
		
		System.out.println(userService.add(user));
	}
//	@Test
//	public void addChild(){
//		for (int i = 1; i < 4; i++) {
//			User user = new User();
//			
//			System.out.println(userService.add(user));
//		}
//	}

	@Test 
	public void remove(){
		userService.delete("96c0afebc32e450ebaf1a7a188914653");
	}
	
	@Test 
	public void removeByCondition(){
		User user = new User();
		user.setId("96c0afebc32e450ebaf1a7a188914653");
		userService.deleteByCondition(user);
	}
	
	@Test
	public void update(){
		User user = new User();
		user.setId("96c0afebc32e450ebaf1a7a188914653");
		user.setUserName("userName1");
		user.setPwd("pwd1");
		user.setAge(10);
		user.setGender(10);
		user.setPhone("phone1");
		user.setPhoneState(10);
		user.setEmail("email1");
		user.setEmailState(10);
		user.setOpenId("openId1");
		user.setWechatState(10);
		user.setCompany(11);
		user.setDepartment(21);
		user.setPosition(31);
		user.setIsLock(41);
		userService.update(user);
	}
	
	@Test
	public void get(){
		User user = userService.get("96c0afebc32e450ebaf1a7a188914653");
		System.out.println(user);
	}
	
	@Test
	public void getByCondition(){
		User user = new User();
		user.setId("96c0afebc32e450ebaf1a7a188914653");
		user.setUserName("userName1");
		user.setPwd("pwd1");
		user.setAge(10);
		user.setGender(10);
		user.setPhone("phone1");
		user.setPhoneState(10);
		user.setEmail("email1");
		user.setEmailState(10);
		user.setOpenId("openId1");
		user.setWechatState(10);
		user.setCompany(11);
		user.setDepartment(21);
		user.setPosition(31);
		user.setIsLock(41);
		
		User user2 = userService.getByCondition(user);
		System.out.println(user2);
	}
	
	
	@Test
	public void findList(){
		User user = new User();
//		user.setId("3557755892ae43d2a5c582091766a1f6");
//		user.setParentId("first");
		user.setDelFlag(1);
//		user.setName("一级栏目7");
//		user.setDescription("一级栏目介绍");
//		user.setHref("一级栏目连接");
//		user.setTarget("_blank");
//		user.setIcon("icon");
//		user.setSort(1);
//		user.setPermission("user:1:");
//		user.setIsShow(1);
		
		List<User> users = userService.findList(user);
		for (User user2 : users) {
			System.out.println(user2);
		}
	}
	
	@Test
	public void findListPage(){
		User user = new User();
//		user.setId("3557755892ae43d2a5c582091766a1f6");
		user.setDelFlag(1);
//		user.setName("一级栏目7");
//		user.setDescription("一级栏目介绍");
//		user.setHref("一级栏目连接");
//		user.setTarget("_blank");
//		user.setIcon("icon");
//		user.setSort(1);
//		user.setPermission("user:1:");
//		user.setIsShow(1);
		
		Page<User> page = new Page<User>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<User> page1 = userService.findPage(page, user);
		System.out.println(page1);
	}
	
	@Test
	public void findAllList(){
		List<User> users = userService.findAllList();
		for (User user : users) {
			System.out.println(user);
		}
	}
	
}
