package com.bcdbook.summer.system.pojo;

import java.util.List;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class User extends DataEntity<User> {
	
	private static final long serialVersionUID = 3129813923604395110L;
	
	private String userName;// 用户名
	private String pwd;// 密码
	private int age;// 年龄
	private int gender;//性别
	private String phone;// 电话
	private int phoneState;//电话的绑定状态,,1:绑定,2:未绑定
	private String email;// 邮箱
	private int emailState;//邮箱的绑定状态,1:绑定,2:未绑定
	private String openId;// 用于微信绑定的openid
	private int wechatState;//微信的绑定状态,1:绑定,2:未绑定
	private int company;// 所在公司
	private int department;// 所在部门
	private int position;// 职位
	private int isLock;// 用户的可使用状态,1:正常,2:锁定
	
	private List<Role> roles;//用户所拥有的角色

	/**
	 * 男/女==>gender
	 */
	public static final Integer MALE = 1;
	public static final Integer FEMALE = 2;
	
	/**
	 * 绑定/未绑定==>XX_state
	 */
	public static final Integer BOUND = 1;
	public static final Integer UNBOUND = 2;
	
	/**
	 * 锁定/未锁定==>is_lock
	 */
	public static final Integer LOCK = 1;
	public static final Integer UNLOCK = 2;
	
	// 空参构造
	public User() {
		super();
	}

	public User(String userName, String pwd, int age, int gender, String phone,
			int phoneState, String email, int emailState, String openId,
			int wechatState, int company, int department,
			int position, int isLock) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.phoneState = phoneState;
		this.email = email;
		this.emailState = emailState;
		this.openId = openId;
		this.wechatState = wechatState;
		this.company = company;
		this.department = department;
		this.position = position;
		this.isLock = isLock;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPhoneState() {
		return phoneState;
	}

	public void setPhoneState(int phoneState) {
		this.phoneState = phoneState;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmailState() {
		return emailState;
	}

	public void setEmailState(int emailState) {
		this.emailState = emailState;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getWechatState() {
		return wechatState;
	}

	public void setWechatState(int wechatState) {
		this.wechatState = wechatState;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", pwd=" + pwd + ", age=" + age
				+ ", gender=" + gender + ", phone=" + phone + ", phoneState="
				+ phoneState + ", email=" + email + ", emailState="
				+ emailState + ", openId=" + openId + ", wechatState="
				+ wechatState + ", company=" + company
				+ ", department=" + department + ", position=" + position
				+ ", isLock=" + isLock + "]";
	}
	
	
}
