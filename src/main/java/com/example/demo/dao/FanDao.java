package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Fan;

@Repository
public class FanDao {
	@Autowired
	JdbcTemplate jdbc;
	public int addFan(Fan user) {
		return jdbc.update("INSERT INTO `webtest`.`fan`(`id`,`guanzhiid`,`userid`,`date`,`locked`,`username`,`guanzhuname`) VALUES ( NULL,?,?,?,'0',?,?)"
				,user.getGuanzhiid(),user.getUserid(),user.getDate(),user.getUsername(),user.getGuanzhuname());
	}
	public int remove(Integer id) {
		return jdbc.update("DELETE FROM `webtest`.`fan` WHERE `id`=?",id);
	}
	
	public List<Fan> getGuanzhuById(Integer id) {//获取你关注的人
		return jdbc.query("SELECT * FROM `fan` WHERE userid = ?",
				new BeanPropertyRowMapper<>(Fan.class),id);
	}
	public List<Fan> getFanById(Integer id) {//获取关注你的人
		return jdbc.query("SELECT * FROM `fan` WHERE guanzhiid = ?",
				new BeanPropertyRowMapper<>(Fan.class),id);
	}
}
