package com.bcdbook.summer.test.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.pojo.Role;
import com.bcdbook.summer.system.service.RoleService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestRole {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private RoleService roleService = null;
	
	@Test
	public void add(){
		for (int i = 1; i < 6; i++) {
			Role role = new Role();
			role.setName("角色名称"+i);
			role.setEnname("juesemingcheng=="+i);
			role.setSort(i);
			roleService.add(role);
		}
//		System.out.println();
		
	}
	
	@Test 
	public void remove(){
		System.out.println(roleService.delete("c20f16019306472b8636a54ed4a215e1"));
	}
	
	@Test 
	public void removeByCondition(){
		Role role = new Role();
		role.setId("33f7f542e73547a38212eb7fe2cb326f");
		role.setDelFlag(1);
//		role.setName("name");
//		role.setDescription("description");
//		role.setIcon("icon");
//		role.setSort(1);
//		role.setPermission("permission");
//		role.setCreateTime("createTime");
//		role.setCreateBy("createBy");
//		role.setUpdateTime("updateTime");
//		role.setUpdateBy("updateBy");
//		role.setRemark("remark");
		
		System.out.println(roleService.deleteByCondition(role));
	}
	
	@Test
	public void update(){
		Role role = new Role();
		role.setId("33f7f542e73547a38212eb7fe2cb326f");
		role.setDelFlag(1);
		role.setName("name");
		role.setCreateTime("createTime");
		role.setCreateBy("createBy");
		role.setUpdateTime("updateTime");
		role.setUpdateBy("updateBy");
		role.setRemark("remark");
		
		System.out.println(roleService.update(role));
	}
	
	@Test
	public void get(){
		Role role = roleService.get("c20f16019306472b8636a54ed4a215e1");
		System.out.println(role);
	}
	
	@Test
	public void getByCondition(){
		Role role = new Role();
		role.setId("33f7f542e73547a38212eb7fe2cb326f");
		role.setDelFlag(1);
//		role.setMenuId("menuId");
//		role.setName("name");
//		role.setDescription("description");
//		role.setIcon("icon");
//		role.setSort(1);
//		role.setPermission("permission");
//		role.setIsShow(1);
//		role.setCreateTime("createTime");
//		role.setCreateBy("createBy");
////		role.setUpdateTime("updateTime");
//		role.setUpdateBy("updateBy");
//		role.setRemark("remark");
		
		Role role1 = roleService.getByCondition(role);
		System.out.println(role1);
	}
	
	
	@Test
	public void findList(){
		Role role = new Role();
//		role.setId("288e74d0758445489e83338636e8c5ae");
		role.setDelFlag(1);
//		role.setMenuId("menuid");
//		role.setName("name");
//		role.setDescription("description");
//		role.setIcon("icon");
//		role.setSort(1);
//		role.setPermission("permission");
//		role.setIsShow(1);
//		role.setCreateTime("createTime");
//		role.setCreateBy("createBy");
//		role.setUpdateTime("updateTime");
//		role.setUpdateBy("updateBy");
//		role.setRemark("remark");
		
		List<Role> roles = roleService.findList(role);
		System.out.println(roles.size());
		
		for (Role role2 : roles) {
			System.out.println(role2);
		}
	}
	
	@Test
	public void findListPage(){
		Role role = new Role();
//		role.setId("288e74d0758445489e83338636e8c5ae");
		role.setDelFlag(0);
//		role.setMenuId("menuid");
//		role.setName("name");
//		role.setDescription("description");
//		role.setIcon("icon");
//		role.setSort(1);
//		role.setPermission("permission");
//		role.setCreateTime("createTime");
//		role.setCreateBy("createBy");
//		role.setUpdateTime("updateTime");
//		role.setUpdateBy("updateBy");
//		role.setRemark("remark");
		
		Page<Role> page = new Page<Role>();
		page.setPageNum(2);
		page.setPageSize(2);
		page.setFunc("testFunction");
		
		Page<Role> page1 = roleService.findPage(page, role);
		System.out.println(page1);
	}
	
	@Test
	public void findAllList(){
		List<Role> list = roleService.findAllList();
		for (Role role : list) {
			System.out.println(role);
		}
	}
	
//	@Test
//	public void findListByMenuId(){
//		List<Role> roles = roleService.findListByMenuId("b5ae64d474da40e3b5033ae4eeb48dbd");
//		for (Role role : roles) {
//			System.out.println(role);
//		}
//	}
	
	
	
//	关联关系设定开始
	//栏目的设定
	@Test
	public void addMenu(){
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("roleId", "58ad7298bbc04e6ba5c29310e62b5664");
		parameter.put("menuId", "c800ea86d43d42feac57016f9ca9dccf");
		roleService.addMenu(parameter);
	}
	@Test
	public void deleteMenu(){
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("roleId", "58ad7298bbc04e6ba5c29310e62b5664");
		parameter.put("menuId", "b7db0192e52644b3935f65193fef57b6");
		roleService.deleteMenu(parameter);
	}
	@Test
	public void getMenu(){
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("roleId", "58ad7298bbc04e6ba5c29310e62b5664");
		parameter.put("menuId", "b5ae64d474da40e3b5033ae4eeb48dbd");
		int i = roleService.getMenu(parameter);
		System.out.println(i);
	}
	@Test
	public void listMenuByRole(){
		String roleId = "58ad7298bbc04e6ba5c29310e62b5664";
		List<Menu> menus = roleService.listMenuByRole(roleId);
		for (Menu menu : menus) {
			System.out.println(menu);
		}
	}
	
	//权限的设定
	@Test
	public void addPower(){
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("roleId", "58ad7298bbc04e6ba5c29310e62b5664");
		parameter.put("powerId", "431fc6a685284ba78125ae0213db3627");
		roleService.addPower(parameter);
	}
	@Test
	public void deletePower(){
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("roleId", "58ad7298bbc04e6ba5c29310e62b5664");
		parameter.put("powerId", "b55d2d26fda445549d1212a58ecb7a6f");
		roleService.deletePower(parameter);
	}
	@Test
	public void getPower(){
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("roleId", "58ad7298bbc04e6ba5c29310e62b5664");
		parameter.put("powerId", "b55d2d26fda445549d1212a58ecb7a6f");
		int i = roleService.getPower(parameter);
		System.out.println(i);
	}
	@Test
	public void listPowerByRole(){
		String roleId = "58ad7298bbc04e6ba5c29310e62b5664";
		List<Power> powers = roleService.listPowerByRole(roleId);
		for (Power power : powers) {
			System.out.println(power);
		}
	}
}
