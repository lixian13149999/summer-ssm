package com.bcdbook.summer.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.context.ContextParameter;
import com.bcdbook.summer.common.util.JsonUtil;
import com.bcdbook.summer.common.util.SHA1;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.wechat.pojo.Wechat;

public class WechatUtil {

	private static Logger logger = Logger.getLogger(WechatUtil.class);
	/**
	 * 
	 * @Discription 处理getter请求,传入一个连接地址,返回相应格式的字符串值
	 * @author lason
	 * @created 2016年5月24日 下午3:12:15
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String wechatGet(String url) {
		//验证参数合法性
		if(StringUtils.isNull(url))
			return null;
		
		//定义返回值
		String backValue = null;
		
		// 创建地址对象
		try {
			URL urlGet = new URL(url);
			//开启连接
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			
			//连接参数的相关配置
			http.setRequestMethod("GET"); //定义请求方式
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置连接特性
			http.setDoOutput(true);//是否可以输出
			http.setDoInput(true);//是否可以写入
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();//执行连接
			
			//创建读取流,从流中读取字节
			InputStream is = http.getInputStream();
			//防止相应不及时的问题
			int size = 0;
			while (size == 0) {
				//获取文件字节的大小
				size = is.available();
			}
			byte[] jsonBytes = new byte[size];//根据字节流的大小,创建一个字节数组
			is.read(jsonBytes);//执行读取操作
			backValue = new String(jsonBytes, "UTF-8");//把读到的字节,转成字符串(编码是UTF-8)
		} catch (MalformedURLException e) {
			logger.info("WechatUtil GET 请求 MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("WechatUtil GET 请求 IOException");
			e.printStackTrace();
		}
//		System.out.println("backValue:"+backValue);
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
	public static String wechatPost(String url, String postValue) {
		
		if(StringUtils.isNull(url))
			return null;
		
		//定义返回值
		String backValue = null;

		//当url不为空的时候执行
		try {
			//根据传入的url,创建发送请求的URL对象
			URL urlPost = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlPost.openConnection();//开启连接
			
			//连接参数的相关配置
			http.setRequestMethod("POST");//定义传输方式
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//定义传输的格式
			http.setDoOutput(true);//允许读取
			http.setDoInput(true);//允许写入
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();//执行连接
			
			OutputStream os = http.getOutputStream();//获取写入流
			os.write(postValue.getBytes("UTF-8"));//传入参数
			os.flush();//刷新流
			os.close();//关闭写入流
			
			InputStream is = http.getInputStream();//获取返回数据流
			//防止相应不及时的问题
			int size = 0;
			while (size == 0) {
				// 获取文件字节的大小
				size = is.available();
			}
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);//把数据传入的字节数组
			backValue = new String(jsonBytes, "UTF-8");
		} catch (MalformedURLException e) {
			logger.info("WechatUtil POST 请求 MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("WechatUtil POST 请求 IOException");
			e.printStackTrace();
		}
		return backValue;
	}

	
	/**
	 * 
	    * @Discription 通过调用微信端的接口,获取accessToken的方法
	    * @author lason       
	    * @created 2016年6月1日 下午1:33:42      
	    * @return     
	    * @see com.bcdbook.summer.wechatold.service.ConnectService#makeAccessToken()
	 */
	public static String getNewAccessTokenFromWechatServer() {
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
				+ "&appid="
				+ Wechat.APP_ID
				+ "&secret=" 
				+ Wechat.APP_SECRET;
		//定义用来返回的accessToken,
		String accessToken = null;
		
		//执行请求,获取accessToken的返回值
		String tokenBack = wechatGet(url);
		if(tokenBack!=null){
			JSONObject tokenJson = JSON.parseObject(tokenBack);
			accessToken = tokenJson.getString("access_token");
		}
		return accessToken;
	}
	
	/**
	 * @Description: 验证连接是否OK的方法
	 * 根据传入的验证值和用来生成验证值的不定量参数,进行校验,并返回校验结构
	 * @param @param equalValue
	 * @param @param checkValues
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	public static boolean safeUuid(String equalValue,String... checkValues){
		//验证参数的合法性
		if(StringUtils.isNull(equalValue)
				||checkValues==null
				||checkValues.length==0)
			return false;
		
		//获取拼接后的字符串
		String pieceString = StringUtils.sortString(checkValues);
		if(StringUtils.isNull(pieceString))
			return false;
		
		//对排序后的字符串进行sha1加密
		String usSignature = SHA1.encode(pieceString);
		
		return usSignature.equals(equalValue);
	}
	
	/**
	 * @Description: 获取json配置文件中的栏目
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	public static String getMenu() {
		String menu = null;
		
		//获取项目的跟路径
		String basePash = ContextParameter.getRealPath();
		if(basePash==null)
			return null;
		//拼接栏目配置文件的目标路径
		String filePath = basePash+"WEB-INF/classes/wechat.json";

		//把获取到的文件解析成json
		JsonUtil jsonUtil = new JsonUtil();
		String paramString = jsonUtil.readJson(filePath);
		JSONObject paramJson = JSON.parseObject(paramString);

		//把解析好的json转换成字符串
		menu = paramJson.getString("menu");

		return menu;
	}
	
	public static void main(String[] args) {
		System.out.println(getNewAccessTokenFromWechatServer());
	}
}
