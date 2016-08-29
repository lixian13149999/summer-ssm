package com.bcdbook.summer.common.persistence.pojo;

import java.io.Serializable;

import com.bcdbook.summer.common.persistence.Page;

/**
 * @Description: 创建基础的实体类,并实现可序列化接口
 * 这样其子类中就不需要重复的添加这些基础的属性
 * 同时也不用再重复的实现可序列化接口
 * @author lason
 * @param <T>
 * @date 2016年8月23日
 */
public abstract class BaseEntity<T> implements Serializable{

	/**
	 * @Fields serialVersionUID : 类的唯一id生成
	 */
	private static final long serialVersionUID = 7776824160890078311L;
	
	
	private String id;//表中的id字段
	private int delFlag;//表中表示删除的字段,用于伪删除操作
	private String remark;//备注信息
	private Page<T> page;//分页对象
	
	/**
	 * 空参构造
	 */
	public BaseEntity() {
		super();
	}
	/**
	 * 全参构造
	 */
	public BaseEntity(String id, int delFlag, String remark, Page<T> page) {
		super();
		this.id = id;
		this.delFlag = delFlag;
		this.remark = remark;
		this.page = page;
	}
	
	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	


	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	

	/**
	 * 
	 * @Description: 
	 * getter and setter
	 * @author lason
	 * @date 2016年8月23日
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Page<T> getPage() {
		return page;
	}
	public void setPage(Page<T> page) {
		this.page = page;
	}
}
