package com.xiaoweiyunchuang;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xiaoweiyunchuang.orderfood.common.SpringContextUtil;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan(basePackages = { "com.xiaoweiyunchuang.microcloud", "com.xiaoweiyunchuang.orderfood" })
@MapperScan(basePackages = { "com.xiaoweiyunchuang.microcloud.mapper", "com.xiaoweiyunchuang.orderfood.mapper" })
public class OrderFoodApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(OrderFoodApplication.class, args);
		//SpringContextUtil.setApplicationContext(applicationContext);
	}

	@Override
	public void run(String... args) throws Exception {
		

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OrderFoodApplication.class);
	}
}
