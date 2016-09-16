package com.bcdbook.summer.test.common.tools;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.common.tools.pojo.Mail;
import com.bcdbook.summer.common.tools.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMail {
	private static Logger logger = Logger.getLogger(TestMail.class);
	
	@Resource
	private MailService emailService = null;
	
	@Test
	public void add(){
		Mail email = new Mail();
		email.setSubject("主题");
		email.setText("文本内容");
		email.setContent("主体内容");
		
		email.setModalUrl("模型地址");
		email.setMailKey("bundUser");
		emailService.add(email);
//		for (int i = 1; i < 6; i++) {
//			Mail email = new Mail("b7db0192e52644b3935f65193fef57b6","权限名称2."+i,"权限简介2."+i,"icon"+i,i,"user:add"+i,1);
//			emailService.add(email);
//		}
//		System.out.println();
		
	}
	
	@Test 
	public void remove(){
		System.out.println(emailService.delete("261502514e284feeb31647f40aaf7b57"));
	}
	
	@Test 
	public void removeByCondition(){
		Mail p = new Mail();
		p.setId("35db8e23fe9444fb9aae93fa47a7cc33");
		p.setDelFlag(1);
//		p.setMenuId("menuId");
//		p.setName("name");
//		p.setDescription("description");
//		p.setIcon("icon");
//		p.setSort(1);
//		p.setPermission("permission");
//		p.setIsShow(1);
//		p.setCreateTime("createTime");
//		p.setCreateBy("createBy");
//		p.setUpdateTime("updateTime");
//		p.setUpdateBy("updateBy");
//		p.setRemark("remark");
		
		System.out.println(emailService.deleteByCondition(p));
	}
	
	@Test
	public void update(){
		Mail email = new Mail();
		email.setId("686cb427bb0948f2b3b2b88bee1662df");
		email.setDelFlag(1);
		
		email.setSubject("主题1");
		email.setText("文本内容1");
		email.setContent("主体内容1");
		
		email.setModalUrl("模型地址1");
		email.setMailKey("bundUser1");
		
		email.setCreateTime("createTime");
		email.setCreateBy("createBy");
		email.setUpdateTime("updateTime");
		email.setUpdateBy("updateBy");
		email.setRemark("remark");
		
		System.out.println(emailService.update(email));
	}
	
	@Test
	public void get(){
		Mail p = emailService.get("686cb427bb0948f2b3b2b88bee1662df");
		System.out.println(p);
		
	}
	
	@Test
	public void getByCondition(){
		Mail p = new Mail();
		p.setId("2bcc839808824d8db664c9fce372ec41");
//		p.setDelFlag(1);
//		p.setMenuId("menuId");
//		p.setName("name");
//		p.setDescription("description");
//		p.setIcon("icon");
//		p.setSort(1);
//		p.setPermission("permission");
//		p.setIsShow(1);
//		p.setCreateTime("createTime");
//		p.setCreateBy("createBy");
////		p.setUpdateTime("updateTime");
//		p.setUpdateBy("updateBy");
//		p.setRemark("remark");
		
		Mail p1 = emailService.getByCondition(p);
		System.out.println(p1);
	}
	
	
	@Test
	public void findList(){
		Mail p = new Mail();
//		p.setId("288e74d0758445489e83338636e8c5ae");
		p.setDelFlag(1);
//		p.setMenuId("menuid");
//		p.setName("name");
//		p.setDescription("description");
//		p.setIcon("icon");
//		p.setSort(1);
//		p.setPermission("permission");
//		p.setIsShow(1);
//		p.setCreateTime("createTime");
//		p.setCreateBy("createBy");
//		p.setUpdateTime("updateTime");
//		p.setUpdateBy("updateBy");
//		p.setRemark("remark");
		
		List<Mail> emails = emailService.findList(p);
		System.out.println(emails.size());
		
		for (Mail email : emails) {
			System.out.println(email);
		}
	}
	
	@Test
	public void findListPage(){
		Mail p = new Mail();
//		p.setId("288e74d0758445489e83338636e8c5ae");
		p.setDelFlag(0);
//		p.setMenuId("menuid");
//		p.setName("name");
//		p.setDescription("description");
//		p.setIcon("icon");
//		p.setSort(1);
//		p.setPermission("permission");
//		p.setCreateTime("createTime");
//		p.setCreateBy("createBy");
//		p.setUpdateTime("updateTime");
//		p.setUpdateBy("updateBy");
//		p.setRemark("remark");
		
		Page<Mail> page = new Page<Mail>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<Mail> page1 = emailService.findPage(page, p);
		System.out.println(page1);
	}
	
	@Test
	public void findAllList(){
//		Mail p = new Mail();
		List<Mail> list = emailService.findAllList();
		for (Mail email : list) {
			System.out.println(email);
		}
	}
	
//	@Test
//	public void findListByMenuId(){
//		List<Mail> emails = emailService.findListByMenuId("b5ae64d474da40e3b5033ae4eeb48dbd");
//		for (Mail email : emails) {
//			System.out.println(email);
//		}
//	}
	
}
