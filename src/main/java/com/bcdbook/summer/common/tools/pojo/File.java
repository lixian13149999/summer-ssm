package com.bcdbook.summer.common.tools.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class File extends DataEntity<File> {

	private static final long serialVersionUID = -678623841650994144L;

	private Integer sort;//顺序
	private String sourceName;//原名
	private String name;//现在名
	private String src;//路径地址
	private Integer size;//文件大小
	private Integer type;//文件类型
	private String suffix;//文件后缀
	private String pojoId;//文件所属类的id
	private Integer pojoType;//在所属类中的类型
	
	public File() {
		super();
	}
	public File(Integer sort, String sourceName, String name, String src,
			Integer size, Integer type, String suffix, String pojoId,
			Integer pojoType) {
		super();
		this.sort = sort;
		this.sourceName = sourceName;
		this.name = name;
		this.src = src;
		this.size = size;
		this.type = type;
		this.suffix = suffix;
		this.pojoId = pojoId;
		this.pojoType = pojoType;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getPojoId() {
		return pojoId;
	}
	public void setPojoId(String pojoId) {
		this.pojoId = pojoId;
	}
	public Integer getPojoType() {
		return pojoType;
	}
	public void setPojoType(Integer pojoType) {
		this.pojoType = pojoType;
	}
	@Override
	public String toString() {
		return "File [sort=" + sort + ", sourceName=" + sourceName + ", name="
				+ name + ", src=" + src + ", size=" + size + ", type=" + type
				+ ", suffix=" + suffix + ", pojoId=" + pojoId + ", pojoType="
				+ pojoType + "]";
	}
	
}
