package com.example.demo.model;

public class Comment {
	private int id;
	private int text_id;
	private String byEmail;
	private String body;
	private int locked;
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getText_id() {
		return text_id;
	}
	public void setText_id(int text_id) {
		this.text_id = text_id;
	}
	public String getByEmail() {
		return byEmail;
	}
	public void setByEmail(String byEmail) {
		this.byEmail = byEmail;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
