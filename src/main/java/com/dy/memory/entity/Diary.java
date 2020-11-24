package com.dy.memory.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dy.memory.util.ID;
import com.dy.memory.util.Time;

@Entity
public class Diary {

	@Id
	private String id;

	private String title;

	private String content;

	private Timestamp ctime;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Diary() {
		this.id = ID.UUID();
		this.ctime = Time.timestamp();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
