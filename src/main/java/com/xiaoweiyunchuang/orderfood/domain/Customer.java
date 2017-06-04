package com.xiaoweiyunchuang.orderfood.domain;

import java.math.BigDecimal;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;

public class Customer extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8864154744301348493L;

	private String customerId;
	private String name;
	private String phoneNum;
	private String wechatId;
	private String birthday;
	private BigDecimal balance;
	private int integral;
	private String address;
	private String vipLevel;
	private String password;
	private String createDate;
	private String createBy;
	private String updateDate;
	private String updateBy;
	private String delFlag;
	private String validateCode;
	private String waitTime;

	public Customer() {
	}

	public Customer(String customerId, String name, String phoneNum, String wechatId, String birthday,
			BigDecimal balance, int integral, String address, String vipLevel) {
		this.customerId = customerId;
		this.name = name;
		this.phoneNum = phoneNum;
		this.wechatId = wechatId;
		this.birthday = birthday;
		this.balance = balance;
		this.integral = integral;
		this.address = address;
		this.vipLevel = vipLevel;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", phoneNum=" + phoneNum + ", wechatId="
				+ wechatId + ", birthday=" + birthday + ", balance=" + balance + ", integral=" + integral + ", address="
				+ address + ", vipLevel=" + vipLevel + "]";
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = DateUtil.now();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = DateUtil.now();
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}
	
	
}
