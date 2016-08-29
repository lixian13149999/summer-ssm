package com.bcdbook.summer.system.pojo;

import java.util.List;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

/**
 * 
     * @Title: Menu.java    
     * @Description: 栏目实体类
     * @author lason       
     * @created 2016年5月27日 下午1:23:51
 */
public class Menu extends DataEntity<Menu> {

	private static final long serialVersionUID = 1045257040316372693L;
	
	private String parentId;//父级菜单id
	private String name;//栏目名字
	private String description;//栏目的描述
	private String href;//栏目跳转地址
	private String target;// 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon;//栏目图标
	private Integer sort;// 排序
	private String permission;//权限标识
	private Integer isShow;//是否显示
	
	private List<Menu> childs;//栏目的子集
	private List<Power> powers;//栏目的权限子集
	
	public Menu() {
		super();
	}
	
	public Menu(String parentId, String name, String description, String href,
			String target, String icon, Integer sort, String permission,
			Integer isShow, List<Menu> childs) {
		super();
		this.parentId = parentId;
		this.name = name;
		this.description = description;
		this.href = href;
		this.target = target;
		this.icon = icon;
		this.sort = sort;
		this.permission = permission;
		this.isShow = isShow;
		this.childs = childs;
	}

	public Menu(String parentId, String name, String description, String href,
			String target, String icon, Integer sort, String permission,
			Integer isShow) {
		super();
		this.parentId = parentId;
		this.name = name;
		this.description = description;
		this.href = href;
		this.target = target;
		this.icon = icon;
		this.sort = sort;
		this.permission = permission;
		this.isShow = isShow;
	}

	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public List<Menu> getChilds() {
		return childs;
	}
	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
	public List<Power> getPowers() {
		return powers;
	}
	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}

	@Override
	public String toString() {
		return "Menu [parentId=" + parentId + ", name=" + name
				+ ", description=" + description + ", href=" + href
				+ ", target=" + target + ", icon=" + icon + ", sort=" + sort
				+ ", permission=" + permission + ", isShow=" + isShow
				+ ", childs=" + childs + "]";
	}
}
