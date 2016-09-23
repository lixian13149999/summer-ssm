package com.bcdbook.summer.test.wechat;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
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
	public void addBackId(){
		Location location = wechatLocationService.get("d8388e0513ea4df6b3e6743314942d7a");
		location.setId(null);
		String backId = wechatLocationService.addBackId(location);
		System.out.println("backId: "+backId);
	}
	
	@Test
	public void delete(){
		wechatLocationService.delete("d8388e0513ea4df6b3e6743314942d7a");
	}
	
	@Test
	public void deleteByCondition(){
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
		location.setLocPrecision(12.11);
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
	public void findList(){
		Location location = wechatLocationService.get("d8388e0513ea4df6b3e6743314942d7a");
		List<Location> list = wechatLocationService.findList(location);
		for(Location w : list){
			System.out.println(w);
		}
	}
	
	@Test
	public void findAllList(){
		Location location = wechatLocationService.get("d8388e0513ea4df6b3e6743314942d7a");
		List<Location> list = wechatLocationService.findList(location);
		for(Location w : list){
			System.out.println(w);
		}
	}
	
	@Test
	public void findPage(){
		Location location = wechatLocationService.get("d8388e0513ea4df6b3e6743314942d7a");
		Page<Location> page = new Page<Location>() ;
		page.setPageNum(1);
		page.setPageSize(10);
		page.setFunc("where?");
		Page<Location> newPage = wechatLocationService.findPage(page, location);
		System.out.println(newPage.getCountResult());
	}

}
