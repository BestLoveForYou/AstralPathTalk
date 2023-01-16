package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Jb;
@Repository
public class JbDao {
	@Autowired
	JdbcTemplate jdbc;
	public int addJb(Jb comment) {
		return jdbc.update("INSERT INTO `webtest`.`jb` (`id`,`jb_id`,`jb_mode`,`jb_by_player`,`date`,`isOver`,`res`) VALUES "
				+ "(?,?,?,?,?,?,?)"
				,null,comment.getJb_id(),comment.getJb_mode(),comment.getJb_by_player(),comment.getDate(),comment.getIsOver(),comment.getRes());
	}
	public int upda(Integer id,Integer res) {
		return jdbc.update("UPDATE `webtest`.`jb` SET `res`=? WHERE `id`=?"
				,res,id);
	}
	public int remove(Integer id) {
		return jdbc.update("UPDATE `webtest`.`jb` SET `isOver`='1' WHERE `id`=?",id);
	}
	public int recovery(Integer id) {
		return jdbc.update("UPDATE `webtest`.`jb` SET `isOver`='0' WHERE `id`=?",id);
	}
	public List<Jb> getWaitDo() {
		return jdbc.query("SELECT * FROM jb WHERE `isOver`=0",
				new BeanPropertyRowMapper<>(Jb.class));
	}
	public List<Jb> getAll() {
		return jdbc.query("SELECT * FROM jb",
				new BeanPropertyRowMapper<>(Jb.class));
	}
}
