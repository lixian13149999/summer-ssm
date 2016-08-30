package com.bcdbook.summer.system.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.RoleDao;
import com.bcdbook.summer.system.pojo.Role;


@Service("roleService")
public class RoleService extends CrudService<RoleDao,Role>{
	@Resource
	private RoleDao roleDao;
	
	/**
	 * @Description: 添加角色,栏目关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int addMenu(Map<String, String> parameter){
		return roleDao.getMenu(parameter)>0?0:roleDao.addMenu(parameter);
	}
	/**
	 * @Description: 查询角色,栏目关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int getMenu(Map<String, String> parameter){
		return roleDao.getMenu(parameter);
	}
	
	
	/**
	 * @Description: 添加角色,权限关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int addPower(Map<String, String> parameter){
		int count = roleDao.getPower(parameter);
		return count>0?0:roleDao.addPower(parameter);
	}
	/**
	 * @Description: 查询角色,权限关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int getPower(Map<String, String> parameter){
		return roleDao.getPower(parameter);
	}
}
