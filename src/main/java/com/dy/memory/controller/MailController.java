package com.dy.memory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dy.memory.service.MailService;
import com.dy.memory.util.MailUtil;

@Controller
public class MailController {

	@Autowired
	private MailService mailService;

	/*
	 * 验证码发送
	 * 
	 */
	@ResponseBody
	@PostMapping("/email/verify")
	public String verifyEmail(HttpSession session, String email) {
		if (!MailUtil.isEmail(email)) {
			return "邮箱格式有误";
		}
		String vcode = MailUtil.getRandom();
		session.setAttribute("vcode", vcode);
		String fromName = "Memory";
		String subject = "激活码邮件";
		String htmlbody = "感谢您的使用，激活码：" + vcode;
		mailService.send(email, email, fromName, subject, htmlbody);
		return "激活码发送成功";
	}
}