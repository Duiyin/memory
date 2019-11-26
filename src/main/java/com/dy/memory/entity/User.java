package com.dy.memory.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.dy.memory.util.ID;
import com.dy.memory.util.Time;
import com.dy.memory.util.UserDegree;

@Entity
public class User {
	
	@Id
	private String id;
	
	private String username;
	
	private String userpwd;
	
	private String mailbox;		//邮箱
	
	private String role;		//用户权限
	
	private Timestamp ctime;	//创建时间
	
	public User(){
		this.id = ID.uuid();
		this.role = UserDegree.MEMBER.toString();
		this.ctime = Time.timestamp();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
