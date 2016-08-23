package com.bcdbook.summer.system.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bcdbook.summer.system.pojo.User;

@Repository(value="userDao")
public interface UserDao {
	
//	//根据主键删除
//    int deleteById(Integer id);
//    
//    //插入几个基本的信息（暂时没有使用）
//    int insert(User record);
//    
//    //选择性添加一条用户记录---------
//    int insertSelective(User record);
//    
//    //根据id查找
//    User selectById(Integer id);
//    
//    //根据主键即id选择性更新user
//    int updateByPrimaryKeySelective(User record);
//    
//    //更新几个基本的信息（暂时没有用)
//    int updateByPrimaryKey(User record);    
//    
//    //根据名查找用户(注：用户名在user数据库中是唯一的)    
//    User selectByName(String name);
//    
//    //根据用户名和密码对查找用户，即验证用户名和密码
//    User selectByNameAndPassword(@Param("userName")String userName,@Param("password")String password);
    
}
