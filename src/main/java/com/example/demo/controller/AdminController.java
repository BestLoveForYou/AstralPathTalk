package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Scanner;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SConfig;

import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController {
    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static int cpuLoad() {

        double cpuLoad = osmxb.getSystemLoadAverage();

        int percentCpuLoad = (int) (cpuLoad * 100);

        return percentCpuLoad;

    }
	@PostMapping("/admin/getSC")
	public SConfig getSConfig(HttpSession httpSession) {
		SConfig sConfig= new SConfig();
		sConfig.setCpu(getCpuInfo());
		Runtime runtime = Runtime.getRuntime();
		sConfig.setCpunum(String.valueOf(runtime.availableProcessors()));
		sConfig.setCpuZhanYong(String.valueOf(cpuLoad()));
		return sConfig;
		
	}

	public static String getHardDiskSN(String drive) {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
 
			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
					+ "Set colDrives = objFSO.Drives\n"
					+ "Set objDrive = colDrives.item(\""
					+ drive
					+ "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber"; // see note
			fw.write(vbs);
			fw.close();
			String path = file.getPath().replace("%20", " ");
			Process p = Runtime.getRuntime().exec(
					"cscript //NoLogo " + path);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
			result=result.trim();
			Integer integer=Integer.parseInt(result);
			Long long1=Long.parseLong(Integer.toHexString(integer), 16);
			result=long1.toString();
		} catch (Exception e) {
		}
		return result;
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
