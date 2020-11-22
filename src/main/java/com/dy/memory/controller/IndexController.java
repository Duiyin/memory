package com.dy.memory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/forgot")
	public String forgot() {
		return "forgot";
	}
	
	@GetMapping("/addInfo")
	public String addInfo() {
		return "index_add";
	}
}
