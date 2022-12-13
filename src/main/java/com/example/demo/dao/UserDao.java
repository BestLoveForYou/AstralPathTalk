package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate jdbc;
	public int addUser(User user) {
		return jdbc.update("INSERT INTO `webtest`.`user`(`id`,`username`,`password`,`money`,`level`,`notenumber`,`locked`,`email`,`date`,`role`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?)"
				,null,user.getUsername(),user.getPassword(),user.getMoney(),user.getLevel(),user.getNotenumber(),user.getLocked(),user.getEmail(),user.getDate(),user.getRole());
	}
	public int updateMoney(Integer id,Integer money) {
		return jdbc.update("UPDATE `webtest`.`user` SET `money`=? WHERE `id`=?",money,id);
	}
	public int updateLevel(Integer id,Integer level) {
		return jdbc.update("UPDATE `webtest`.`user` SET `level`=? WHERE `id`=?",level,id);
	}
	public int updateDate(Integer id,String date) {
		return jdbc.update("UPDATE `webtest`.`user` SET `date`=? WHERE `id`=?",date,id);
	}
	public int updateNote(Integer id,Integer num) {
		return jdbc.update("UPDATE `webtest`.`user` SET `notenumber`=? WHERE `id`=?",num,id);
	}
	public int updateName(Integer id,String username) {
		return jdbc.update("UPDATE `webtest`.`user` SET `username`=? WHERE `id`=?",username,id);
	}
	public int updatePass(Integer id,String num) {
		return jdbc.update("UPDATE `webtest`.`user` SET `password`=? WHERE `id`=?",num,id);
	}
	
	public int remove(Integer id) {
		return jdbc.update("UPDATE `webtest`.`user` SET `locked`='1' WHERE `id`=?",id);
	}
	public int recovery(Integer id) {
		return jdbc.update("UPDATE `webtest`.`user` SET `locked`='0' WHERE `id`=?",id);
	}
	public User getUserById(Integer id) {
		return jdbc.queryForObject("SELECT * FROM user WHERE id = ?",
				new BeanPropertyRowMapper<>(User.class),id);
	}
	public User checkUserLogin(String email) {
		return jdbc.queryForObject("SELECT * FROM user WHERE email = ?",
				new BeanPropertyRowMapper<>(User.class),email);
	}
	public List<User> getAllUser() {
		return jdbc.query("SELECT * FROM user",
				new BeanPropertyRowMapper<>(User.class));
	}
}
