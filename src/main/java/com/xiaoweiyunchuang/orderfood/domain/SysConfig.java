package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.util.Date;

public class SysConfig implements Serializable {
	private String cfgId;

	private String cfgName;

	private String cfgCode;

	private String cfgValue;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	private static final long serialVersionUID = 1L;

	public String getCfgId() {
		return cfgId;
	}

	public void setCfgId(String cfgId) {
		this.cfgId = cfgId == null ? null : cfgId.trim();
	}

	public String getCfgName() {
		return cfgName;
	}

	public void setCfgName(String cfgName) {
		this.cfgName = cfgName == null ? null : cfgName.trim();
	}

	public String getCfgCode() {
		return cfgCode;
	}

	public void setCfgCode(String cfgCode) {
		this.cfgCode = cfgCode == null ? null : cfgCode.trim();
	}

	public String getCfgValue() {
		return cfgValue;
	}

	public void setCfgValue(String cfgValue) {
		this.cfgValue = cfgValue == null ? null : cfgValue.trim();
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
		sb.append(", cfgId=").append(cfgId);
		sb.append(", cfgName=").append(cfgName);
		sb.append(", cfgCode=").append(cfgCode);
		sb.append(", cfgValue=").append(cfgValue);
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