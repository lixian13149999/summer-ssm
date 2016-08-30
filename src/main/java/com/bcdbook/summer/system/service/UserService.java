package com.bcdbook.summer.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.UserDao;
import com.bcdbook.summer.system.pojo.User;

@Service("userService")
//public class UserService implements InitializingBean{
public class UserService extends CrudService<UserDao,User>{
	@Resource
	private UserDao userDao;

}
