package com.bcdbook.summer.system.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.RoleDao;
import com.bcdbook.summer.system.dao.UserDao;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.pojo.Role;
import com.bcdbook.summer.system.pojo.User;

@Service("userService")
//public class UserService implements InitializingBean{
public class UserService extends CrudService<UserDao,User>{
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	
	/**
	 * @Description: 添加用户,角色关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int addRole(Map<String, String> parameter){
		if(!checkParameter(parameter))
			return 0;
		
		return userDao.getRole(parameter)>0?0:userDao.addRole(parameter);
	}
	/**
	 * @Description: 删除用户,角色关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int deleteRole(Map<String, String> parameter){
		if(!checkParameter(parameter))
			return 0;
		
		return userDao.deleteRole(parameter);
	}
	/**
	 * @Description: 查询用户,角色关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int getRole(Map<String, String> parameter){
		if(!checkParameter(parameter))
			return 0;
		
		return userDao.getRole(parameter);
	}
	/**
	 * @Description: 根据用户获取此用户拥有的角色集合
	 * @param @param userId
	 * @param @return   
	 * @return List<Role>  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public List<Role> listRoleByUser(String userId){
		if(userId==null||userId.equals(""))
			return null;
		
		return userDao.listRoleByUser(userId);
	}
	/*
	 * 检查参数的公用方法,如果参数合法返回true,否则返回false
	 */
	private boolean checkParameter(Map<String, String> parameter){
		if(parameter==null)
			return false;
		
		String userId = parameter.get("userId");
		String roleId = parameter.get("roleId");
		if(userId==null||userId.equals("")||roleId==null||roleId.equals(""))
			return false;
		
		return true;
	}
	
	
	/*
	 * 此下方两个方法需要提取到controller层
	 * 注意!!!
	 */
	public Set<Menu> listMenuByUser(String userId){
		if(userId==null||userId.equals(""))
			return null;
		
		//获取当前用户所拥有的角色
		List<Role> roles = listRoleByUser(userId);
		if(roles==null||roles.size()<=0)
			return null;
		
		//定义栏目集合,用来获取此用户所有的栏目
		Set<Menu> menus = new HashSet<Menu>();
		//循环角色集合,依次获取栏目集合,并放入定义好的栏目集合中
		for (Role role : roles) {
			List<Menu> roleMenus = roleDao.listMenuByRole(role.getId());
			for (Menu menu : roleMenus) {
				menus.add(menu);
			}
		}
		
		return menus;
	}
	
	public Set<Power> listPowerByUser(String userId){
		if(userId==null||userId.equals(""))
			return null;
		
		//获取当前用户所拥有的角色
		List<Role> roles = listRoleByUser(userId);
		if(roles==null||roles.size()<=0)
			return null;
		
		//定义权限集合,用来获取此用户所有的权限
		Set<Power> powers = new HashSet<Power>();
		//循环角色集合,依次获取权限集合,并放入定义好的权限集合中
		for (Role role : roles) {
			List<Power> rolePowers = roleDao.listPowerByRole(role.getId());
			for (Power power : rolePowers) {
				powers.add(power);
			}
		}
		return powers;
	}
}
