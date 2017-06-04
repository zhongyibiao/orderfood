package com.xiaoweiyunchuang.orderfood.domain;

public class IntegralToIntegral implements IntegralCalculate {

	@Override
	public int calculate(int tradeValue) {
		return -tradeValue;
	}

}
