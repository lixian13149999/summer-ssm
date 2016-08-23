package com.bcdbook.summer.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bcdbook.summer.wechat.pojo.message.Message;


@Repository(value = "wechatMsgDao")
public interface WechatMsgDao {
	// int deleteByPrimaryKey(Integer id);
	
 	 int insert(Message message);
 	 
	 int update(Message message);
	//
	 Message selectById(String id);
	// Message selectByKeyword(String keyword);
	// Message selectByName(String name);
	 
	//根据传入的条件查询到对应的Message对象
	Message selectByCondition(@Param("excludeId")String id,@Param("keyword")String keyword
			,@Param("name")String name,@Param("msgType")String msgType );
	
	//此处为新添加 获取message列表
	List<Message> selectAllMsg();
	
	//按照id删除Msg
	void deleteById(Integer id);
	
	//分页查询
	List<Message> getMsgPageByCondition(@Param("begin")int begin, @Param("pageSize")int size,
			@Param("typeString")String typeString, @Param("keyword")String keyword);
	
   //获取message的总条数
	int countMsgByConditon(@Param("typeString")String typeString, @Param("keyword")String keyword);
	
}
