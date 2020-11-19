package com.dy.memory.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {
	
	@NotEmpty(message = "邮箱不能为空")
	@Email(message = "请正确书写Email格式")
	private String account;
	
	@NotEmpty(message = "密码不能为空")
	@Size(min = 6, max = 16, message = "密码长度有误,请输入6-16位密码")
	private String password;
	
	@Size(min = 5, max = 6, message = "验证码长度有误，请输入6位验证码")
	private String vcode;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
}
