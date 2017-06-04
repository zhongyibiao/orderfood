package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;

public class DinnerDesk implements Serializable {

	private String dinnerDeskId;

	private String dinnerDeskName;

	private String dinnerDeskPosition;

	private String dinnerDeskType;

	private String dinnerDeskTypeDes;

	private Integer dinnerDeskManQty;

	private String dinnerRoomId;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	private static final long serialVersionUID = 1L;

	public String getDinnerDeskId() {
		return dinnerDeskId;
	}

	public void setDinnerDeskId(String dinnerDeskId) {
		this.dinnerDeskId = dinnerDeskId == null ? null : dinnerDeskId.trim();
	}

	public String getDinnerDeskName() {
		return dinnerDeskName;
	}

	public void setDinnerDeskName(String dinnerDeskName) {
		this.dinnerDeskName = dinnerDeskName == null ? null : dinnerDeskName.trim();
	}

	public String getDinnerDeskPosition() {
		return dinnerDeskPosition;
	}

	public void setDinnerDeskPosition(String dinnerDeskPosition) {
		this.dinnerDeskPosition = dinnerDeskPosition == null ? null : dinnerDeskPosition.trim();
	}

	public String getDinnerDeskType() {
		return dinnerDeskType;
	}

	public void setDinnerDeskType(String dinnerDeskType) {
		this.dinnerDeskType = dinnerDeskType == null ? null : dinnerDeskType.trim();
	}

	public String getDinnerDeskTypeDes() {
		return dinnerDeskTypeDes;
	}

	public void setDinnerDeskTypeDes(String dinnerDeskTypeDes) {
		this.dinnerDeskTypeDes = dinnerDeskTypeDes == null ? null : dinnerDeskTypeDes.trim();
	}

	public Integer getDinnerDeskManQty() {
		return dinnerDeskManQty;
	}

	public void setDinnerDeskManQty(Integer dinnerDeskManQty) {
		this.dinnerDeskManQty = dinnerDeskManQty;
	}

	public String getDinnerRoomId() {
		return dinnerRoomId;
	}

	public void setDinnerRoomId(String dinnerRoomId) {
		this.dinnerRoomId = dinnerRoomId == null ? null : dinnerRoomId.trim();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", dinnerDeskId=").append(dinnerDeskId);
		sb.append(", dinnerDeskName=").append(dinnerDeskName);
		sb.append(", dinnerDeskPosition=").append(dinnerDeskPosition);
		sb.append(", dinnerDeskType=").append(dinnerDeskType);
		sb.append(", dinnerDeskTypeDes=").append(dinnerDeskTypeDes);
		sb.append(", dinnerDeskManQty=").append(dinnerDeskManQty);
		sb.append(", dinnerRoomId=").append(dinnerRoomId);
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