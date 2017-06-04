package com.xiaoweiyunchuang.orderfood.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoweiyunchuang.microcloud.domain.User;
import com.xiaoweiyunchuang.microcloud.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "PAD登录管理服务", description = "PAD端用户的登录、退出服务")
@RestController
public class PadLoginController extends AbstractController {

	@Autowired
	private UserService userService;

	/**
	 * Go login
	 * 
	 * @return
	 */
	@ApiOperation("跳转到登录页")
	@RequestMapping(value = "/padLogin", method = RequestMethod.GET)
	public String padLogin() {
		return "padlogin";
	}

	/**
	 * Login
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation("登录")
	@RequestMapping(value = "/padLogin/{username}/{password}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> login(@ApiParam("用户名") @PathVariable String username,
			@ApiParam("密码") @PathVariable String password) {

		Map<String, String> resultMap = new HashMap<String, String>();

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();

		try {

			subject.login(token);
			User user = userService.findUserByAccount((String) subject.getPrincipal());
			
			resultMap.put("errcode", "0");
			resultMap.put("errmsg", "ok");
			resultMap.put("account", user.getAccount());
			resultMap.put("name", user.getName());

		} catch (AuthenticationException e) {
			logger.info(e.getMessage(), e);
			resultMap.put("errcode", "1");
			resultMap.put("errmsg", "您的账号或密码输入错误!");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

	/**
	 * Exit
	 *
	 * @return
	 */
	@ApiOperation("退出")
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> logout() {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();

			resultMap.put("errcode", "0");
			resultMap.put("errmsg", "ok");
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			resultMap.put("errcode", "1");
			resultMap.put("errmsg", "您的账号或密码输入错误!");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
}
