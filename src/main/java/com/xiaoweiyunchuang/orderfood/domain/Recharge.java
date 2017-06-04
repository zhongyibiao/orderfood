package com.xiaoweiyunchuang.orderfood.domain;

import java.math.BigDecimal;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;

public class Recharge extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1704792008528654297L;
	/**充值*/
	public static final int RECHARGE = 0;
	/**现金消费*/
	public static final int CONSUME = 1;
	/**积分消费*/
	public static final int INTERGRAL = 2;
	
	/**余额支付*/
	public static final int BALANCE = 0;
	/**微信支付*/
	public static final int WECHAT = 1;
	/**现金支付*/
	public static final int CASH = 2;
	
	/**充值积分换算*/
	public static final int RECHARGE_TO_INTEGRAL = 10;
	/**消费积分换算*/
	public static final int CONSUME_TO_INTEGRAL = 5;
	
	private String rechargeId;
	private String rechargeTime;
	private BigDecimal rechargeAmount;
	private int rechargeIntegral;
	private String customerId;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	private String startTime;
	private String endTime;
	/**交易类型*/
	private int rechargeType;
	/**支付类型*/
	private int payType;
	private String delFlag;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}

	public String getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(String rechargeTime) {
		this.rechargeTime = DateUtil.now();
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public int getRechargeIntegral() {
		return rechargeIntegral;
	}

	public void setRechargeIntegral(int rechargeIntegral) {
		this.rechargeIntegral = rechargeIntegral;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = DateUtil.now();
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = DateUtil.now();
	}

	public int getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
