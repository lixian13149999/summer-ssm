package com.bcdbook.summer.demo.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.system.user.pojo.User;

/**
 * 
     * @Title: Json.java    
     * @Description: 用来测试alibab用于json处理的工具类fastjson
     * @author lason       
     * @created 2016年5月19日 下午7:28:10
 */
public class Json {
	/**
	 * 
	    * @Discription 对象转成json字符串的方法
	    * @author lason       
	    * @created 2016年5月19日 下午7:36:03
	 */
	public static String bean2JsonText(){
		User user = getUser();
		String userText = JSON.toJSONString(user);
//		JSON
//		System.out.println(userText);
		return userText;
	}
	
	/**
	 * 
	    * @Discription json格式的字符串转成节送对象
	    * @author lason       
	    * @created 2016年5月19日 下午7:38:21
	 */
	public static String text2JsonObject(){
		String userText = bean2JsonText();
		JSONObject jo = JSON.parseObject(userText);
		String userName = jo.getString("userName");
//		JSON.to
		return userName;
		
	}
	
//	public static 
	
	public static void main(String[] args) {
//		Json json = new Json();
		//测试对象转json字符串
//		System.out.println(bean2JsonText());
		
		//测试json字符串转json对象,并根据key获取对应的值
		System.out.println(text2JsonObject());
	}
	
	public static void list2Json(){
		List<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
//		JSON.p
//		JSonarr
	}
	
	/**
	    * @Discription 用于生成测试数据的方法
	    * @author lason       
	    * @created 2016年5月19日 下午7:29:55     
	    * @return User
	 */
	public static User getUser(){
		User user = new User();
		user.setUserName("admin");
		user.setAge(23);
		user.setGender(0);
		return user;
	}
	
}
