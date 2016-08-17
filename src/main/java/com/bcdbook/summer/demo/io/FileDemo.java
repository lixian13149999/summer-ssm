package com.bcdbook.summer.demo.io;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @Description: file的相关demo及介绍
 * @author lason
 * @date 2016年8月17日
 * 
 * 1. java.io.File类表示文件(目录)
 * 2. File类只用于表示文件(目录)的信息(名称,大小等),不能用于文件内容的访问
 * 
 */
public class FileDemo {
	//创建file的方法
	public static void init(){
		// 1. 直接通过路径来创建file对象
		File file1 = new File("/Users/lason/java/test");
		System.out.println(file1.exists());
		
		// 2. 通过路径和子路径,创建file对象
		File file2 = new File("/Users/lason/java/test","text.txt");
		System.out.println(file2.exists());
	}
	
	//创建(删除)目录
	public static void mkdir(){
		File file1 = new File("/Users/lason/java/test/mkdir");
		if(!file1.exists())
			System.out.println(file1.exists());
			file1.mkdir();//mkdir只会创建一级目录
			System.out.println(file1.exists());
			
		File file2 = new File("/Users/lason/java/test/mkdirs1/mkdirs11");
		if(!file2.exists()){
			System.out.println(file2.exists());
			file2.mkdirs();//mkdirs会创建一个目录树
			System.out.println(file2.exists());
		}else{
			file2.delete();//删除的时候只会删除最后一级目录
		}
	}
	//创建(删除)文件
	public static void delete(){
		File file1 = new File("/Users/lason/java/test/mkdir/text.txt");
		if(file1.exists()){
			//删除方法既可以删除目录,也可以删除文件
			file1.delete();
		}else{
			try {
				//创建一个新的文件
				file1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void filePara(){
		File file = new File("/Users/lason/java/test/text.txt");
		System.out.println(file);//直接打印file(调用的是file的toString方法)
		System.out.println(file.getAbsolutePath());//打印file的绝对路径
		try {
			System.out.println(file.getCanonicalPath());//不知道是什么东西
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.getFreeSpace());
		System.out.println(file.getName());//打印file的名字
		System.out.println(file.getParent());//打印file的父级目录
		System.out.println(file.getPath());//file的目录
		System.out.println(file.getTotalSpace());//所有空间
		System.out.println(file.getUsableSpace());//可用空间
		System.out.println(file.getAbsoluteFile());//
		try {
			System.out.println(file.getCanonicalFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.getParentFile());
		
	}
	public static void main(String[] args) {
//		init();
//		mkdir();
//		delete();
		filePara();
	}
}
