package com.xiaoweiyunchuang.orderfood.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.sword.wechat4j.token.TokenListener;

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		logger.info("TokenListener start.....................");
		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
		servletListenerRegistrationBean.setListener(new TokenListener());
		return servletListenerRegistrationBean;
	}
}
