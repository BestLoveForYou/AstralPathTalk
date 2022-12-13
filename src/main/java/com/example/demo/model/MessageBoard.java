package com.example.demo.model;

public class MessageBoard {
	Integer id;
	String by;
	Integer byid;
	String body;
	String date;
	Integer locked;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public Integer getByid() {
		return byid;
	}
	public void setByid(Integer byid) {
		this.byid = byid;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
}
