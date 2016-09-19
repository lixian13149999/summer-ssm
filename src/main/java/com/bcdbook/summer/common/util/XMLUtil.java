package com.bcdbook.summer.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {
	
	/**
	 * 
	 * @Discription 解析请求信息的工具类,从request中获取xml格式的数据,转换并返回map格式的对象
	 *              key是标签名,value是标签内的数据值
	 * @author lason
	 * @created 2016年5月24日 下午10:01:15
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(InputStream inputStream)
			throws IOException, DocumentException {
		
		// System.out.println("进入parseXml");
		
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点

		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList) {
			
			System.out.println("<"+e.getName()+">"+e.getText()+"<"+e.getName()+"/>");

			map.put(e.getName(), e.getText());
		}

		// 释放资源
		if(inputStream!=null){
			inputStream.close();
			inputStream = null;
		}

		return map;
	}
}
