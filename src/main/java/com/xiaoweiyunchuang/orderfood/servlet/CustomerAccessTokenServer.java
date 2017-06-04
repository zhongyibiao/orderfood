/**
 * 
 */
package com.xiaoweiyunchuang.orderfood.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.sword.wechat4j.token.Token;
import org.sword.wechat4j.token.server.CustomerServer;

import com.xiaoweiyunchuang.orderfood.common.SpringContextUtil;

/**
 * @author repo_user
 * @date 2015年1月12日
 */
public class CustomerAccessTokenServer extends CustomerServer {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sword.wechat4j.token.DbAccessTokenServer#find()
	 */
	@Override
	public String find() {
		String accessToken = null;
		StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil
				.getBean("stringRedisTemplate");
		accessToken = stringRedisTemplate.opsForValue().get("access_token");
		logger.info("CustomerAccessTokenServer find:" + accessToken);

		// 执行数据库操作
		// String sql = "select cfgValue from cfg where cfg.cfgKey =
		// 'access_token'";
		// accessToken = DBUtil.query(sql);
		return accessToken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sword.wechat4j.token.DbAccessTokenServer#save()
	 */
	@Override
	public boolean save(Token accessToken) {
		logger.info("CustomerAccessTokenServer save:" + accessToken.getToken());

		StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil
				.getBean("stringRedisTemplate");
		stringRedisTemplate.opsForValue().set("access_token", accessToken.getToken());

		// 如果没有需要插入，如果有的就更新，假设已经有了数据库配置项
		// String sql = "update cfg set cfg.cfgValue=" + accessToken.getToken()
		// +
		// " where cfg.cfgKey= 'access_token'";
		// DBUtil.execute(sql);
		return true;
	}
}
