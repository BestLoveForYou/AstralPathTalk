package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Text;

@Repository
public class TextDao {
	@Autowired
	JdbcTemplate jdbc;
	public int addText(Text text) {
		return jdbc.update("INSERT INTO `webtest`.`text`(`id`,`title`,`target`,`body`,`author`,`quanzhong`,`look`,`locked`,`date`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?)"
				,null,text.getTitle(),text.getTarget(),text.getBody(),text.getAuthor(),text.getQuanzhong(),text.getLook(),text.getLocked(),text.getDate());
	}
	public int remove(Integer id) {
		return jdbc.update("UPDATE `webtest`.`text` SET `locked`='1' WHERE `id`=?",id);
	}
	public int recovery(Integer id) {
		return jdbc.update("UPDATE `webtest`.`text` SET `locked`='0' WHERE `id`=?",id);
	}
	public Text getTextById(Integer id) {
		return jdbc.queryForObject("SELECT * FROM text WHERE id = ?",
				new BeanPropertyRowMapper<>(Text.class),id);
	}
	public int setLook(Integer id,Integer look) {
		return jdbc.update("UPDATE `webtest`.`text` SET `look`=? WHERE `id`=?",look,id);
	}
	public int setQuanzhong(Integer id,Integer num) {
		return jdbc.update("UPDATE `webtest`.`text` SET `quanzhong`=? WHERE `id`=?",num,id);
	}
	public List<Text> getHottext() {
		return jdbc.query("SELECT * FROM `webtest`.`text` ORDER BY 6 DESC LIMIT 0, 10",
				new BeanPropertyRowMapper<>(Text.class));
	}
	public List<Text> getAlltext(String author) {
		return jdbc.query("SELECT * FROM text WHERE `author`=?",
				new BeanPropertyRowMapper<>(Text.class),author);
	}
	public List<Text> getAll() {
		return jdbc.query("SELECT * FROM text",
				new BeanPropertyRowMapper<>(Text.class));
	}
	public List<Text> selecttextBytitle(String title,int start,int end) {
		return jdbc.query("SELECT * FROM text WHERE `title` LIKE ? LIMIT ?,?",
				new BeanPropertyRowMapper<>(Text.class),title,start,end);
	}
}
