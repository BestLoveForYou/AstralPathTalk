package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.util.TransApi;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	private static final String APP_ID = "20220713001271803";//自己的是啥就输入啥
	private static final String SECURITY_KEY = "7T7t5VQ2JdjU5JArTtK8";
	public static void main(String[] args) throws Exception{
		System.out.println("Welcome to use AstralPathTalk!");
		//TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		//String transResult = api.getTransResult("今天下午第1.2节课物理做这张试卷，大家可以提前打印出来。", "auto", "en");
		SpringApplication.run(DemoApplication.class, args);
	}
}
