package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.util.TransApi;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	public static void main(String[] args) throws Exception{
		System.out.println("Welcome to use AstralPathTalk!");
		SpringApplication.run(DemoApplication.class, args);
	}
}
