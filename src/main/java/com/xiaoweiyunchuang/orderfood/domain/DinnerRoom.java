package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DinnerRoom implements Serializable {

	private String dinnerRoomId;

	private String dinnerRoomName;

	private String dinnerRoomTel;

	private String dinnerRoomMobile;

	private String dinnerRoomProvince;

	private String dinnerRoomCity;

	private String dinnerRoomAddr;

	private Double dinnerRoomArea;

	private String partnerId;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	List<DinnerDesk> dinnerDesks;

	private static final long serialVersionUID = 1L;

	public String getDinnerRoomId() {
		return dinnerRoomId;
	}

	public void setDinnerRoomId(String dinnerRoomId) {
		this.dinnerRoomId = dinnerRoomId == null ? null : dinnerRoomId.trim();
	}

	public String getDinnerRoomName() {
		return dinnerRoomName;
	}

	public void setDinnerRoomName(String dinnerRoomName) {
		this.dinnerRoomName = dinnerRoomName == null ? null : dinnerRoomName.trim();
	}

	public String getDinnerRoomTel() {
		return dinnerRoomTel;
	}

	public void setDinnerRoomTel(String dinnerRoomTel) {
		this.dinnerRoomTel = dinnerRoomTel == null ? null : dinnerRoomTel.trim();
	}

	public String getDinnerRoomMobile() {
		return dinnerRoomMobile;
	}

	public void setDinnerRoomMobile(String dinnerRoomMobile) {
		this.dinnerRoomMobile = dinnerRoomMobile == null ? null : dinnerRoomMobile.trim();
	}

	public String getDinnerRoomProvince() {
		return dinnerRoomProvince;
	}

	public void setDinnerRoomProvince(String dinnerRoomProvince) {
		this.dinnerRoomProvince = dinnerRoomProvince == null ? null : dinnerRoomProvince.trim();
	}

	public String getDinnerRoomCity() {
		return dinnerRoomCity;
	}

	public void setDinnerRoomCity(String dinnerRoomCity) {
		this.dinnerRoomCity = dinnerRoomCity == null ? null : dinnerRoomCity.trim();
	}

	public String getDinnerRoomAddr() {
		return dinnerRoomAddr;
	}

	public void setDinnerRoomAddr(String dinnerRoomAddr) {
		this.dinnerRoomAddr = dinnerRoomAddr == null ? null : dinnerRoomAddr.trim();
	}

	public Double getDinnerRoomArea() {
		return dinnerRoomArea;
	}

	public void setDinnerRoomArea(Double dinnerRoomArea) {
		this.dinnerRoomArea = dinnerRoomArea;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId == null ? null : partnerId.trim();
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public List<DinnerDesk> getDinnerDesks() {
		return dinnerDesks;
	}

	public void setDinnerDesks(List<DinnerDesk> dinnerDesks) {
		this.dinnerDesks = dinnerDesks;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", dinnerRoomId=").append(dinnerRoomId);
		sb.append(", dinnerRoomName=").append(dinnerRoomName);
		sb.append(", dinnerRoomTel=").append(dinnerRoomTel);
		sb.append(", dinnerRoomMobile=").append(dinnerRoomMobile);
		sb.append(", dinnerRoomProvince=").append(dinnerRoomProvince);
		sb.append(", dinnerRoomCity=").append(dinnerRoomCity);
		sb.append(", dinnerRoomAddr=").append(dinnerRoomAddr);
		sb.append(", dinnerRoomArea=").append(dinnerRoomArea);
		sb.append(", partnerId=").append(partnerId);
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