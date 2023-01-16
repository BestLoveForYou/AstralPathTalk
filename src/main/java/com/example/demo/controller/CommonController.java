package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoApplication;
import com.example.demo.model.Text;
import com.example.demo.model.User;
import com.example.demo.service.TextService;
import com.example.demo.service.UserService;
import com.example.demo.util.TransApi;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommonController {
	@Autowired
	TextService textService;
	@Autowired
	UserService userService;
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
		String title = (String) session.getAttribute("title");
		user.setTitle(title);
		int notenumber = (Integer) session.getAttribute("notenumber");
		user.setNotenumber(notenumber);
		user.setPassword("null");
		user.setLocked(0);
		int id = (Integer) session.getAttribute("id");
		user.setId(id);
		
		return user;
	}
	@PostMapping("/user/getUserById")
	public User getUserInfo5(HttpSession session,int id) {
		User user =new User();
		user = userService.getUserById(id);
		return user;
	}
	@PostMapping("/user/getUserByEmail")
	public User getUserInfoe(HttpSession session,String email) {
		User user =new User();
		if (isNumeric(email)) {
			user = userService.getUserById(Integer.valueOf(email));
			
			return user;
		} else {
			user = userService.getUserByEmail(email);
			return user;
		}
		
	}
	public static boolean isNumeric(String str){ 
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){ 
		    return false; 
		   } 
		  } 
		  return true; 
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
	@PostMapping("/admin/getUserLogin")
	public User agetUserInfo(HttpSession session) {
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
		user.setRole((Integer) session.getAttribute("role"));
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
	@PostMapping("/user/getUserAllTextByEmail")
	public List<Text> getUserInfotextby(HttpSession session,String email) {
		
		return textService.getAllText(email);
	}
	@PostMapping("/trans/api")
	public String fanyi(String text,String to) {
		String a = DemoApplication.api.getTransResult(text, "auto", to);
		System.out.println(a);
		return a;
	}
}
