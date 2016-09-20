package com.bcdbook.summer.wechat.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.wechat.dao.WechatMaterialDao;
import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.util.WechatUtil;

/**
 * @Description: 微信素材管理的类
 * @author lason
 * @date 2016年9月20日
 */
@Service("wechatMaterialService")
public class WechatMaterialService extends CrudService<WechatMaterialDao, WechatMaterial>{

	/**
	 * 
	 * @Description: 获取素材总数的方法
	 * @param @param accessToken
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	public String count(String accessToken) {
		//拼接请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token="+accessToken;
		//执行请求,并返回微信的返回值
		return WechatUtil.wechatGet(url);
	}

	/**
	 * @Description: 用来获取素材列表的方法
	 * @param @param accessToken
	 * @param @param type
	 * @param @param offset
	 * @param @param count
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	public String list(String accessToken, String type, int offset, int count) {
		//拼接请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken;
		
		//创建map对象,用以封装参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("offset", offset);
		paramMap.put("count", count);
		//获取封装后的参数字符串
		String param = JSON.toJSONString(paramMap);
		
		//执行post请求,并返回请求返回的值
		return WechatUtil.wechatPost(url, param);
	}

}
