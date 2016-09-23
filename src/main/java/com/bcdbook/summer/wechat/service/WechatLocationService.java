package com.bcdbook.summer.wechat.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.wechat.dao.WechatLocationDao;
import com.bcdbook.summer.wechat.pojo.Location;
import com.bcdbook.summer.wechat.pojo.message.resp.WechatRespMessage;


@Service("wechatLocationService")
public class WechatLocationService extends CrudService<WechatLocationDao, Location> {
	@Resource
	private WechatLocationDao wechatLocationDao;

	/**
	 * @Description: 保存微信自动上报的地理位置
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月23日
	 */
	public String addLocationFromEvent(Map<String, String> reqMapMsg) {
		if(reqMapMsg==null)
			return null;
		
		//获取想要存储的数据,并验证器合法性
		String openId = reqMapMsg.get("FromUserName");
		String latitudeStr = reqMapMsg.get("Latitude");
		String longitudeStr = reqMapMsg.get("Longitude");
		String precisionStr = reqMapMsg.get("Precision");
		if(StringUtils.isNull(openId)
				||StringUtils.isNull(latitudeStr)
				||StringUtils.isNull(longitudeStr)
				||StringUtils.isNull(precisionStr))
			return null;
		
		//把相关的参数进行类型转换
		double latitude = Double.valueOf(latitudeStr);
		double longitude = Double.valueOf(longitudeStr);
		double precision = Double.valueOf(precisionStr);
		
		//封装地理位置对象
		Location location = new Location();
		location.setOpenId(openId);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setLocPrecision(precision);
		
		return add(location)==1?WechatRespMessage.SUCCESS:null;
	}
}
