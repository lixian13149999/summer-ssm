package com.bcdbook.summer.test.wechat;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.wechat.pojo.Location;
import com.bcdbook.summer.wechat.service.WechatLocationService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestWechatLocation {
	private static Logger logger = Logger.getLogger(TestWechatLocation.class);
	
	@Resource
	private WechatLocationService wechatLocationService;
	
	@Test
	public void add(){
		Location location = new Location();
		location.setOpenId("1314222");
		location.setLatitude(12.34);
		location.setLongitude(23.24);
		location.setLocPrecision(12.0);
		location.setLabel("suzhou");
		wechatLocationService.add(location);
	}
	
	@Test
	public void delete(){
		wechatLocationService.delete("d8388e0513ea4df6b3e6743314942d7a");
	}
	
	@Test
	public void delete2(){
		Location location = new Location();
		location.setOpenId("1314222");
		location.setLatitude(12.34);
		location.setLongitude(23.24);
		location.setLocPrecision(12.0);
		location.setLabel("suzhou");
		wechatLocationService.deleteByCondition(location);
	}
	
	@Test
	public void update(){
		Location location = wechatLocationService.get("d8388e0513ea4df6b3e6743314942d7a");
		location.setOpenId("1314222");
		location.setLatitude(12.34);
		location.setLongitude(23.24);
		location.setLocPrecision(12.1);
		location.setLabel("suzhou");
		wechatLocationService.update(location);
	}
	
	@Test
	public void get(){
		Location location = new Location();
		location.setOpenId("1314222");
		location.setLatitude(12.34);
		location.setLongitude(23.24);
		location.setLocPrecision(12.0);
		location.setLabel("suzhou");
		Location location1 = wechatLocationService.getByCondition(location);
		System.out.println(location1);
	}
	
	@Test
	public void getTime(){
		System.out.println(DateUtil.toTimeString("1464869363000"));
		System.out.println(DateUtil.toTimeString("1464869337000"));
		System.out.println(DateUtil.toTimeString("1464869040000"));
		System.out.println(DateUtil.toTimeString("1464703252000"));
		System.out.println(DateUtil.toTimeString("1464703220000"));
		System.out.println(DateUtil.toTimeString("1464703213000"));
		System.out.println(DateUtil.toTimeString("1474367022000"));
	}

}
