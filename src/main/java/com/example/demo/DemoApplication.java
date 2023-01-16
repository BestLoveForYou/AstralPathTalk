package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.util.TransApi;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	public static final String version = "1.002.2023010730";//版本
	
	public static Map<String, String> infoMap = new HashMap<>();//这是用户类的专属log
	public static Map<String, String> textMap = new HashMap<>();//这是文章类的专属log
	private static final String APP_ID = "20220713001271803";//自己的是啥就输入啥
	private static final String SECURITY_KEY = "7T7t5VQ2JdjU5JArTtK8";
	public static TransApi api = new TransApi(APP_ID, SECURITY_KEY);
	public static void main(String[] args) throws Exception{
		System.out.println("Welcome to use AstralPathTalk!");
		//String transResult = api.getTransResult("欢迎使用AstralPathTalk!", "auto", "en");
		//System.out.println(transResult);
		SpringApplication.run(DemoApplication.class, args);
	}
}
