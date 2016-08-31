package com.bcdbook.summer.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.system.pojo.Addr;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.service.AddrService;
import com.bcdbook.summer.system.service.MessageService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestAddr {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private AddrService AddrService = null;
	
//	private String userId;//用户id外键
//	private int type;//地址类型
//	private int isDefault;//是否是默认地址
//	private String province;//省/直辖市
//	private int provinceCode;//省/直辖市编码
//	private String city;//市
//	private int cityCode;//市编码
//	private String county;//县
//	private int countyCode;//县编码
//	private String infoAddr;//详细地址
//	private String addressee;//收件人
//	private String phone;//收件人电话
//	private int sort;//排序
	
	@Test
	public void add(){
		Addr p = new Addr("asd", 1, 1, "wws",
				1, "ews", 2, "rws",
				3, "tzxc", "ysd", "uws",
				2) ;
			AddrService.add(p);
//		System.out.println();
		
	}
	
	@Test 
	public void remove(){
		AddrService.delete("ead38f47075b42bf9506f0ec498ef09a");
		AddrService.delete("f628d1d8af124861946bbdcce3f48d73");
		}
	
	@Test 
	public void removeByCondition(){
		Addr p = new Addr();
//		p.setId("fbfcf06b35f94ca0af6769f073d1f39b");
		p.setDelFlag(1);
//		p.setUserId("asd");
//		p.setType("name");
//		p.setIsDefault("description");
//		p.setprovince("icon");
//		p.setprovinceCode(1);
//		p.setcity("permission");

//		p.setcityCode("createTime");
//		p.setcounty("createBy");
//		p.setcountyCode("updateTime");
//		p.setinfoAddr("updateBy");
//		p.setaddressee("remark");
//		p.setPhone("uu");
//		p.setsort("remark");
		
		System.out.println(AddrService.deleteByCondition(p));
	}
		
	
	@Test
	public void update(){
		Addr p = new Addr();
		p.setId("e5b1d42fe063463dbf7d2be48897cd07");
		p.setDelFlag(1);
		p.setUserId("qq");
//		p.setType("name");
//		p.setIsDefault("description");
//		p.setprovince("icon");
//		p.setprovinceCode(1);
//		p.setcity("permission");

//		p.setcityCode("createTime");
//		p.setcounty("createBy");
//		p.setcountyCode("updateTime");
//		p.setinfoAddr("updateBy");
//		p.setaddressee("remark");
		p.setPhone("u2");
//		p.setsort("remark");
//		AddrService.update(p);
//		System.out.println(AddrService.update(p));
	}
	
	@Test
	public void get(){
		Addr p = AddrService.get("6f67ee4b39854164924b9a2e437a8b70");
		System.out.println(p);
		
	}
	
	@Test
	public void getByCondition(){
		Addr p = new Addr();
		p.setId("051011ca4c3b4d8c98c44ca9eec42c5a");
		p.setDelFlag(1);
		p.setUserId("q2");
//		p.setType("name");
//		p.setIsDefault("description");
//		p.setprovince("icon");
//		p.setprovinceCode(1);
//		p.setcity("permission");
//		p.setcityCode("createTime");
//		p.setcounty("createBy");
//		p.setcountyCode("updateTime");
//		p.setinfoAddr("updateBy");
//		p.setaddressee("remark");
		p.setPhone("uu");
//		p.setsort("remark");
		
		Addr p1 = AddrService.getByCondition(p);
		System.out.println(p1);
	}
	
	
	@Test
	public void findList(){
		Addr p = new Addr();
		p.setId("051011ca4c3b4d8c98c44ca9eec42c5a");
		p.setDelFlag(1);
		p.setUserId("q2");
//		p.setType("name");
//		p.setIsDefault("description");
//		p.setprovince("icon");
//		p.setprovinceCode(1);
//		p.setcity("permission");

//		p.setcityCode("createTime");
//		p.setcounty("createBy");
//		p.setcountyCode("updateTime");
//		p.setinfoAddr("updateBy");
//		p.setaddressee("remark");
		p.setPhone("u2");
//		p.setsort("remark");
		
		List<Addr> p2 = AddrService.findList(p);
		System.out.println(p2.size());
		
		for (Addr Addr : p2) {
			System.out.println(Addr);
		}
	}
	
	@Test
	public void findListPage(){
		Addr p = new Addr();
		p.setId("af4f044c407c46ca8baaa5fa688aad14");
		p.setDelFlag(1);
		p.setUserId("qq");
//		p.setType("name");
//		p.setIsDefault("description");
//		p.setprovince("icon");
//		p.setprovinceCode(1);
//		p.setcity("permission");
//		p.setcityCode("createTime");
//		p.setcounty("createBy");
//		p.setcountyCode("updateTime");
//		p.setinfoAddr("updateBy");
//		p.setaddressee("remark");
		p.setPhone("uu");
//		p.setsort("remark");
		
		Page<Addr> page = new Page<Addr>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<Addr> page1 = AddrService.findPage(page, p);
		System.out.println(page1);
	}
	
	@Test
	public void findAllList(){
//		Power p = new Power();
		List<Addr> list = AddrService.findAllList();
		for (Addr Addr : list) {
			System.out.println(Addr);
		}
	}	
}
