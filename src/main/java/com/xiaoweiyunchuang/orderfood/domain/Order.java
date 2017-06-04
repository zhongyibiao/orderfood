package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String businessId;

	private String createTime;

	private String paymentTime;

	private String businessTime;

	private BigDecimal orderOriginPrice;

	private BigDecimal OraclePreferentialPrice;

	private String payType;

	private String deviceType;

	private String osType;

	private Short dinnerNumber;

	private String placeOrderCode;

	private String dinnerRoomId;

	private String customerId;

	private String dinnerDeskNo;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	List<OrderDetail> orderDetails;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId == null ? null : businessId.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public BigDecimal getOrderOriginPrice() {
		return orderOriginPrice;
	}

	public void setOrderOriginPrice(BigDecimal orderOriginPrice) {
		this.orderOriginPrice = orderOriginPrice;
	}

	public BigDecimal getOraclePreferentialPrice() {
		return OraclePreferentialPrice;
	}

	public void setOraclePreferentialPrice(BigDecimal oraclePreferentialPrice) {
		OraclePreferentialPrice = oraclePreferentialPrice;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getPlaceOrderCode() {
		return placeOrderCode;
	}

	public void setPlaceOrderCode(String placeOrderCode) {
		this.placeOrderCode = placeOrderCode;
	}

	public String getDinnerRoomId() {
		return dinnerRoomId;
	}

	public void setDinnerRoomId(String dinnerRoomId) {
		this.dinnerRoomId = dinnerRoomId == null ? null : dinnerRoomId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public Short getDinnerNumber() {
		return dinnerNumber;
	}

	public void setDinnerNumber(Short dinnerNumber) {
		this.dinnerNumber = dinnerNumber;
	}

	public String getDinnerDeskNo() {
		return dinnerDeskNo;
	}

	public void setDinnerDeskNo(String dinnerDeskNo) {
		this.dinnerDeskNo = dinnerDeskNo;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", orderId=").append(orderId);
		sb.append(", businessId=").append(businessId);
		sb.append(", createTime=").append(createTime);
		sb.append(", paymentTime=").append(paymentTime);
		sb.append(", businessTime=").append(businessTime);
		sb.append(", orderOriginPrice=").append(orderOriginPrice);
		sb.append(", OraclePreferentialPrice=").append(OraclePreferentialPrice);
		sb.append(", payType=").append(payType);
		sb.append(", deviceType=").append(deviceType);
		sb.append(", osType=").append(osType);
		sb.append(", osType=").append(osType);
		sb.append(", dinnerRoomId=").append(dinnerRoomId);
		sb.append(", customerId=").append(customerId);
		sb.append(", dinnerDeskNo=").append(dinnerDeskNo);
		sb.append(", dinnerNumber=").append(dinnerNumber);
		sb.append(", createBy=").append(createBy);
		sb.append(", createDate=").append(createDate);
		sb.append(", updateBy=").append(updateBy);
		sb.append(", updateDate=").append(updateDate);
		sb.append(", delFlag=").append(delFlag);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}