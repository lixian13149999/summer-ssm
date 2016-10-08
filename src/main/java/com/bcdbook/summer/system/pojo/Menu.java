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
	
	private Integer place;//栏目位置,前台栏目/后台栏目
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
	/**
	 * @Description: 全参构造
	 * @param @param place
	 * @param @param parentId
	 * @param @param name
	 * @param @param description
	 * @param @param href
	 * @param @param target
	 * @param @param icon
	 * @param @param sort
	 * @param @param permission
	 * @param @param isShow
	 * @param @param childs
	 * @param @param powers   
	 * @return Menu  
	 * @throws
	 * @author lason
	 * @date 2016年8月31日
	 */
	public Menu(Integer place, String parentId, String name,
			String description, String href, String target, String icon,
			Integer sort, String permission, Integer isShow, List<Menu> childs,
			List<Power> powers) {
		super();
		this.place = place;
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
		this.powers = powers;
	}


	/**
	 * @Description: 没有引入对象的全参构造
	 * @param @param place
	 * @param @param parentId
	 * @param @param name
	 * @param @param description
	 * @param @param href
	 * @param @param target
	 * @param @param icon
	 * @param @param sort
	 * @param @param permission
	 * @param @param isShow   
	 * @return Menu  
	 * @throws
	 * @author lason
	 * @date 2016年8月31日
	 */
	public Menu(Integer place, String parentId, String name,
			String description, String href, String target, String icon,
			Integer sort, String permission, Integer isShow) {
		super();
		this.place = place;
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
	public Integer getPlace() {
		return place;
	}
	public void setPlace(Integer place) {
		this.place = place;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childs == null) ? 0 : childs.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((powers == null) ? 0 : powers.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (childs == null) {
			if (other.childs != null)
				return false;
		} else if (!childs.equals(other.childs))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (isShow == null) {
			if (other.isShow != null)
				return false;
		} else if (!isShow.equals(other.isShow))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (powers == null) {
			if (other.powers != null)
				return false;
		} else if (!powers.equals(other.powers))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu ["
				+ (parentId != null ? "parentId=" + parentId + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description + ", "
						: "") + (href != null ? "href=" + href + ", " : "")
				+ (target != null ? "target=" + target + ", " : "")
				+ (icon != null ? "icon=" + icon + ", " : "")
				+ (sort != null ? "sort=" + sort + ", " : "")
				+ (permission != null ? "permission=" + permission + ", " : "")
				+ (isShow != null ? "isShow=" + isShow + ", " : "")
				+ (childs != null ? "childs=" + childs + ", " : "")
				+ (powers != null ? "powers=" + powers : "") + "]";
	}
	
}
