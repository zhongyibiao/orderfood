package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.util.List;

public class MenuType implements Serializable {

	private String menuTypeId;

	private String menuTypeName;

	private String menuTypeDesc;

	private String menuTypeImg;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	List<MenuInfo> menuInfos;

	private static final long serialVersionUID = 1L;

	public MenuType() {

	}

	public MenuType(String menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

	public String getMenuTypeId() {
		return menuTypeId;
	}

	public void setMenuTypeId(String menuTypeId) {
		this.menuTypeId = menuTypeId == null ? null : menuTypeId.trim();
	}

	public String getMenuTypeName() {
		return menuTypeName;
	}

	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName == null ? null : menuTypeName.trim();
	}

	public String getMenuTypeDesc() {
		return menuTypeDesc;
	}

	public void setMenuTypeDesc(String menuTypeDesc) {
		this.menuTypeDesc = menuTypeDesc == null ? null : menuTypeDesc.trim();
	}

	public String getMenuTypeImg() {
		return menuTypeImg;
	}

	public void setMenuTypeImg(String menuTypeImg) {
		this.menuTypeImg = menuTypeImg;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public List<MenuInfo> getMenuInfos() {
		return menuInfos;
	}

	public void setMenuInfos(List<MenuInfo> menuInfos) {
		this.menuInfos = menuInfos;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", menuTypeId=").append(menuTypeId);
		sb.append(", menuTypeName=").append(menuTypeName);
		sb.append(", menuTypeDesc=").append(menuTypeDesc);
		sb.append(", menuTypeImg=").append(menuTypeImg);
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