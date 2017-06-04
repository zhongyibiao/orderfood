package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.util.List;

public class PartnerInfo implements Serializable {
	
	private String partnerId;

	private String partnerName;

	private String businessLicenceNo;

	private String operatorName;

	private String operatorIdNum;

	private String partnerTel;

	private String partnerMobile;

	private String partnerAddr;
	
	private String partnerRegion;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	private List<DinnerRoom> dinnerRooms;

	private static final long serialVersionUID = 1L;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId == null ? null : partnerId.trim();
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName == null ? null : partnerName.trim();
	}

	public String getBusinessLicenceNo() {
		return businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo == null ? null : businessLicenceNo.trim();
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}

	public String getOperatorIdNum() {
		return operatorIdNum;
	}

	public void setOperatorIdNum(String operatorIdNum) {
		this.operatorIdNum = operatorIdNum == null ? null : operatorIdNum.trim();
	}

	public String getPartnerTel() {
		return partnerTel;
	}

	public void setPartnerTel(String partnerTel) {
		this.partnerTel = partnerTel == null ? null : partnerTel.trim();
	}

	public String getPartnerMobile() {
		return partnerMobile;
	}

	public void setPartnerMobile(String partnerMobile) {
		this.partnerMobile = partnerMobile == null ? null : partnerMobile.trim();
	}

	public String getPartnerAddr() {
		return partnerAddr;
	}

	public void setPartnerAddr(String partnerAddr) {
		this.partnerAddr = partnerAddr == null ? null : partnerAddr.trim();
	}
	
	public String getPartnerRegion() {
		return partnerRegion;
	}

	public void setPartnerRegion(String partnerRegion) {
		this.partnerRegion = partnerRegion;
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

	public List<DinnerRoom> getDinnerRooms() {
		return dinnerRooms;
	}

	public void setDinnerRooms(List<DinnerRoom> dinnerRooms) {
		this.dinnerRooms = dinnerRooms;
	}
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", partnerId=").append(partnerId);
		sb.append(", partnerName=").append(partnerName);
		sb.append(", businessLicenceNo=").append(businessLicenceNo);
		sb.append(", operatorName=").append(operatorName);
		sb.append(", operatorIdNum=").append(operatorIdNum);
		sb.append(", partnerTel=").append(partnerTel);
		sb.append(", partnerMobile=").append(partnerMobile);
		sb.append(", partnerAddr=").append(partnerAddr);
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