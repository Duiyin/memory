package com.dy.memory.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.dy.memory.entity.UserDto;
import com.dy.memory.service.RegisterService;

@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;

	@PostMapping("/register")
	public String register(HttpSession session, @Valid UserDto userDto, BindingResult result) {
		
		/*String verifyCode = (String) session.getAttribute("vcode");
		if(!userDto.getVcode().equals(verifyCode)){
			result.rejectValue("vcode","user.verifycode.error","激活码错误");
			return "register";
		}
		
		if (result.hasErrors()) {
			return "register";
		}*/
		
		try{
			registerService.register(session, userDto);
		}catch(ServiceException e){
			result.reject(e.getMessage());
			return "register";
		}
		return "success";
	}
}
