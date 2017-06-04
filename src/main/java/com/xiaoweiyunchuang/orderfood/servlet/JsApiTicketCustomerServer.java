package com.xiaoweiyunchuang.orderfood.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.sword.wechat4j.token.Token;
import org.sword.wechat4j.token.server.CustomerServer;

import com.xiaoweiyunchuang.orderfood.common.SpringContextUtil;

public class JsApiTicketCustomerServer extends CustomerServer {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public String find() {
		String jsApiTicket = null;

		StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil
				.getBean("stringRedisTemplate");
		jsApiTicket = stringRedisTemplate.opsForValue().get("jsapi_ticket");
		logger.info("CustomerAccessTokenServer find:" + jsApiTicket);

		// 执行数据库操作
		// String sql = "select cfgValue from cfg where cfg.cfgKey =
		// 'jsapi_ticket'";
		// jsApiTicket = DBUtil.query(sql);
		return jsApiTicket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sword.wechat4j.token.DbAccessTokenServer#save()
	 */
	@Override
	public boolean save(Token jsApiTicket) {
		StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil
				.getBean("stringRedisTemplate");
		stringRedisTemplate.opsForValue().set("jsapi_ticket", jsApiTicket.getToken());
		
		// 如果没有需要插入，如果有的就更新，假设已经有了数据库配置项
		// String sql = "update cfg set cfg.cfgValue=" + jsApiTicket.getToken()
		// +
		// " where cfg.cfgKey= 'jsapi_ticket'";
		// DBUtil.execute(sql);
		return true;
	}

}
