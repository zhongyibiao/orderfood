package com.xiaoweiyunchuang.orderfood.domain;

public class IntegralConvert {
	
	private IntegralCalculate integralCalculate;
	
	public void setIntegralCalculate(IntegralCalculate integralCalculate){
		this.integralCalculate = integralCalculate;
	}
	
	/**
	 * 计算积分
	 * @param tradeValue
	 * @return
	 */
	public int calculateIntegral(int tradeValue){
		return integralCalculate.calculate(tradeValue);
	}
}
