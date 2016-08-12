package com.bcdbook.summer.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @Title: Req.java
 * @Description: 处理微信请求的公共类
 * @author lason
 * @created 2016年5月24日 下午3:11:52
 */
public class WechatRequest {
	/**
	 * 
	 * @Discription 处理getter请求,传入一个连接地址,返回相应格式的字符串值
	 * @author lason
	 * @created 2016年5月24日 下午3:12:15
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String wechatGet(String url) throws IOException {
		String backValue = null;
		// 当url不为空的时候执行
		if (url != null && !url.trim().equals("")) {
			// 创建地址对象
			URL urlGet = new URL(url);
			// 创建连接
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");// 设置连接特性
			http.setDoOutput(true);// 是否可以输出
			http.setDoInput(true);// 是否可以写入
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();// 执行连接

			// 创建读取流,从流中读取字节
			InputStream is = http.getInputStream();
			// 防止相应不及时的问题
			int size = 0;
			while (size == 0) {
				// 获取文件字节的大小
				size = is.available();
			}
			byte[] jsonBytes = new byte[size];// 根据字节流的大小,创建一个字节数组
			is.read(jsonBytes);// 执行读取操作
			backValue = new String(jsonBytes, "UTF-8");// 把读到的字节,转成字符串(编码是UTF-8)

			return backValue;
		}
		return backValue;
	}

	/**
	 * 
	 * @Discription 微信post请求的处理
	 * @author lason
	 * @created 2016年5月24日 下午3:33:02
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String wechatPost(String url, String postValue)
			throws IOException {
		//定义返回值
		String backValue = null;

		// 当url不为空的时候执行
		if (url != null && !url.trim().equals("")) {
			URL urlPost = new URL(url);//根据传入的url,创建发送请求的URL对象
			HttpURLConnection http = (HttpURLConnection) urlPost.openConnection();//开启连接

			http.setRequestMethod("POST");//定义传输方式
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//定义传输的格式
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();//执行连接
			
			OutputStream os = http.getOutputStream();//获取写入流
			os.write(postValue.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();//关闭写入流

			InputStream is = http.getInputStream();//获取返回数据流
			// 防止相应不及时的问题
			int size = 0;
			while (size == 0) {
				// 获取文件字节的大小
				size = is.available();
			}
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			backValue = new String(jsonBytes, "UTF-8");
		}
		return backValue;
	}
}
