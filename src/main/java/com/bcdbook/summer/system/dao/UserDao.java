package com.bcdbook.summer.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.Role;
import com.bcdbook.summer.system.pojo.User;

@Repository(value="userDao")
public interface UserDao extends CrudDao<User> {
	/**
	 * @Description: 插入用户,角色关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int addRole(Map<String, String> parameter);
	/**
	 * @Description: 删除用户,角色关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int deleteRole(Map<String, String> parameter);
	/**
	 * @Description: 获取用户,角色关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int getRole(Map<String, String> parameter);
	/**
	 * @Description: 根据用户id查询出与之关联的角色对象的集合
	 * @param @param userId
	 * @param @return   
	 * @return List<Role>  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public List<Role> listRoleByUser(String userId);
	
}
