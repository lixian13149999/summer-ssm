package com.bcdbook.summer.system.pojo;

import java.util.List;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

/**
 * 
 * @Description: 角色实体类
 * @author lason
 * @date 2016年8月29日
 */
public class Role extends DataEntity<Role> {
	
	private static final long serialVersionUID = -7452239954247484243L;
	
	private String name;//角色名称
	private String enname;//角色的英文名称
	private int sort;
	
	private List<Menu> menus;//角色所拥有的栏目
	private List<Power> powers;//角色所拥有的权限
	
	public Role() {
		super();
	}
	public Role(String name, String enname, int sort) {
		super();
		this.name = name;
		this.enname = enname;
		this.sort = sort;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public List<Power> getPowers() {
		return powers;
	}
	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}
	@Override
	public String toString() {
		return "Role [" + (name != null ? "name=" + name + ", " : "")
				+ (enname != null ? "enname=" + enname + ", " : "") + "sort="
				+ sort + ", " + (menus != null ? "menus=" + menus + ", " : "")
				+ (powers != null ? "powers=" + powers : "") + "]";
	}
	
}
