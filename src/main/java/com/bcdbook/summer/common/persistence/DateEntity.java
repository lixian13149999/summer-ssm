package com.bcdbook.summer.common.persistence;

import java.util.Date;

import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.common.util.IdGen;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.common.util.UserUtil;
import com.bcdbook.summer.system.pojo.User;
//import com.thinkgem.jeesite.common.utils.IdGen;
//import com.thinkgem.jeesite.modules.sys.utils.UserUtils;


/**
 * @Description: 时间相关的基础类
 * 便于通用的数据维护
 * @author lason
 * @date 2016年8月23日
 */
public class DateEntity<T> extends BaseEntity<T> {

	/**
	 * 生成唯一的类识别码
	 */
	private static final long serialVersionUID = 2738342821838117314L;

	private User createBy;	// 创建者
	private String createTime;	// 创建日期
	private User updateBy;	// 更新者
	private String updateTime;	// 更新日期
	
	public DateEntity() {
		super();
	}
	public DateEntity(User createBy, String createTime, User updateBy,
			String updateTime) {
		super();
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}
	
	@Override
	public void preInsert() {
		setId(IdGen.uuid());
		User user = UserUtil.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
		this.createTime = DateUtil.getTimeStr();
		this.updateTime = this.createTime;
	}
	
	@Override
	public void preUpdate() {
		User user = UserUtil.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
		}
		this.updateTime = DateUtil.getTimeStr();
	}
	
	public User getCreateBy() {
		return createBy;
	}
	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public User getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
