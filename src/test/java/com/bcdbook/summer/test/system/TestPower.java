package com.bcdbook.summer.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
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
		for (int i = 1; i < 6; i++) {
			Power power = new Power("b7db0192e52644b3935f65193fef57b6","权限名称2."+i,"权限简介2."+i,"icon"+i,i,"user:add"+i,1);
			powerService.add(power);
		}
//		System.out.println();
		
	}
	
	@Test 
	public void remove(){
		System.out.println(powerService.delete("fb089243ea8d463b9d83e6dcc6a6ca36"));
	}
	
	@Test 
	public void removeByCondition(){
		Power p = new Power();
		p.setId("2e3e70a280d74e569531ae937c84d6fa");
		p.setDelFlag(1);
		p.setMenuId("menuId");
//		p.setName("name");
//		p.setDescription("description");
//		p.setIcon("icon");
//		p.setSort(1);
//		p.setPermission("permission");
		p.setIsShow(1);
//		p.setCreateTime("createTime");
//		p.setCreateBy("createBy");
//		p.setUpdateTime("updateTime");
//		p.setUpdateBy("updateBy");
//		p.setRemark("remark");
		
		System.out.println(powerService.deleteByCondition(p));
	}
	
	@Test
	public void update(){
		Power p = new Power();
		p.setId("288e74d0758445489e83338636e8c5ae");
		p.setDelFlag(1);
		p.setMenuId("menuId");
		p.setName("name");
		p.setDescription("description");
		p.setIcon("icon");
		p.setSort(1);
		p.setPermission("permission");
		p.setIsShow(1);
		p.setCreateTime("createTime");
		p.setCreateBy("createBy");
		p.setUpdateTime("updateTime");
		p.setUpdateBy("updateBy");
		p.setRemark("remark");
		
		System.out.println(powerService.update(p));
	}
	
	@Test
	public void get(){
		Power p = powerService.get("288e74d0758445489e83338636e8c5ae");
		System.out.println(p);
		
	}
	
	@Test
	public void getByCondition(){
		Power p = new Power();
		p.setId("0013bce8f4744899b7d1d1ab4cc59437");
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
		
		Power p1 = powerService.getByCondition(p);
		System.out.println(p1);
	}
	
	
	@Test
	public void findList(){
		Power p = new Power();
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
		
		List<Power> powers = powerService.findList(p);
		System.out.println(powers.size());
		
		for (Power power : powers) {
			System.out.println(power);
		}
	}
	
	@Test
	public void findListPage(){
		Power p = new Power();
//		p.setId("288e74d0758445489e83338636e8c5ae");
		p.setDelFlag(0);
//		p.setMenuId("menuid");
//		p.setName("name");
//		p.setDescription("description");
//		p.setIcon("icon");
//		p.setSort(1);
//		p.setPermission("permission");
		p.setIsShow(1);
//		p.setCreateTime("createTime");
//		p.setCreateBy("createBy");
//		p.setUpdateTime("updateTime");
//		p.setUpdateBy("updateBy");
//		p.setRemark("remark");
		
		Page<Power> page = new Page<Power>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<Power> page1 = powerService.findPage(page, p);
		System.out.println(page1);
	}
	
	@Test
	public void findAllList(){
//		Power p = new Power();
		List<Power> list = powerService.findAllList();
		for (Power power : list) {
			System.out.println(power);
		}
	}
	
	@Test
	public void findListByMenuId(){
		List<Power> powers = powerService.findListByMenuId("b5ae64d474da40e3b5033ae4eeb48dbd");
		for (Power power : powers) {
			System.out.println(power);
		}
	}
}
