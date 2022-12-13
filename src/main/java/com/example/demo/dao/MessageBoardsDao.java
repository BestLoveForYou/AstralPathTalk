package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MessageBoard;
import com.example.demo.model.MessageBoard;

@Repository
public class MessageBoardsDao {
	@Autowired
	JdbcTemplate jdbc;
	public int addMB(MessageBoard mb) {
		return jdbc.update("INSERT INTO `webtest`.`MessageBoard`(`id`,`by`,`byid`,`body`,`date`,`locked`) VALUES ( NULL,?,?,?,?,?)",
				mb.getBy(),mb.getByid(),mb.getBody(),mb.getDate(),mb.getLocked());
	}
	public int remove(Integer id) {
		return jdbc.update("UPDATE `webtest`.`MessageBoard` SET `locked`='1' WHERE `id`=?",id);
	}
	public int recovery(Integer id) {
		return jdbc.update("UPDATE `webtest`.`MessageBoard` SET `locked`='0' WHERE `id`=?",id);
	}
	public MessageBoard getMessageBoardById(Integer id) {
		return jdbc.queryForObject("SELECT * FROM MessageBoard WHERE id = ?",
				new BeanPropertyRowMapper<>(MessageBoard.class),id);
	}
	public List<MessageBoard> getAllMessageBoard() {
		return jdbc.query("SELECT * FROM MessageBoard",
				new BeanPropertyRowMapper<>(MessageBoard.class));
	}
}
