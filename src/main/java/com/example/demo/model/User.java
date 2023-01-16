package com.example.demo.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String date;
    private Integer money;
    private Integer level;
    private	Integer notenumber;
    private Integer locked;
    private String email;
    private int role;
    private String title;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNotenumber() {
		return notenumber;
	}
	public void setNotenumber(int notenumber) {
		this.notenumber = notenumber;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int i) {
		this.role = i;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setNotenumber(Integer notenumber) {
		this.notenumber = notenumber;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", date=" + date + ", money="
				+ money + ", level=" + level + ", notenumber=" + notenumber + ", locked=" + locked + ", email=" + email
				+ ", role=" + role + ", title=" + title + "]";
	}
	
}
