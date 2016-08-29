package com.bcdbook.summer.test.system;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.service.PowerService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestPower {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private PowerService powerService = null;
	
//	private String menuId;//所属栏目的id
//	private String name;//权限名称
//	private String description;//权限简介
//	private String icon;//权限图标
//	private int sort;//权限排序
//	private String permission;//权限标识
//	private int isShow;//是否显示
	@Test
	public void add(){
		Power power = new Power("menuid","权限名称","权限简介","icon",1,"user:add",1);
		power.preInsert();
		System.out.println(powerService.add(power));
	}
}