package com.xiaoweiyunchuang.orderfood.domain;

public class RechargeToIntegral implements IntegralCalculate {

	@Override
	public int calculate(int tradeValue) {
		return tradeValue*10;
	}
}
