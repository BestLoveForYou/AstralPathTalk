package com.example.demo.model;

public class Fan {
	private int id;
	private int guanzhiid;
	private int userid;
	private String date;
	private int locked;
	private String username;
	private String guanzhuname;
	public String getGuanzhuname() {
		return guanzhuname;
	}
	public void setGuanzhuname(String guanzhuname) {
		this.guanzhuname = guanzhuname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public int getGuanzhiid() {
		return guanzhiid;
	}
	public void setGuanzhiid(int guanzhiid) {
		this.guanzhiid = guanzhiid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
