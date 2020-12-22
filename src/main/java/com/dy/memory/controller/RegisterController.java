package com.dy.memory.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.dy.memory.entity.UserDto;
import com.dy.memory.service.RegisterService;
import com.dy.memory.util.ServiceException;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping("/register")
	public String register(HttpSession session, @Valid UserDto userDto, BindingResult result) {

		String verifyCode = (String) session.getAttribute("verifyCode");
		if (!userDto.getVcode().equals(verifyCode)) {
			result.rejectValue("verifyCode", "user.verifycode.error", "激活码错误");
			return "register";
		}

		if (result.hasErrors()) {
			return "register";
		}

		try {
			registerService.register(session, userDto);
		} catch (ServiceException e) {
			result.rejectValue("register", e.getMessage(), "账号已存在");
			return "register";
		}
		return "success";
	}

	/**
	 * 密码重置
	 * 
	 * @param session
	 * @param userDto
	 * @param result
	 * @return
	 */
	@PostMapping("/reset")
	public String resetPassword(HttpSession session, @Valid UserDto userDto, BindingResult result) {

		String verifyCode = (String) session.getAttribute("verifyCode");
		if (!userDto.getVcode().equals(verifyCode)) {
			result.rejectValue("vcode", "user.verifycode.error", "激活码错误");
			return "reset_password";
		}

		if (result.hasErrors()) {
			return "reset_password";
		}

		try {
			registerService.resetPassword(session, userDto);
		} catch (ServiceException e) {
			result.rejectValue("unregistered", e.getMessage(), "账号不存在");
			return "register";
		}
		return "success";
	}
}
