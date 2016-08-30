package com.bcdbook.summer.system.dao;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.Role;

@Repository(value="roleDao")
public interface RoleDao extends CrudDao<Role> {
	/**
	 * @Description: 插入角色,栏目关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int addMenu(Map<String, String> parameter);
	
	/**
	 * @Description: 获取角色,栏目关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int getMenu(Map<String, String> parameter);
	
	/**
	 * @Description: 添加角色,权限关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int addPower(Map<String, String> parameter);
	
	/**
	 * @Description: 获取角色,权限关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int getPower(Map<String, String> parameter);
}
