package com.bcdbook.summer.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.util.JsonUtil;
import com.bcdbook.summer.wechat.message.pojo.resp.Article;
import com.bcdbook.summer.wechat.message.pojo.resp.ImageMessage;
import com.bcdbook.summer.wechat.message.pojo.resp.MusicMessage;
import com.bcdbook.summer.wechat.message.pojo.resp.NewsMessage;
import com.bcdbook.summer.wechat.message.pojo.resp.TextMessage;
import com.bcdbook.summer.wechat.message.pojo.resp.VideoMessage;
import com.bcdbook.summer.wechat.message.pojo.resp.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WechatUtil {
	/**
	 * 
	    * @Discription 获取菜单的配置文件,并解析成json格式的字符串
	    * @author lason       
	    * @created 2016年5月25日 上午10:06:29     
	    * @return
	 */
	public String getMenu() {
		String menu = null;
		String rootPath = System.getProperty("user.dir");

		String paramPath = rootPath.substring(0, rootPath.length() - 4)
				+ "/webapps/strom/WEB-INF/classes/wechat.json";
//		 String paramPath =
//		 "/Users/lason/java/tomcat7/webapps/strom/WEB-INF/classes/wechat.json";

		JsonUtil jsonUtil = new JsonUtil();
		String paramString = jsonUtil.readJson(paramPath);
		JSONObject paramJson = JSON.parseObject(paramString);

		menu = paramJson.getString("menu");

		return menu;
	}

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
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws IOException, DocumentException {
		// System.out.println("进入parseXml");
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		
//		System.out.println(document.toString());
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点

		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();

		System.out.println("用户发来的消息");
		// 遍历所有子节点
		for (Element e : elementList) {
			
			System.out.println("<"+e.getName()+">"+e.getText()+"<"+e.getName()+"/>");

			map.put(e.getName(), e.getText());
		}

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}
	
	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	
	/**
	 * 图片消息对象转换成xml
	 * 
	 * @param imageMessage 图片消息对象
	 * @return xml
	 */
	public static String imageMessageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转换成xml
	 * 
	 * @param voiceMessage 语音消息对象
	 * @return xml
	 */
	public static String voiceMessageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
	
	
	/**
	 * 视频消息对象转换成xml
	 * 
	 * @param videoMessage 视频消息对象
	 * @return xml
	 */
	public static String videoMessageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage 图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;
				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	/**
	 * 
	    * @Discription 对url转码的工具方法
	    * @author lason       
	    * @created 2016年5月26日 上午10:35:08     
	    * @param str
	    * @return
	 */
    public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
