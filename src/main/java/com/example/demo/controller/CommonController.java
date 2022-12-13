package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Text;
import com.example.demo.model.User;
import com.example.demo.service.TextService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommonController {
	@Autowired
	TextService textService;
	@PostMapping("/user/getUserLogin")
	public User getUserInfo(HttpSession session) {
		User user =new User();
		
		String email = (String) session.getAttribute("email");
		user.setEmail(email);
		String username = (String) session.getAttribute("username");
		user.setUsername(username);
		int money= (Integer) session.getAttribute("money");
		user.setMoney(money);
		int level = (Integer) session.getAttribute("level");
		user.setLevel(level);
		String date = (String) session.getAttribute("date");
		user.setDate(date);
		int notenumber = (Integer) session.getAttribute("notenumber");
		user.setNotenumber(notenumber);
		user.setPassword("null");
		user.setLocked(0);
		int id = (Integer) session.getAttribute("id");
		user.setId(id);
		
		return user;
	}
	@PostMapping("/text/getUserLogin")
	public User getUserInfo2(HttpSession session) {
		User user =new User();
		
		String email = (String) session.getAttribute("email");
		user.setEmail(email);
		String username = (String) session.getAttribute("username");
		user.setUsername(username);
		int money= (Integer) session.getAttribute("money");
		user.setMoney(money);
		int level = (Integer) session.getAttribute("level");
		user.setLevel(level);
		String date = (String) session.getAttribute("date");
		user.setDate(date);
		int notenumber = (Integer) session.getAttribute("notenumber");
		user.setNotenumber(notenumber);
		user.setPassword("null");
		user.setLocked(0);
		int id = (Integer) session.getAttribute("id");
		user.setId(id);
		
		return user;
	}
	@PostMapping("/mb/getUserLogin")
	public User getUserInfo4(HttpSession session) {
		User user =new User();
		
		String email = (String) session.getAttribute("email");
		user.setEmail(email);
		String username = (String) session.getAttribute("username");
		user.setUsername(username);
		int money= (Integer) session.getAttribute("money");
		user.setMoney(money);
		int level = (Integer) session.getAttribute("level");
		user.setLevel(level);
		String date = (String) session.getAttribute("date");
		user.setDate(date);
		int notenumber = (Integer) session.getAttribute("notenumber");
		user.setNotenumber(notenumber);
		user.setPassword("null");
		user.setLocked(0);
		int id = (Integer) session.getAttribute("id");
		user.setId(id);
		
		return user;
	}
	@PostMapping("/user/getUserAllText")
	public List<Text> getUserInfo3(HttpSession session) {
		String email = (String) session.getAttribute("email");
		
		return textService.getAllText(email);
	}
}
