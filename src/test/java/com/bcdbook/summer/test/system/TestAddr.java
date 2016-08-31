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
import com.bcdbook.summer.system.service.AddrService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestAddr {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private AddrService addrService = null;
	
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

	
//	public static final Integer SIGN_UP_ADDR = 1;//注册地址
//	public static final Integer DELIVERY_ADDR = 2;//收货地址
//
//	public static final Integer DEFAULT = 1;//默认
//	public static final Integer UNDEFAULT = 2;//非默认
	
	@Test
	public void add(){
		Addr addr = new Addr("李四", 1, 1, "江苏省",
				32, "苏州市", 320502, "工业园区",
				100, "东振路", "张三", "0512-65966315",
				2) ;
		addrService.add(addr);
//		System.out.println();		
	}
	
	@Test 
	public void remove(){
		addrService.delete("16033c3230894dc0aa5b98d005d039a4");

	}
	
	@Test 
	public void removeByCondition(){
		Addr addr = new Addr();
//		addr.setId("fbfcf06b35f94ca0af6769f073d1f39b");
		addr.setDelFlag(1);
//		addr.setUserId("李四1");
//		addr.setType(1);
//		addr.setIsDefault(1);
//		addr.setprovince("江苏省1");
//		addr.setprovinceCode(32);
//		addr.setcity("苏州市1");
//		addr.setcityCode(320502);
//		addr.setcounty("工业园区1");
//		addr.setcountyCode(100);
//		addr.setinfoAddr("东振路1");
//		addr.setaddressee("张三1");
//		addr.setPhone("0512-65966315");
//		addr.setsort(2);
		
		System.out.println(addrService.deleteByCondition(addr));
	}
		
	
	@Test
	public void update(){	
		Addr addr = new Addr();
		addr.setId("1d887bd9ee714f6d980701b3c1428ad6");
		addr.setDelFlag(1);
		addr.setUserId("李四1");
		addr.setSort(2);
		addr.setType(1);
		addr.setIsDefault(1);
		addr.setProvince("江苏省1");
		addr.setProvinceCode(32);
		addr.setCity("苏州市1");
		addr.setCityCode(320502);
		addr.setCounty("工业园区1");
		addr.setCountyCode(100);
		addr.setInfoAddr("东振路1");
		addr.setAddressee("张三1");
		addr.setPhone("0512-65966315");
		
		addr.setCreateTime("createTime");
		addr.setCreateBy("createBy");
		addr.setUpdateTime("updateTime");
		addr.setUpdateBy("updateBy");
		addr.setRemark("remark");

		System.out.println(addrService.update(addr));
	}
	
	@Test
	public void get(){
		Addr addr = addrService.get("6f67ee4b39854164924b9a2e437a8b70");
		System.out.println(addr);
		
	}
	
	@Test
	public void getByCondition(){
		Addr addr = new Addr();
		addr.setId("fbfcf06b35f94ca0af6769f073d1f39b");
		addr.setDelFlag(1);
		addr.setUserId("李四1");
//		addr.setType(1);
//		addr.setIsDefault(1);
//		addr.setprovince("江苏省1");
//		addr.setprovinceCode(32);
//		addr.setcity("苏州市1");
//		addr.setcityCode(320502);
//		addr.setcounty("工业园区1");
//		addr.setcountyCode(100);
//		addr.setinfoAddr("东振路1");
//		addr.setaddressee("张三1");
		addr.setPhone("0512-65966315");
//		addr.setsort(2);
		
		Addr addr2 = addrService.getByCondition(addr);
		System.out.println(addr2);
	}
	
	
	@Test
	public void findList(){
		Addr addr = new Addr();
		addr.setId("fbfcf06b35f94ca0af6769f073d1f39b");
		addr.setDelFlag(1);
		addr.setUserId("李四1");
//		addr.setType(1);
//		addr.setIsDefault(1);
//		addr.setprovince("江苏省1");
//		addr.setprovinceCode(32);
//		addr.setcity("苏州市1");
//		addr.setcityCode(320502);
//		addr.setcounty("工业园区1");
//		addr.setcountyCode(100);
//		addr.setinfoAddr("东振路1");
//		addr.setaddressee("张三1");
		addr.setPhone("0512-65966315");
//		addr.setsort(2);
		
		List<Addr> list = addrService.findList(addr);
		System.out.println(list.size());
		
		for (Addr Addr : list) {
			System.out.println(Addr);
		}
	}
	
	@Test
	public void findListPage(){
		Addr addr = new Addr();
		addr.setId("fbfcf06b35f94ca0af6769f073d1f39b");
		addr.setDelFlag(1);
		addr.setUserId("李四");
//		addr.setType(1);
//		addr.setIsDefault(1);
//		addr.setprovince("江苏省1");
//		addr.setprovinceCode(32);
//		addr.setcity("苏州市1");
//		addr.setcityCode(320502);
//		addr.setcounty("工业园区1");
//		addr.setcountyCode(100);
//		addr.setinfoAddr("东振路1");
//		addr.setaddressee("张三1");
		addr.setPhone("0512-65966315");
//		addr.setsort(2);
		
		Page<Addr> page = new Page<Addr>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<Addr> page2 = addrService.findPage(page, addr);
		System.out.println(page2);
	}
	
	@Test
	public void findAllList(){
//		Addr addr = new Addr();
		List<Addr> list = addrService.findAllList();
		for (Addr Addr : list) {
			System.out.println(Addr);
		}
	}	
}
