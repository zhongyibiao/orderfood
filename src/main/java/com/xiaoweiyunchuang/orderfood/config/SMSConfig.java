package com.xiaoweiyunchuang.orderfood.config;

public class SMSConfig {
	/**
	 * url前半部分
	 */
	public static final String BASE_URL = "http://gw.api.taobao.com/router/rest";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String API_Key = "23517419";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String SECRET = "93dd864cf4b4ff9e1e73ae17fb9d8095";

	/**
	 * 响应数据类型, JSON或XML
	 */
	public static final String RESP_DATA_TYPE = "json";
	
	/**
	 * 	短信签名
	 */
	public static final String SIGN_NAME = "小微云创";
	/**
	 * 模板ID
	 */
	public static final String MODEL_ID = "SMS_25530003";
}
