package com.bcdbook.summer.system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.util.MD5Util;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.dao.RoleDao;
import com.bcdbook.summer.system.dao.UserDao;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.pojo.Role;
import com.bcdbook.summer.system.pojo.User;

@Service("userService")
public class UserService extends CrudService<UserDao,User>{
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	
	/**
	 * @Description: 注册的service层处理
	 * @param @param user
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月14日
	 */
//	@Transactional
	//为了避免注册的时候出现用户名冲突的问题(多线程并发,这里注册时锁定线程)
	public synchronized User signup(User user){
		//如果传入的User对象为空,或者username为空或者password为空,直接返回false
		if(user==null||StringUtils.isNull(user.getUserName())||StringUtils.isNull(user.getPwd()))
			return null;
		
		//创建一个User对象,用于封装检测条件
		User checkUser = new User();
		//把用户名封装到用于检测的User对象中
		checkUser.setUserName(user.getUserName());
		//通过设定的条件获取User对象
		User dbUser = getByCondition(checkUser);
		
		//如果用户存在,说明此用户名已经被占用
		if(dbUser!=null)
			return null;
		
		user.setPwd(MD5Util.getMD5Code(user.getPwd()));
		user.setPhoneState(User.UNBOUND);//设置手机为未绑定状态
		user.setEmailState(User.UNBOUND);//设置邮箱为未绑定状态
		user.setWechatState(User.UNBOUND);//设置微信为未绑定状态
		user.setIsLock(User.LOCK);//设置用户为锁定状态
		
		String userId = addBackId(user);
		
		if(userId==null)
			return null;
					
		User backUser = get(userId);
		
		return backUser;
	}

	/**
	 * @Description: 用户登录的方法
	 * @param @param user
	 * @param @return   
	 * @return User  
	 * @throws
	 * @author lason
	 * @date 2016年9月14日
	 */
	public User signin(User user) {
		//如果传入的User为空,或者用户名为空,或者密码为空,则直接返回空
		if(user==null||StringUtils.isNull(user.getUserName())||StringUtils.isNull(user.getPwd()))
			return null;
		
		//对传入的密码进行md5加密处理,使用加密后的值和后台对比
		user.setPwd(MD5Util.getMD5Code(user.getPwd()));
		
		//为避免出现查出多条记录的错误,这里直接查一个user集合
		List<User> userList = findList(user);
		//如果符合条件的User的数量不为1,则说明登陆数据出错了,返回null
		if(userList.size()!=1)
			return null;
		
		return userList.get(0);
	}
	
	
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
