package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	MailService mailService;
	@PostMapping("/user/rega")
	public int reg1(HttpSession session,String email,String password,String username) {
		try {
			User user = userService.checkUser(email);
			return 0;
		} catch (Exception e) {
			String link = String.valueOf(UUID.randomUUID()).substring(0,5);
			session.setAttribute("auth", link);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			session.setAttribute("username", username);
			new Thread(() -> {                       
				mailService.sendSimpleMail("yaoboyulove@163.com", email, "yaoboyulove@163.com", "欢迎加入AstralPath", 
						"AstralPath目前处于初期发展,欢迎新用户的加入,您需要进行邮箱认证,若非自己操作,请忽略\r\n"
						+ "验证码:" + link);
	        },"发生邮箱").start();

	 		
			System.out.println(link);
			return 1;
		}
	}
	@PostMapping("/user/regb")
	public User reg2(HttpSession session,String auth) {
		if(session.getAttribute("auth").equals(auth)) {
			User u = new User();
			u.setEmail((String) session.getAttribute("email"));
			u.setUsername((String) session.getAttribute("username"));
			u.setPassword((String) session.getAttribute("password"));
			u.setLevel(1);
			u.setMoney(0);
			u.setRole(0);
			u.setLocked(0);
			Date date = new Date();
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			u.setDate(fmt.format(date));
			u.setNotenumber(0);
			System.out.println("数据添加");
			System.out.println(u.toString());
			userService.addUser(u);
		
			return u;
		}
		return null;
		
	}
	
	@PostMapping("/user/login")
	public User loginUser(HttpSession session,String email,String password) {
		User user = userService.checkUser(email);
		if (user.getPassword().equals(password)) {
			if (user.getLocked() == 0) {
				session.setAttribute("id", user.getId());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("money", user.getMoney());
				session.setAttribute("date", user.getDate());
				session.setAttribute("notenumber", user.getNotenumber());
				session.setAttribute("level", user.getLevel());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole());
				return user;
			}
			return null;
		}
		return null;
	}
	@GetMapping("/user/sign")//签到
	public String sign(HttpSession session) {
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if(!fmt.format(date).equals((String) session.getAttribute("date"))) {
			userService.updateMoney((Integer) session.getAttribute("id"), (Integer) session.getAttribute("money") + 100);
			userService.updateLevel((Integer) session.getAttribute("id"), (Integer) session.getAttribute("level") + 10);
			userService.updateDate((Integer) session.getAttribute("id"), fmt.format(date));
			session.setAttribute("money", (Integer) session.getAttribute("money") + 100);
			session.setAttribute("level",(Integer) session.getAttribute("level") + 10);
			
			return null;
		}
		return "今日已签到!";
	}
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("money");
		session.removeAttribute("email");
		session.removeAttribute("date");
		session.removeAttribute("username");
		session.removeAttribute("level");
		session.removeAttribute("notenumber");
		session.removeAttribute("role");
		
		
		return "完成";
	}
	
	@GetMapping("/user/change/username")
	public String change1(HttpSession session,String username) {
		userService.updateName((Integer) session.getAttribute("id"), username);
		session.setAttribute("username", username);
		return "完成";
	}
	@GetMapping("/user/change/password")
	public String change2(HttpSession session,String password) {
		userService.updatePass((Integer) session.getAttribute("id"), password);
		return "完成";
	}
}
