package com.xiaoweiyunchuang.orderfood.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoweiyunchuang.microcloud.common.NetworkUtil;
import com.xiaoweiyunchuang.microcloud.domain.User;
import com.xiaoweiyunchuang.microcloud.service.UserService;

import io.swagger.annotations.Api;

@Api(value = "登录管理服务", description = "用户的登录、退出、注册服务")
@Controller
public class LoginController extends AbstractController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private UserService userService;

	/**
	 * Go login
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 *  Login
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, RedirectAttributes rediect, ModelMap model) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// String rememberMe = request.getParameter("rememberMe");
		String randomcode = request.getParameter("randomcode");

		String ip = null;
		Boolean validateFlag = false;

		try {
			ip = NetworkUtil.getIpAddr(request);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		String validateCodeWithAccount = stringRedisTemplate.opsForValue().get(username);
		String validateCodeWithIP = stringRedisTemplate.opsForValue().get(ip);

		logger.info(
				"validateCodeWithAccount:validateCodeWithIP ->" + validateCodeWithAccount + ":" + validateCodeWithIP);

		if (StringUtils.isNotEmpty(randomcode)) {
			if ((StringUtils.isNotEmpty(validateCodeWithAccount) && validateCodeWithAccount.equals(randomcode))
					|| (StringUtils.isNotEmpty(validateCodeWithIP) && validateCodeWithIP.equals(randomcode))) {
				validateFlag = true;
			}
		}

		if (!validateFlag) {
			rediect.addFlashAttribute("errorText", "验证码输入错误!");
			return "login";
		}

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// if (StringUtils.isNotEmpty(rememberMe) && "true".equals(rememberMe))
		// {
		// //token.setRememberMe(true);
		// }
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			User u = new User();
			u.setAccount((String) subject.getPrincipal());
			u = userService.findUserRolesAndPermissions(u);
			model.put("user", u);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
			return "login";
		}
		return "redirect:/index";
	}

	/**
	 * Register
	 *
	 * @return
	 */
	@RequestMapping("/userRegister")
	public String userRegister(ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		User u = userService.findUserByAccount((String) subject.getPrincipal());
		model.put("user", u);
		return "register";
	}

	/**
	 * Exit
	 *
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}
}
