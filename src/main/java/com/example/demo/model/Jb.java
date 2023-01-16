package com.example.demo.model;

public class Jb {
	private int id;
	private int jb_id;
	private int jb_mode;
	private int jb_by_player;
	private String date;
	private int isOver;
	private int res;
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Jb [id=" + id + ", jb_id=" + jb_id + ", jb_mode=" + jb_mode + ", jb_by_player=" + jb_by_player
				+ ", date=" + date + ", isOver=" + isOver + ", res=" + res + "]";
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJb_id() {
		return jb_id;
	}
	public void setJb_id(int jb_id) {
		this.jb_id = jb_id;
	}
	public int getJb_mode() {
		return jb_mode;
	}
	public void setJb_mode(int jb_mode) {
		this.jb_mode = jb_mode;
	}
	public int getJb_by_player() {
		return jb_by_player;
	}
	public void setJb_by_player(int jb_by_player) {
		this.jb_by_player = jb_by_player;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
}
