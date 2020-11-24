package com.dy.memory.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dy.memory.entity.UserDto;
import com.dy.memory.service.LoginService;
import com.dy.memory.util.ServiceException;
import com.google.code.kaptcha.servlet.KaptchaExtend;

@Controller
public class LoginController extends KaptchaExtend {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public String login(HttpSession session, @Valid UserDto userDto, BindingResult result, HttpServletRequest request) {
		String verifyCode = request.getParameter("verifyCode");
		if (!StringUtils.equals(verifyCode, getGeneratedKey(request))) {
			result.rejectValue("verifyCode", "login.verifycode.error", "验证码错误");
			return "login";
		}

		if (result.hasErrors()) {
			return "login";
		}

		try {
			loginService.login(session, userDto.getAccount(), userDto.getPassword(), userDto.getVcode());
		} catch (ServiceException e) {
			result.rejectValue("account", e.getMessage(), "账号或密码错误");
			return "login";
		}

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}

	@GetMapping("/verifycode")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.captcha(request, response);
	}
}
