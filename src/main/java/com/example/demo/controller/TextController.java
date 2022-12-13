package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Text;
import com.example.demo.service.TextService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class TextController {
	@Autowired
	TextService textService;
	@Autowired
	UserService userService;
	@GetMapping("/text/rest")
	public Text getText(Integer id) {
		Text text = textService.getTextById(id);
		text.setBody(text.getBody().replaceAll("\r\n", "<br>"));
		return text;
	}
	@PostMapping("/text/rest")
	public String getTextBody(Integer id) {
		return textService.getTextById(id).getBody();
	}
	@PostMapping("/text/create")
	public int create(HttpSession httpSession,String title,String target,String body) {
		String email = (String) httpSession.getAttribute("email");
		
		Text text = new Text();
		text.setAuthor(email);
		text.setBody(body);
		text.setTitle(title);
		text.setTarget(target);
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		text.setDate(fmt.format(date));
		text.setLocked(0);
		text.setLook(0);
		text.setQuanzhong(100);
		
		int a = (Integer) httpSession.getAttribute("notenumber") + 1;
		httpSession.setAttribute("notenumber", a);
		userService.updateNote((Integer) httpSession.getAttribute("id"), a);
		textService.addText(text);
		return 1;
	}
	@GetMapping("/text/search")
	public List<Text> search2(String title,int start,int end) {
		return textService.selecttextBytitle("%" + title + "%", start, end);
	}
 
	@GetMapping("/text/out")//主动推送
	public List<Text> getOuText() {
		return textService.getHotText();
	}
	
}
