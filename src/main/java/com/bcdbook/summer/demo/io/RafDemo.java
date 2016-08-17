package com.bcdbook.summer.demo.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

//java提供的对文件内容的访问,既可以读文件,也可以写文件
//支持随机访问文件,可以访问文件的任意位置

//java文件模型
//在硬盘上的文件是byte byte存储的,是数据的集合

//文件指针,打开文件时指针在开头 pointer= 0

//打开文件
//1. rw(读写)
	//write(int)-->只写一个字节(后8位),同时指针指向下一个位置,准备再次写入
//2. r(只读)
	//read() 只读一个字节
public class RafDemo {
	public static void rw() throws IOException{
		File dir = new File("/Users/lason/java/test");
		if(!dir.exists()){
			dir.mkdirs();
		}
		File file = new File(dir,"text.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		
		//创建一个文件操作对象
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		//输出指针的位置
		System.out.println(raf.getFilePointer());
		raf.write('A');
		System.out.println(raf.getFilePointer());
		raf.write('B');
		System.out.println(raf.getFilePointer());
		
		String s = "中国";
		byte[] gbk = s.getBytes();//直接写入byte数组
		raf.write(gbk);
		
		/**
		 * 读文件夹
		 */
		raf.seek(0);//设置指针到头部
		//创建一共字节数组,长度为文件的内容长度
		byte[] buf = new byte[(int)raf.length()];
		//执行读的操作
		raf.read(buf);
		System.out.println(Arrays.toString(buf));
		
		String s1 = new String(buf);
		System.out.println(s1);
		
		raf.close();
	}
	
	public static void main(String[] args) {
		try {
			rw();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
