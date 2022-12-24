package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comment;

@Repository
public class CommentDao {
	@Autowired
	JdbcTemplate jdbc;
	public int addComment(Comment comment) {
		return jdbc.update("INSERT INTO `webtest`.`comment`(`id`,`text_id`,`byEmail`,`body`,`locked`) VALUES "
				+ "(?,?,?,?,?)"
				,null,comment.getText_id(),comment.getByEmail(),comment.getBody(),comment.getLocked());
	}
	public int remove(Integer id) {
		return jdbc.update("UPDATE `webtest`.`comment` SET `locked`='1' WHERE `id`=?",id);
	}
	public int recovery(Integer id) {
		return jdbc.update("UPDATE `webtest`.`comment` SET `locked`='0' WHERE `id`=?",id);
	}
	public List<Comment> getAll(Integer textid) {
		return jdbc.query("SELECT * FROM comment WHERE `text_id`=?",
				new BeanPropertyRowMapper<>(Comment.class),textid);
	}
}
