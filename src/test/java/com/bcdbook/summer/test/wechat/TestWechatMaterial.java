package com.bcdbook.summer.test.wechat;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.service.WechatMaterialService;
import com.bcdbook.summer.wechat.service.WechatService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestWechatMaterial {
	private static Logger logger = Logger.getLogger(TestWechatMaterial.class);
	
	@Resource
	private WechatMaterialService wechatMaterialService;
	@Resource
	private WechatService wechatService;
	
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
		wechatMaterial.setPicUrl("www.gif.com");
		wechatMaterial.setHqMusicUrl("www.google.com");
		wechatMaterialService.add(wechatMaterial);
		System.out.println(wechatMaterialService.getByCondition(wechatMaterial));
	}
	
	@Test
	public void addBackId(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("801f06ba2fad4c0fb1ad01e5634a4643");
		wechatMaterial.setId(null);
		String backId = wechatMaterialService.addBackId(wechatMaterial);
		System.out.println("backId: "+backId);
	}
	
	@Test
	public void delete(){
		String id = "801f06ba2fad4c0fb1ad01e5634a4643";
		wechatMaterialService.delete(id);
	}
	
	@Test
	public void deleteByCondition(){
		String id = "a49df4f5c33b4d0c8a6786f01099aaf6";
		WechatMaterial wechatMaterial = wechatMaterialService.get(id);
		wechatMaterialService.deleteByCondition(wechatMaterial);
	}
	
	@Test
	public void update(){
		String id = "a49df4f5c33b4d0c8a6786f01099aaf6";
		WechatMaterial wechatMaterial = wechatMaterialService.get(id);
		System.out.println("before: "+wechatMaterial);
		wechatMaterial.setMsgType("mpeg");
		wechatMaterial.setUrl("spring.io");
		wechatMaterial.setPicUrl("www.fpga.com");
		wechatMaterialService.update(wechatMaterial);
		System.out.println("after:  "+wechatMaterialService.get(id));
	}
	
	@Test
	public void get(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("75bf7698e0114b78ac54d99b6bf9e6b7");
		WechatMaterial wechatMaterial1 = wechatMaterialService.getByCondition(wechatMaterial);
		System.out.println(wechatMaterial1);
	}
	
	@Test
	public void findList(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("cdb9819726144cf99b0aea0c64904515");
		List<WechatMaterial> list = wechatMaterialService.findList(wechatMaterial);
		for(WechatMaterial w : list){
			System.out.println(w);
		}
	}
	
	@Test
	public void findAllList(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("cdb9819726144cf99b0aea0c64904515");
		List<WechatMaterial> list = wechatMaterialService.findList(wechatMaterial);
		for(WechatMaterial w : list){
			System.out.println(w);
		}
	}
	
	@Test
	public void findPage(){
		WechatMaterial wechatMaterial = wechatMaterialService.get("cdb9819726144cf99b0aea0c64904515");
		Page<WechatMaterial> page = new Page<WechatMaterial>() ;
		page.setPageNum(1);
		page.setPageSize(10);
		page.setFunc("where?");
		Page<WechatMaterial> newPage = wechatMaterialService.findPage(page, wechatMaterial);
		System.out.println(newPage.getCountResult());
	}
	
	public void getPicUrlByMediaId(){
		String accessToken = wechatService.getAccessToken();
		String mediaId = "mErowUdivp53pbwYC5N7cSLx5K5GbE0H40";
		String picUrl = wechatMaterialService.getPicUrlByMediaId(accessToken, mediaId);
		
		System.out.println(picUrl);
	}
}
