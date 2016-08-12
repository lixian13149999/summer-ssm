package com.bcdbook.summer.wechat.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.wechat.service.ConnectService;
import com.bcdbook.summer.wechat.service.TemplateService;
import com.bcdbook.summer.wechat.util.WechatRequest;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {

	@Resource
	private ConnectService connectService;
	
	@Override
	public String sendTemplate(String openId,String templateId,String linkUrl,String data) {
		String ACCESS_TOKEN = connectService.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ACCESS_TOKEN;
		
		JSONObject reqJson = new JSONObject();
		reqJson.put("touser", openId);
		reqJson.put("template_id", templateId);
		reqJson.put("url", linkUrl);
		reqJson.put("data", JSON.parse(data));
		
		String reqData = reqJson.toJSONString();
		System.out.println(reqData);
		String backData = null;
		
		try {
			backData = WechatRequest.wechatPost(url, reqData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return backData;
	}
	
	@Override
	public String setIndustry(int industryId1, int industryId2) {
		String ACCESS_TOKEN = connectService.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+ACCESS_TOKEN;
		String industrys = "{\"industry_id1\":\""+industryId1+"\",\"industry_id2\":\""+industryId2+"\"}";
		String backValue = null;
		try {
			backValue = WechatRequest.wechatPost(url, industrys);
			System.out.println(backValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return backValue;
	}

	@Override
	public String getIndustryInfo() {
		String ACCESS_TOKEN = connectService.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+ACCESS_TOKEN;
		String backValue = null;
		
		try {
			backValue = WechatRequest.wechatGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return backValue;
	}

//	OPENTM207466405
//	产品变动通知
//	OPENTM207276764
//	产品开放期提醒
//	OPENTM204532436
//	产品上线提醒
	@Override
	public String getTemplateId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

}
