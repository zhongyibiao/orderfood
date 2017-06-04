package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderDetailId;

	private String menuId;

	private String menuConfId;

	private String menuConfName;

	private Short menuQuantity;

	private BigDecimal menuOriginPrice;

	private BigDecimal menuPreferentialPrice;

	private String orderId;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String delFlag;

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getMenuConfId() {
		return menuConfId;
	}

	public void setMenuConfId(String menuConfId) {
		this.menuConfId = menuConfId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId == null ? null : menuId.trim();
	}

	public String getMenuConfName() {
		return menuConfName;
	}

	public void setMenuConfName(String menuConfName) {
		this.menuConfName = menuConfName;
	}

	public Short getMenuQuantity() {
		return menuQuantity;
	}

	public BigDecimal getMenuOriginPrice() {
		return menuOriginPrice;
	}

	public void setMenuOriginPrice(BigDecimal menuOriginPrice) {
		this.menuOriginPrice = menuOriginPrice;
	}

	public BigDecimal getMenuPreferentialPrice() {
		return menuPreferentialPrice;
	}

	public void setMenuPreferentialPrice(BigDecimal menuPreferentialPrice) {
		this.menuPreferentialPrice = menuPreferentialPrice;
	}

	public void setMenuQuantity(Short menuQuantity) {
		this.menuQuantity = menuQuantity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
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
		sb.append(", orderDetailId=").append(orderDetailId);
		sb.append(", menuId=").append(menuId);
		sb.append(", menuConfId=").append(menuConfId);
		sb.append(", menuConfName=").append(menuConfName);
		sb.append(", menuQuantity=").append(menuQuantity);
		sb.append(", menuOriginPrice=").append(menuOriginPrice);
		sb.append(", menuPreferentialPrice=").append(menuPreferentialPrice);
		sb.append(", orderId=").append(orderId);
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