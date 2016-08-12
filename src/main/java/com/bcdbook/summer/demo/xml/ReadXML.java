package com.bcdbook.summer.demo.xml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
     * @Title: ReadXML.java    
     * @Description: 测试用dom4j解析xml的类
     * @author lason       
     * @created 2016年5月19日 下午10:05:05
 */
public class ReadXML {
	public void parseXML(){
		//通过文件路径获取到xml文件
		File xmlFile = new File("/Users/lason/workspaces/test.xml");
		//创建xml读取流
		SAXReader sr = new SAXReader();
		//获取document对象
		Document document = null;
		try {
			document = sr.read(xmlFile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		//获取元素的根节点
		Element rootEle = document.getRootElement();
		
		//获取所有一级子元素
		List<Element> childs = rootEle.elements();
		//根据传入的标签名,获取其下一级子集的元素
		List<Element> childsByName = rootEle.elements("user");
		
		//获取指定名字的第一个子元素
		Element firstEle = rootEle.element("teacher");
		//获取元素的属性
		String eleRemark = firstEle.attributeValue("remark");
		
		
		System.out.println(rootEle);
		System.out.println(childs);
		System.out.println(childsByName);
		System.out.println(firstEle);
		System.out.println(eleRemark);
	}
	
	public static void main(String[] args) {
		ReadXML rm = new ReadXML();
		rm.parseXML();
//		System.out.println(rm.getClassPath());
	}
}
