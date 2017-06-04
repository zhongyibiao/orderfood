package com.xiaoweiyunchuang.orderfood.servlet;

import org.sword.wechat4j.token.TokenProxy;

public class AccessTokenSdk {

	/**
	 * 将此方法得到的access_token
	 * 
	 * @return
	 */
	public String accessToken() {
		return TokenProxy.accessToken();
	}
}
