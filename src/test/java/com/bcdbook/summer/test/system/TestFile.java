package com.bcdbook.summer.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.system.pojo.File;
import com.bcdbook.summer.system.service.FileService;
import com.bcdbook.summer.test.wechat.TestWechat;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestFile {
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private FileService fileService = null;
	
	private Integer sort;//顺序
	private String sourceName;//原名
	private String name;//现在名
	private String src;//路径地址
	private Integer size;//文件大小
	private Integer type;//文件类型
	private String suffix;//文件后缀
	private String pojoId;//文件所属类的id
	private Integer pojoType;//在所属类中的类型
	
	//@Test
	public void add(){
		File file = new File(1,"原文件名_10", "文件名_10", "C盘",2,2, "txt","类ID_1", 1);
		fileService.add(file);
	}
	
	@Test 
	public void remove(){
		fileService.delete("c05824ca95c44ab685d7f404bda538f2");
	}
	
	@Test 
	public void removeByCondition(){
		File file = new File();
//		file.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		file.setDelFlag(1);
		file.setSort(1);
		file.setSourceName("原文件名");
//		file.setName("文件名");
//		file.setSrc("C盘");
//		file.setSize(1024);
//		file.setType(1);
//		file.setSuffix("java");
//		file.setPojoId("类ID_1");
//		file.setPojoType(1);
		
//		file.setCreateTime("createTime");
//		file.setCreateBy("createBy");
//		file.setUpdateTime("updateTime");
//		file.setUpdateBy("updateBy");
//		file.setRemark("remark");
		
		System.out.println(fileService.deleteByCondition(file));
	}
		
	
	@Test
	public void update(){	
		File file = new File();
		file.setId("1d2294034f754c26a4ea3ace9e657161");
		file.setDelFlag(1);
		file.setSort(1);
		file.setSourceName("原文件名_1改");
		file.setName("文件名_1改");
		file.setSrc("C盘");
		file.setSize(1024);
		file.setType(1);
		file.setSuffix("exe");
		file.setPojoId("类ID_1");
		file.setPojoType(1);
		
		file.setCreateTime("createTime");
		file.setCreateBy("createBy");
		file.setUpdateTime("updateTime");
		file.setUpdateBy("updateBy");
		file.setRemark("remark");

		System.out.println(fileService.update(file));
	}
	
	@Test
	public void get(){
		File file = fileService.get("1d2294034f754c26a4ea3ace9e657161");
		System.out.println(file);	
	}
	
	@Test
	public void getByCondition(){
		File file = new File();
//		file.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		file.setDelFlag(1);
//		file.setSort(1);
		file.setSourceName("原文件名_1改");
//		file.setName("文件名");
//		file.setSrc("C盘");
//		file.setSize(2);
//		file.setType(1);
//		file.setSuffix("java");
//		file.setPojoId("类ID_1");
//		file.setPojoType(1);
		
//		file.setCreateTime("createTime");
//		file.setCreateBy("createBy");
//		file.setUpdateTime("updateTime");
//		file.setUpdateBy("updateBy");
//		file.setRemark("remark");
		
		File file2 = fileService.getByCondition(file);
		System.out.println(file2);
	}
	
	
	@Test
	public void findList(){
		File file = new File();
//		file.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		file.setDelFlag(1);
		file.setSort(1);
//		file.setSourceName("原文件名_1");
//		file.setName("文件名_2");
		file.setSrc("D盘");
//		file.setSize(1);
//		file.setType(1);
		file.setSuffix("java");
//		file.setPojoId("类ID_1");
//		file.setPojoType(1);
		
//		file.setCreateTime("createTime");
//		file.setCreateBy("createBy");
//		file.setUpdateTime("updateTime");
//		file.setUpdateBy("updateBy");
//		file.setRemark("remark");
		
		List<File> list = fileService.findList(file);
		System.out.println(list.size());
		
		for (File File : list) {
			System.out.println(File);
		}
	}
	
	@Test
	public void findListPage(){
		File file = new File();
//		file.setId("fbfcf06b35f94ca0af6769f073d1f39b");
//		file.setDelFlag(1);
//		file.setSort(1);
//		file.setSourceName("原文件名");
//		file.setName("文件名");
//		file.setSrc("C盘");
//		file.setSize(1);
//		file.setType(1);
//		file.setSuffix("java");
		file.setPojoId("类ID_1");
//		file.setPojoType(1);
		
//		file.setCreateTime("createTime");
//		file.setCreateBy("createBy");
//		file.setUpdateTime("updateTime");
//		file.setUpdateBy("updateBy");
//		file.setRemark("remark");
		
		Page<File> page = new Page<File>();
		page.setPageNum(1);
		page.setPageSize(4);
		page.setFunc("testFunction");
		
		Page<File> page2 = fileService.findPage(page, file);
		System.out.println(page2);
	}
	
	@Test
	public void findAllList(){
//		File file = new File();
		List<File> list = fileService.findAllList();
		for (File File : list) {
			System.out.println(File);
		}
	}
}
