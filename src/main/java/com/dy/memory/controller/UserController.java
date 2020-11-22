package com.dy.memory.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.dy.memory.entity.UserDto;
import com.dy.memory.service.UserService;
import com.dy.memory.util.ServiceException;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/passwordReset")
	public String passwordReset(HttpSession session, @Valid UserDto userDto, BindingResult result) {

		String verifyCode = (String) session.getAttribute("vcode");
		if (!userDto.getVcode().equals(verifyCode)) {
			result.rejectValue("vcode", "user.verifycode.error", "激活码错误");
			return "register";
		}

		if (result.hasErrors()) {
			return "forgot";
		}

		try {
			userService.passwordReset(session, userDto);
		} catch (ServiceException e) {
			result.rejectValue("unregistered", e.getMessage(), "账号不存在");
			return "register";
		}
		return "success";
	}
}
