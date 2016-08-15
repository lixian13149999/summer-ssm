package com.bcdbook.summer.system.pojo;

/**
 * 
     * @Title: Menu.java    
     * @Description: 栏目实体类
     * @author lason       
     * @created 2016年5月27日 下午1:23:51
 */
public class Menu {
	private int id;//栏目的id
	private String name;//栏目名字
	private String description;//栏目的描述
	
	public Menu() {
		super();
	}
	public Menu(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
