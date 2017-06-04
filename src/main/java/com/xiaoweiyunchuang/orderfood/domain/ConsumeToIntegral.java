package com.xiaoweiyunchuang.orderfood.domain;

public class ConsumeToIntegral implements IntegralCalculate {

	@Override
	public int calculate(int tradeValue) {
		return tradeValue*5;
	}

}
