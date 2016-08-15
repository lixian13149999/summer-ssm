package com.bcdbook.summer.common.backmsg;


import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class BackMsg {

	public String getMsg(Integer code,Integer type,Object data,String errorMsg){
		Map<String, Object> msgMap = Maps.newHashMap();
		msgMap.put("code", code);
		msgMap.put("type", type);
		msgMap.put("data", data);
		msgMap.put("error_msg", errorMsg);
		return JSON.toJSONString(msgMap);
	}
	
	public static String error(){
		BackMsg backMsg = new BackMsg();
		return backMsg.getMsg(500, 0, "", "server_error");
	}
	public static String error(String errorMsg){
		BackMsg backMsg = new BackMsg();
		return backMsg.getMsg(500, 0, "", errorMsg);
	}
	
	public static String success(){
		BackMsg backMsg = new BackMsg();
		return backMsg.getMsg(200, 1, "", "success");
	}
	public static String success(String errorMsg){
		BackMsg backMsg = new BackMsg();
		return backMsg.getMsg(200, 1, "", errorMsg);
	}
	public static String success(Object data){
		BackMsg backMsg = new BackMsg();
		return backMsg.getMsg(200, 1, data, "success");
	}
	public static String success(Object data,String errorMsg){
		BackMsg backMsg = new BackMsg();
		return backMsg.getMsg(200, 1, data, errorMsg);
	}
}
