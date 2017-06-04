package com.xiaoweiyunchuang.orderfood.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sword.wechat4j.jsapi.JsApiManager;
import org.sword.wechat4j.jsapi.JsApiParam;

import com.xiaoweiyunchuang.orderfood.servlet.JsSdk;
import com.xiaoweiyunchuang.orderfood.servlet.JsSdkSign;



@Controller
public class IndexController extends AbstractController {

	@RequestMapping(value = { "", "/", "index" })
	public String index(Map<String, Object> model) {
		return "index";
	}

	@RequestMapping("/header")
	public String header(Map<String, Object> model) {
		return "header";
	}

	@RequestMapping("/footer")
	public String footer(Map<String, Object> model) {
		return "footer";
	}

	@RequestMapping("/qa")
	public String qa(Map<String, Object> model) {
		return "qa";
	}

	@RequestMapping("/me")
	public String me(Map<String, Object> model) {
		return "me";
	}
	@RequestMapping("/lijidiancan")
	public String lijidiancan(Map<String, Object> model) {
		return "lijidiancan";
	}

	@RequestMapping("/weui")
	public String weui(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {
		return "weui";
	}

	@RequestMapping("/jssdk")
	public String jssdk(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {
		StringBuffer requestUrl = request.getRequestURL();
		String url = "";
		if (request.getQueryString() != null) {
			requestUrl.append("?");
			requestUrl.append(request.getQueryString());
		}
		url = requestUrl.toString();
		if (url.lastIndexOf("#") > 0) {
			url = url.substring(0, url.lastIndexOf("#"));
		}

		logger.info("url=" + url);
		Map<String, String> map = JsSdkSign.getSign(JsSdk.ticket(), url.toString());
		logger.info("map=" + map);
		model.putAll(map);
		
		JsApiParam jsApiParam = JsApiManager.signature(url);
		model.put("jsApiParam", jsApiParam);
		
		return "jssdk";
	}
}
