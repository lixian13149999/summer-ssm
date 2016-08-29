package com.bcdbook.summer.system.pojo;

import com.bcdbook.summer.common.persistence.pojo.DataEntity;

public class Addr extends DataEntity<Addr>{
	
	private String userId;//用户id外键
	private int type;//地址类型
	private int isDefault;//是否是默认地址
	private String province;//省/直辖市
	private int provinceCode;//省/直辖市编码
	private String city;//市
	private int cityCode;//市编码
	private String county;//县
	private int countyCode;//县编码
	private String infoAddr;//详细地址
	private String addressee;//收件人
	private String phone;//收件人电话
	private int sort;//排序
	
	public static final Integer SIGN_UP_ADDR = 1;//注册地址
	public static final Integer DELIVERY_ADDR = 2;//收货地址

	public static final Integer DEFAULT = 1;//默认
	public static final Integer UNDEFAULT = 2;//非默认
	
	
	public Addr() {
		super();
	}

	public Addr(String userId, int type, int isDefault, String province,
			int provinceCode, String city, int cityCode, String county,
			int countyCode, String infoAddr, String addressee, String phone,
			int sort) {
		super();
		this.userId = userId;
		this.type = type;
		this.isDefault = isDefault;
		this.province = province;
		this.provinceCode = provinceCode;
		this.city = city;
		this.cityCode = cityCode;
		this.county = county;
		this.countyCode = countyCode;
		this.infoAddr = infoAddr;
		this.addressee = addressee;
		this.phone = phone;
		this.sort = sort;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public int getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(int countyCode) {
		this.countyCode = countyCode;
	}

	public String getInfoAddr() {
		return infoAddr;
	}

	public void setInfoAddr(String infoAddr) {
		this.infoAddr = infoAddr;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}
