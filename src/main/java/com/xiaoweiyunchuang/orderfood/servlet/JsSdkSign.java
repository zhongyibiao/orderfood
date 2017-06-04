package com.xiaoweiyunchuang.orderfood.servlet;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sword.wechat4j.common.Config;

import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class JsSdkSign {
	protected static Logger logger = LoggerFactory.getLogger(JsSdkSign.class);

	public static void main(String[] args) {
		
		//String url1  ="http://example.com?a=b#aef=3";
		//if (url1.lastIndexOf("#") > 0) {
		//	url1 = url1.substring(0, url1.lastIndexOf("#")).toString();
		//}
		//System.err.println("url1=" + url1);
		
		String jsapi_ticket = "jsapi_ticket";
		// 注意 URL 一定要动态获取，不能 hardcode
		String url = "http://example.com";
		if (url.lastIndexOf("#") > 0) {
			url = url.substring(0, url.lastIndexOf("#")).toString();
		}
		
		Map<String, String> ret = sign(jsapi_ticket, url);
		String appid = Config.instance().getAppid();
		ret.put("appId", appid);
		for (Map.Entry<String, String> entry : ret.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	};

	public static Map<String, String> getSign(String jsapi_ticket, String url) {
		Map<String, String> ret = sign(jsapi_ticket, url);
		String appid = Config.instance().getAppid();
		ret.put("appId", appid);

		for (Map.Entry<String, String> entry : ret.entrySet()) {
			logger.info(entry.getKey() + ", " + entry.getValue());
		}
		return ret;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
