package com.bcdbook.summer.system.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class User extends DataEntity<User> {
	
	private static final long serialVersionUID = 3129813923604395110L;
	
	//	private int id;// 用户id
	private String userName;// 用户名
	private String pwd;// 密码
	private int age;// 年龄
	private int gender;//性别
	private String phone;// 电话
	private String email;// 邮箱
	private String openId;// 用于微信绑定的openid
	private String addr;// 地址
	private int company;// 所在公司
	private int department;// 所在部门
	private int position;// 职位
	private int userType;// 用户状态,1:新注册用户,2:邮箱未验证,3:电话未验证,4:微信未认证
	private int isLock;// 用户的可使用状态,1:正常,2:锁定

	// 空参构造
	public User() {
		super();
	}
	
	public User(String userName, String pwd, int age, int gender, String phone,
			String email, String openId, String addr, int company,
			int department, int position, int userType, int isLock) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.openId = openId;
		this.addr = addr;
		this.company = company;
		this.department = department;
		this.position = position;
		this.userType = userType;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getIsLock() {
		return isLock;
	}
	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", pwd=" + pwd + ", age=" + age
				+ ", gender=" + gender + ", phone=" + phone + ", email="
				+ email + ", openId=" + openId + ", addr=" + addr
				+ ", company=" + company + ", department=" + department
				+ ", position=" + position + ", userType=" + userType
				+ ", isLock=" + isLock + "]";
	}
	
	
}
