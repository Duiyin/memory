package com.dy.memory.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.dy.memory.util.ID;
import com.dy.memory.util.PasswordUtil;
import com.dy.memory.util.Time;
import com.dy.memory.util.UserDegree;

@Entity
public class User {
	
	@Id
	private String id;
	
	private String account;		//邮箱作账号
	
	private String password;	//用户密码
	
	private String username;	//用户昵称
	
	private UserDegree role;	//用户权限
	
	private Timestamp ctime;	//创建时间
	
	public User(){
		this.id = ID.uuid();
		this.username = ID.Intercept();
		this.role = UserDegree.MEMBER;
		this.ctime = Time.timestamp();
	}
	
	//初始化账户
	public void init(){
		this.setAccount("memory@memory.com");
		this.setPassword("memory123456");
		this.setUsername("memory");
		this.setRole(UserDegree.SUPERADMIN);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		this.password = PasswordUtil.createPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserDegree getRole() {
		return role;
	}

	public void setRole(UserDegree role) {
		this.role = role;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
