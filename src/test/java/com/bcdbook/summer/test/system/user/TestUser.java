package com.bcdbook.summer.test.system.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.system.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestUser {
	private static Logger logger = Logger.getLogger(TestUser.class);
	// private ApplicationContext ac = null;
	@Resource
	private UserService userService = null;

//	 @Before
//	 public void before() {
//	 ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//	 userService = (IUserService) ac.getBean("userService");
//	 }

	@Test
	public void testGetUser() {
		User user = userService.getUserById(1);
		System.out.println(user);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
		logger.info(JSON.toJSONString(user));
	}

	@Test
	public void testTransaction() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 2; i++) {
			User user = new User();
			user.setUserName("user" + i);
			users.add(user);
		}
		userService.addUsers(users);
	}

	// 测试添加用户接口方法
	@Test
	public void testAddUser() {
		User user=new User("jack" ,"123456" , 23, 0, "13812345678", "12345@qq.com", "totologin", "江苏省苏州市工业园区旺墩路xx号", 3, 4, 1, 8, 0, "2015-6-7", "2016-6-5", "有房有车富二代");
	
		System.out.println(userService.addUser(user));
	}

	// 测试更新用户接口方法
	@Test
	public void testUpdateUser() {
		User user=new User(38,"tome" ,"123dd6" , 23, 0, "13812345678", "12345@qq.com", "totologin", "江苏省苏州市工业园区旺墩路xx号", 3, 4, 1, 8, 0, "2015-6-7", "2016-6-5", "有房有车富二代");
		
		//System.out.println(user.getId());
		System.out.println(userService.updateUser(user));

	}

	// 测试删除用户接口方法
	@Test
	public void teDeleteUser() {
		User user=new User();
		user.setId(37);
		System.out.println(userService.deleteUser(user));;

	}

	// 测试绑定openId接口方法
	@Test
	public void testBindingWechat() {
		
		System.out.println(userService.bindingWechat("jack","123456" , "gggggg"));

	}

	// 测试绑定邮箱接口方法
	@Test
	public void testBindingEmail() {
		System.out.println(userService.bindingEmail("jack", "123456", "56666@qq.com"));

	}
}
