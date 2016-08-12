package com.bcdbook.summer.system.user.service;

import java.util.List;

import com.bcdbook.summer.system.user.pojo.User;

public interface UserService {
	/**
	 * 
	    * @Discription 根据用户id获取用户对象的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:20:21     
	    * @param userId
	    * @return
	 */
	public User getUserById(int userId);
	
	/**
	 * 
	    * @Discription 批量添加user的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:20:57     
	    * @param users
	 */
	public boolean addUsers(List<User> users);
	
	/**
	 * 
	    * @Discription 添加用户的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:21:37     
	    * @param user
	    * @return
	 */
	public boolean addUser(User user);
	
	/**
	 * 
	    * @Discription 更新用户信息的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:22:00     
	    * @param user
	    * @return
	 */
	public boolean updateUser(User user);
	
	/**
	 * 
	    * @Discription 删除用户的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:36:17     
	    * @param user
	    * @return
	 */
	public boolean deleteUser(User user);
	
	/**
	 * 
	    * @Discription 绑定微信的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:32:06     
	    * @param username
	    * @param pwd
	    * @param openId
	    * @return
	 */
	public String bindingWechat(String username,String pwd,String openId);
	
	/**
	 * 
	    * @Discription 绑定邮箱的方法
	    * @author lason       
	    * @created 2016年6月5日 下午4:39:06     
	    * @param username
	    * @param pwd
	    * @param email
	    * @return
	 */
	public String bindingEmail(String username, String pwd, String email);
}
