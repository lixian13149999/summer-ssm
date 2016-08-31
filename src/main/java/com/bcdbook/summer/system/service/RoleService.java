package com.bcdbook.summer.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.RoleDao;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
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
		if(!checkParameter(parameter, "roleId", "menuId"))
			return 0;
		
		return roleDao.getMenu(parameter)>0?0:roleDao.addMenu(parameter);
	}
	/**
	 * @Description: 删除角色,栏目关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int deleteMenu(Map<String, String> parameter){
		if(!checkParameter(parameter, "roleId", "menuId"))
			return 0;
		
		return roleDao.deleteMenu(parameter);
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
		if(!checkParameter(parameter, "roleId", "menuId"))
			return 0;
		
		return roleDao.getMenu(parameter);
	}
	/**
	 * @Description: 根据角色获取此角色拥有的栏目集合
	 * @param @param roleId
	 * @param @return   
	 * @return List<Menu>  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public List<Menu> listMenuByRole(String roleId){
		if(roleId==null||roleId.equals(""))
			return null;
		
		return roleDao.listMenuByRole(roleId);
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
		if(!checkParameter(parameter, "roleId", "powerId"))
			return 0;
		
		int count = roleDao.getPower(parameter);
		return count>0?0:roleDao.addPower(parameter);
	}
	/**
	 * @Description: 删除角色,权限关系
	 * @param @param parameter
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public int deletePower(Map<String, String> parameter){
		if(!checkParameter(parameter, "roleId", "powerId"))
			return 0;
		
		return roleDao.deletePower(parameter);
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
		if(!checkParameter(parameter, "roleId", "powerId"))
			return 0;
		
		return roleDao.getPower(parameter);
	}
	
	/*
	 * 检查参数的公用方法,如果参数合法返回true,否则返回false
	 */
	private boolean checkParameter(Map<String, String> parameter,String parm1,String parm2){
		if(parameter==null)
			return false;
		
		String p1 = parameter.get(parm1);
		String p2 = parameter.get(parm2);
		if(p1==null||p1.equals("")||p2==null||p2.equals(""))
			return false;
		
		return true;
	}
	
	/**
	 * @Description: 根据权限,获取与之对应的所有权限的集合
	 * @param @param roleId
	 * @param @return   
	 * @return List<Power>  
	 * @throws
	 * @author lason
	 * @date 2016年8月30日
	 */
	public List<Power> listPowerByRole(String roleId){
		if(roleId==null||roleId.equals(""))
			return null;
		
		return roleDao.listPowerByRole(roleId);
	}
	
}
