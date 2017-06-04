package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinanceReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String startTime;

	private String endTime;

	private BigDecimal salesAmount;

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

	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	@Override
	public String toString() {
		return "FinanceReport [startTime=" + startTime + ", endTime=" + endTime + ", salesAmount=" + salesAmount + "]";
	}

}
