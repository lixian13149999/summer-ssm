package com.bcdbook.summer.common.backmsg;


import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.util.StringUtils;
import com.google.common.collect.Maps;

/**
 */
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
	
	/**
	 * @Description: 检查固定返回值否是是成功的值
	 * @param @param backMsg
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public static boolean isSuccess(String backMsg){
		//如果传入的值为空,则直接返回false
		if(StringUtils.isNull(backMsg))
			return false;
		
		//把传入的字符串解析成json对象
		JSONObject backMsgJson = JSONObject.parseObject(backMsg);
				
		if(backMsgJson==null)
			return false;
		
		//获取其中的code值
		int code = backMsgJson.getIntValue("code");
		//获取其中的type值
		int type = backMsgJson.getIntValue("type");
		
		if(code==200&&type==1)
			return true;
		
		return false;
	}
}
