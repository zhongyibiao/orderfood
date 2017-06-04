package com.xiaoweiyunchuang.orderfood.domain;

public class SMSBean {
	private String phoneNum;
	private int code;
	private long createTime;
	public static final long WAIT_TIME = 1000*60*5;
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public boolean checkCode(int code, long checkTime){
		if(this.code == code && (checkTime - createTime) < WAIT_TIME){
			return true;
		}
		return false;
	}
}
