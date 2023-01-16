package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Jb;
import com.example.demo.model.MessageBoard;
import com.example.demo.service.JbService;
import com.example.demo.service.MBService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MessageBoardController {
	@Autowired
	MBService mbService;
	@Autowired
	JbService jbService;
	@GetMapping("/mb/get")
	public List<MessageBoard> get() {
		return mbService.getAllMessageBoardsDao();
	}
	@PostMapping("/mb/add")
	public int add(HttpSession httpSession,String body) {
		MessageBoard m = new MessageBoard();
		m.setBody(body);
		m.setByid((Integer) httpSession.getAttribute("id"));
		m.setBy((String) httpSession.getAttribute("username"));
		m.setLocked(0);
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		m.setDate(fmt.format(date));
		return mbService.addMessageBoardsDao(m);
	}
	@GetMapping("/mb/jb")
	public String jb(HttpSession httpSession,int id) {
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		Jb jb = new Jb();
		jb.setIsOver(0);
		jb.setDate(fmt.format(date));
		jb.setJb_by_player((Integer) httpSession.getAttribute("id"));
		jb.setJb_id(id);
		jb.setJb_mode(1);//1为留言
		jbService.addJb(jb);
		return "成功";
	}
}
