package com.bcdbook.summer.test.wechat;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.wechat.pojo.Wechat;
import com.bcdbook.summer.wechat.service.WechatService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestWechat {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private WechatService wechatService;
	
	@Test
	public void add(){
		Wechat wechat = new Wechat();
		wechat.setWechatKey("wechatKey");
		wechat.setWechatValue("wechatValue");
		wechat.setRefreshValue("refreshValue");
		
		wechatService.add(wechat);
	}
	
	@Test
	public void delete(){
		wechatService.delete("8eae82996fe1454a90d93aa89173ee68");
	}
	
	@Test
	public void delete2(){
		Wechat wechat = new Wechat();
		wechat.setWechatKey("wechatKey");
		wechat.setWechatValue("wechatValue");
		wechat.setRefreshValue("refreshValue");
		wechatService.deleteByCondition(wechat);
	}
	
	@Test
	public void update(){
		Wechat wechat = wechatService.get("8eae82996fe1454a90d93aa89173ee68");
		wechat.setWechatKey("wechatKey1");
		wechat.setWechatValue("wechatValue1");
		wechat.setRefreshValue("refreshValue1");
		wechatService.update(wechat);
	}
	
	@Test
	public void get(){
		Wechat wechat = new Wechat();
		wechat.setWechatKey("wechatKey1");
		wechat.setWechatValue("wechatValue1");
		wechat.setRefreshValue("refreshValue1");
		Wechat wc = wechatService.getByCondition(wechat);
		System.out.println(wc);
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
//	@Test
//	public void getWechatByKey(){
//		Wechat w = wechatService.getWechatByKey(Wechat.KEY_ACCESS_TOKEN);
//		System.out.println(w);
//	}
}
