package com.bcdbook.summer.test.wechat;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.wechat.pojo.Location;
import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.service.WechatMaterialService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestWechatMaterial {
	private static Logger logger = Logger.getLogger(TestWechatMaterial.class);
	
	@Resource
	private WechatMaterialService wechatMaterialService;
	
	@Test
	public void add(){
		WechatMaterial wechatMaterial = new WechatMaterial();
		wechatMaterial.setMsgType("text");
		wechatMaterial.setKeyword("key");
		wechatMaterial.setMediaId("2323231");
		wechatMaterial.setContent("html");
		wechatMaterial.setName("name");
		wechatMaterial.setUrl("www.baidu.com");
		wechatMaterial.setDescription("des");
		wechatMaterial.setParentMediaId("22221");
		wechatMaterial.setTitle("title");
		wechatMaterial.setThumbMediaId("121");
		wechatMaterial.setShowCoverPic(1);
		wechatMaterial.setAuthor("vae");
		wechatMaterial.setDigest("dig");
		wechatMaterial.setContentSourceUrl("www.bilibili.com");
		wechatMaterial.setSort(3);
		wechatMaterial.setHqMusicUrl("www.google.com");
		wechatMaterialService.add(wechatMaterial);
	}
	
	@Test
	public void delete(){
		wechatMaterialService.delete("a7064dfbd49a43e78eaa77b451594983");
	}
	
	@Test
	public void delete2(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("a7064dfbd49a43e78eaa77b451594983");
		wechatMaterialService.deleteByCondition(wechatMaterial);
	}
	
	@Test
	public void update(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("a7064dfbd49a43e78eaa77b451594983");
		wechatMaterial.setMsgType("gif");
		wechatMaterial.setUrl("spring.io");
		wechatMaterialService.update(wechatMaterial);
	}
	
	@Test
	public void get(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("a7064dfbd49a43e78eaa77b451594983");
		WechatMaterial wechatMaterial1 = wechatMaterialService.getByCondition(wechatMaterial);
		System.out.println(wechatMaterial1);
	}
	
}
