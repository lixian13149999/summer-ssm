package com.bcdbook.summer.demo.io;

import java.io.File;

//列出一些file类的常用操作--过滤,遍历等等
public class FileUtil {
	/**
	 * @Description: 列出指定目录下(包括其子目录)的所有文件
	 * @param @param dir   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年8月17日
	 */
	public static void listDirectory(File dir){
		if(!dir.exists()){
			throw new IllegalArgumentException("目录:"+dir+"不存在");
		}
		if(!dir.isDirectory()){
			throw new IllegalArgumentException(dir+"不是目录");
		}
		/*
		//通过list方法,获取当前目录下的所有文件(目录)的名字的集合
		//直接子的名称,不包含子目录下的内容
		String[] fileNames = dir.list();
		for (String string : fileNames) {
			System.out.println(string);
		}
		*/
		
		//如果要遍历目录下的内容,需要构造
		
		File[] files = dir.listFiles();
		if(files!=null&&files.length>0){
			for (File file : files) {
				System.out.println(file);
				if(file.isDirectory()){
					listDirectory(file);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		File dir = new File("/Users/lason/java/test");
		listDirectory(dir);
	}
}
