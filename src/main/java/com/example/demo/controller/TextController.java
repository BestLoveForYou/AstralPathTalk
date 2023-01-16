package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoApplication;
import com.example.demo.dao.CommentDao;
import com.example.demo.model.Comment;
import com.example.demo.model.Jb;
import com.example.demo.model.MessageBoard;
import com.example.demo.model.Text;
import com.example.demo.service.CommentService;
import com.example.demo.service.JbService;
import com.example.demo.service.TextService;
import com.example.demo.service.UserService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;

@RestController
public class TextController {
	@Autowired
	TextService textService;
	@Autowired
	UserService userService;
	@Autowired
	JbService jbService;
	@Autowired
	CommentDao commentDao;
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
	
	@GetMapping("/text/getComment")
	public List<Comment> getComment(Integer id) {
		List<Comment>  comments = commentDao.getAll(id);
		List<Comment>  comments2 = new ArrayList<>();
		for (int x = 0 ;x < comments.size();x ++) {
			Comment comment = comments.get(x);
			comment.setBody(comment.getBody().replaceAll("\r\n", "<br>"));
			comments2.add(comment);
		}
		return comments2;
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
		System.out.print(body);
		int a = (Integer) httpSession.getAttribute("notenumber") + 1;
		httpSession.setAttribute("notenumber", a);
		userService.updateNote((Integer) httpSession.getAttribute("id"), a);
		textService.addText(text);
		DemoApplication.textMap.put("info", "文章添加,发布者:" + (String) httpSession.getAttribute("email") + "发布时间:" + fmt.format(date));
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
	
	@PostMapping("/text/add")
	public int add(HttpSession httpSession,String body,int textid) {
		Comment comment = new Comment();
		comment.setBody(body);
		comment.setText_id(textid);
		comment.setLocked(0);
		comment.setByEmail((String) httpSession.getAttribute("email"));
		DemoApplication.textMap.put("info", "评论,评论者:" + (String) httpSession.getAttribute("email") + "评论文章:" + textid);
		return commentDao.addComment(comment);
	}
	@GetMapping("/text/jb")
	public String jb(HttpSession httpSession,int id) {
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		Jb jb = new Jb();
		jb.setIsOver(0);
		jb.setDate(fmt.format(date));
		jb.setJb_by_player((Integer) httpSession.getAttribute("id"));
		jb.setJb_id(id);
		jb.setJb_mode(0);//0为文章
		jbService.addJb(jb);
		return "成功";
	}
	@GetMapping("/text/jbpl")
	public String jb2(HttpSession httpSession,int id) {
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		Jb jb = new Jb();
		jb.setIsOver(0);
		jb.setDate(fmt.format(date));
		try {
			jb.setJb_by_player((Integer) httpSession.getAttribute("id"));
		} catch (Exception e) {
			return "未登录";
		}

		jb.setJb_id(id);
		jb.setJb_mode(2);//2为评论
		jb.setRes(0);
		jbService.addJb(jb);
		return "成功";
	}
}
