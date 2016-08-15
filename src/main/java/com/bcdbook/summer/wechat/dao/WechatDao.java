package com.bcdbook.summer.wechat.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.wechat.pojo.Wechat;


@Repository(value="wechatDao")
public interface WechatDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Wechat wechat);
    
    Wechat selectById(Integer id);
    
    Wechat selectRecent();
    
    void refreshToken(Wechat wechat);

}
