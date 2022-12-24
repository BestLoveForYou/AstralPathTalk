package com.example.demo.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SConfig;

import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController {
	@PostMapping("/admin/getSC")
	public SConfig getSConfig(HttpSession httpSession) {
		SConfig sConfig= new SConfig();
		sConfig.setCpu(getCpuInfo());
		Runtime runtime = Runtime.getRuntime();
		sConfig.setCpunum(String.valueOf(runtime.availableProcessors()));
		
		return sConfig;
		
	}
	// 获取CPU序列号
	@SuppressWarnings("resource")
	public static String getCpuInfo() {
		try {
			 Process process = Runtime.getRuntime().exec(
					    new String[] { "wmic", "cpu", "get", "ProcessorId" });
					  process.getOutputStream().close();
					  Scanner sc = new Scanner(process.getInputStream());
					  String property = sc.next();
					  String serial = sc.next();
					  if(serial.length()==16) {
						  serial+="-0000000000000000";
					  }
					  return serial;
		} catch (Exception e) {
		}
		 return null;   
	} 
}
