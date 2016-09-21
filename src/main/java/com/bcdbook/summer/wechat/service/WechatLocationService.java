package com.bcdbook.summer.wechat.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.wechat.dao.WechatLocationDao;
import com.bcdbook.summer.wechat.pojo.Location;


@Service("wechatLocationService")
public class WechatLocationService extends CrudService<WechatLocationDao, Location> {
	@Resource
	private WechatLocationDao wechatLocationDao;
}
