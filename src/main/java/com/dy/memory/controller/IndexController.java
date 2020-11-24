package com.dy.memory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/reset")
	public String forgot() {
		return "reset_password";
	}

	@GetMapping("/diary/add")
	public String addInfo() {
		return "index_diary_add";
	}
}