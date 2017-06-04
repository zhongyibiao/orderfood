package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.util.List;

public class MenuInfo implements Serializable {

	private String menuId;

	private String menuName;

	private String menuTypeId;

	private String mainIngredient;

	private String accessories;

	private String tastePoint;

	private String dinnerRoomId;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	private String menuImg;
	
	
	private List<MenuConf> menuConfs;

	private static final long serialVersionUID = 1L;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId == null ? null : menuId.trim();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuTypeId() {
		return menuTypeId;
	}

	public void setMenuTypeId(String menuTypeId) {
		this.menuTypeId = menuTypeId == null ? null : menuTypeId.trim();
	}

	public String getMainIngredient() {
		return mainIngredient;
	}

	public void setMainIngredient(String mainIngredient) {
		this.mainIngredient = mainIngredient == null ? null : mainIngredient.trim();
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories == null ? null : accessories.trim();
	}

	public String getTastePoint() {
		return tastePoint;
	}

	public void setTastePoint(String tastePoint) {
		this.tastePoint = tastePoint == null ? null : tastePoint.trim();
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public List<MenuConf> getMenuConfs() {
		return menuConfs;
	}

	public void setMenuConfs(List<MenuConf> menuConfs) {
		this.menuConfs = menuConfs;
	}

	
	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", menuId=").append(menuId);
		sb.append(", menuName=").append(menuName);
		sb.append(", menuTypeId=").append(menuTypeId);
		sb.append(", mainIngredient=").append(mainIngredient);
		sb.append(", accessories=").append(accessories);
		sb.append(", tastePoint=").append(tastePoint);
		sb.append(", dinnerRoomId=").append(dinnerRoomId);
		sb.append(", createBy=").append(createBy);
		sb.append(", createDate=").append(createDate);
		sb.append(", updateBy=").append(updateBy);
		sb.append(", updateDate=").append(updateDate);
		sb.append(", delFlag=").append(delFlag);
		sb.append(", menuImg=").append(menuImg);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}