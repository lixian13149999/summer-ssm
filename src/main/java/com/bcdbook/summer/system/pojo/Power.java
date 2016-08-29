package com.bcdbook.summer.system.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

/**
 * 
 * @Description: 权限实体类
 * @author lason
 * @date 2016年8月29日
 */
public class Power extends DataEntity<Power> {
	
	private static final long serialVersionUID = -8185823585876527112L;
	
	private String menuId;//所属栏目的id
	private String name;//权限名称
	private String description;//权限简介
	private String icon;//权限图标
	private int sort;//权限排序
	private String permission;//权限标识
	private int isShow;//是否显示
	
	public Power() {
		super();
	}
	public Power(String menuId, String name, String description, String icon,
			int sort, String permission, int isShow) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.sort = sort;
		this.permission = permission;
		this.isShow = isShow;
	}
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	
	@Override
	public String toString() {
		return "Power [menuId=" + menuId + ", name=" + name + ", description="
				+ description + ", icon=" + icon + ", sort=" + sort
				+ ", permission=" + permission + ", isShow=" + isShow + "]";
	}
	
}
