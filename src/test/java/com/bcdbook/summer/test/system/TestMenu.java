package com.bcdbook.summer.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.service.MenuService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMenu {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private MenuService menuService = null;
	/**
	 * 
	 * @Description: 测试添加栏目的方法
	 * @param    
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年8月25日
	 */
	@Test
	public void add(){
		for (int i = 1; i < 11; i++) {
			Menu menu = new Menu();
			
			menu.setName("一级栏目"+i);
			menu.setDescription("一级栏目介绍"+i);
			menu.setHref("一级栏目连接"+i);
			menu.setTarget("_blank");
			menu.setIcon("icon"+i);
			menu.setSort(i);
			menu.setPermission("item:"+i);
			menu.setIsShow(1);
			
			System.out.println(menuService.add(menu));
		}
	}
	@Test
	public void addChild(){
		for (int i = 1; i < 4; i++) {
			Menu menu = new Menu();
			menu.setParentId("1d4cc6e713a042668ddcb8de641d979b");
			menu.setName("二级栏目"+i);
			menu.setDescription("二级栏目介绍"+i);
			menu.setHref("二级栏目连接"+i);
			menu.setTarget("_blank");
			menu.setIcon("icon-secede"+i);
			menu.setSort(i);
			menu.setPermission("item"+i);
			menu.setIsShow(1);
			
			System.out.println(menuService.add(menu));
		}
	}

	@Test 
	public void remove(){
		menuService.delete("d01eada7099c40d8bef88c4ab77d8eab");
	}
	
	@Test 
	public void removeByCondition(){
		Menu menu = new Menu();
		menu.setParentId("123");
		menuService.deleteByCondition(menu);
	}
	
	@Test
	public void update(){
		Menu menu = new Menu();
		menu.setId("c800ea86d43d42feac57016f9ca9dccf");
//		menu.setDelFlag(1);
//		menu.setName("一级栏目7");
//		menu.setDescription("一级栏目介绍");
//		menu.setHref("一级栏目连接");
//		menu.setTarget("_blank");
//		menu.setIcon("icon");
//		menu.setSort(1);
//		menu.setPermission("menu:1:");
//		menu.setIsShow(1);
		menu.setParentId("first");
		menuService.update(menu);
	}
	
	@Test
	public void get(){
		Menu menu = menuService.get("3557755892ae43d2a5c582091766a1f6");
		System.out.println(menu);
	}
	
	@Test
	public void getByCondition(){
		Menu menu = new Menu();
		menu.setId("3557755892ae43d2a5c582091766a1f6");
//		menu.setDelFlag(1);
		menu.setName("一级栏目7");
//		menu.setDescription("一级栏目介绍");
//		menu.setHref("一级栏目连接");
//		menu.setTarget("_blank");
//		menu.setIcon("icon");
//		menu.setSort(1);
//		menu.setPermission("menu:1:");
//		menu.setIsShow(1);
		
		Menu menu2 = menuService.getByCondition(menu);
		System.out.println(menu2);
	}
	
	
	@Test
	public void findList(){
		Menu menu = new Menu();
//		menu.setId("3557755892ae43d2a5c582091766a1f6");
		menu.setParentId("first");
		menu.setDelFlag(1);
//		menu.setName("一级栏目7");
//		menu.setDescription("一级栏目介绍");
//		menu.setHref("一级栏目连接");
//		menu.setTarget("_blank");
//		menu.setIcon("icon");
//		menu.setSort(1);
//		menu.setPermission("menu:1:");
		menu.setIsShow(1);
		
		List<Menu> list = menuService.findList(menu);
		
		for (Menu menu2 : list) {
			System.out.println(menu2);
			List<Menu> childs = menu2.getChilds();
			if(childs.size()>0){
				for (Menu menu3 : childs) {
					System.out.println("======>"+menu3);
					
					List<Power> powers = menu3.getPowers();
					if(powers.size()>0){
						for (Power power : powers) {
							System.out.println("============>"+power);
						}
					}
				}
			}
			List<Power> powers = menu2.getPowers();
			if(powers.size()>0){
				for (Power power : powers) {
					System.out.println("============>"+power);
				}
			}
		}
	}
	
	@Test
	public void findListPage(){
		Menu menu = new Menu();
//		menu.setId("3557755892ae43d2a5c582091766a1f6");
		menu.setDelFlag(1);
//		menu.setName("一级栏目7");
//		menu.setDescription("一级栏目介绍");
//		menu.setHref("一级栏目连接");
//		menu.setTarget("_blank");
//		menu.setIcon("icon");
//		menu.setSort(1);
//		menu.setPermission("menu:1:");
		menu.setIsShow(1);
		
		Page<Menu> page = new Page<Menu>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<Menu> page1 = menuService.findPage(page, menu);
		System.out.println(page1);
	}
	
	@Test
	public void findAllList(){
		List<Menu> menus = menuService.findAllList();
		for (Menu menu : menus) {
			System.out.println(menu);
		}
	}
}
