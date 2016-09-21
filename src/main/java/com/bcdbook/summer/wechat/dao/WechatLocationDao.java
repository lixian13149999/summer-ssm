package com.bcdbook.summer.wechat.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.wechat.pojo.Location;

@Repository(value="wechatLocationDao")
public interface WechatLocationDao extends CrudDao<Location> {

}
