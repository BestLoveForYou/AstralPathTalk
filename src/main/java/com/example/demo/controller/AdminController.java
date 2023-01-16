package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FMessage;
import com.example.demo.model.Jb;
import com.example.demo.model.SConfig;
import com.example.demo.model.Text;
import com.example.demo.service.JbService;
import com.example.demo.service.TextService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController {
	@Autowired
	JbService jbService;
	@Autowired
	TextService textService;
	@PostMapping("/admin/getSC")
	public SConfig getSConfig(HttpSession httpSession) {
		SConfig sConfig= new SConfig();
		sConfig.setCpu(getCpuInfo());
		Runtime runtime = Runtime.getRuntime();
		sConfig.setCpunum(String.valueOf(runtime.availableProcessors()));
		
		return sConfig;
		
	}
	@GetMapping("/admin/shenghe")
	public Text getText2() {
		
		int id = new Random().nextInt(textService.getAll().size());
		Text text = textService.getTextById(id);
		text.setBody(text.getBody().replaceAll("\r\n", "<br>"));
		
		return text;
	}
	@PostMapping("/admin/getJb")
	public List<Jb> getJb(HttpSession httpSession) {
		return jbService.getWaitDo();	
	}
	@GetMapping("/admin/thread")
	public int thr() {
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread.getParent() != null; parentThread = parentThread.getParent());
		int totalThread = parentThread.activeCount();
		return totalThread ;
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
	
	@PostMapping("/admin/file")
	public List<FMessage> getFile(String path) throws IOException {
		File file = new File(path);
		List<FMessage> aList = new ArrayList<>();
		if(file.isFile()) {
			FileInputStream input = new FileInputStream(file);					// 文件输入流
            FileChannel channel = input.getChannel(); 							// 获取文件通道
            ByteBuffer buffer = ByteBuffer.allocate(100); 						// 开辟缓冲大小
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); 			// 内存输出流
            int count = 0; 													// 保存读取个数
            while ((count = channel.read(buffer)) != -1) {						// 缓冲区读取
                buffer.flip(); 												// 重置缓冲区
                while (buffer.hasRemaining()) { 								// 是否还有数据
                    bos.write(buffer.get()); 									// 内容写入内存流
                }
                buffer.clear();												// 清空缓冲区
            }
            channel.close();													// 关闭通道
            input.close();
            FMessage f = new FMessage();
            f.setClazz(1);
            f.setName(bos.toString());
            aList.add(f);
		} else {
			File f[] = file.listFiles();
			for(int x = 0 ; x < f.length; x ++) {
				FMessage fa = new FMessage();
	            fa.setName(f[x].getPath());
	            fa.setClazz(0);
				aList.add(fa);
			}
		}
		
		return aList;
	}
	@PutMapping("/admin/file")
	public int writeFile(String path,String body,int model) throws IOException {
		File file = new File(path);
		if (model == 0) {
			PrintWriter pu = new PrintWriter(file);
			if (body.contains("<br>")) {
				String r[] = body.split("<br>");
				for (int i = 0; i < r.length -1; i++) {
					 
					pu.println(r[i]);
				}
				pu.print(r[r.length - 1]);
			} else {
				pu.print(body);
			}

	    	pu.close();
		} else if (model == 1) {
			PrintWriter pu = new PrintWriter(new FileWriter(file, true));
			if (body.contains("<br>")) {
				String r[] = body.split("<br>");
				for (int i = 0; i < r.length -1; i++) {
			 
					pu.println(r[i]);
				}
				pu.print(r[r.length - 1]);
			} else {
				pu.print(body);
			}
	    	pu.close();
		} 
    	return 1;
	}
	@GetMapping("/admin/cmd")
	public String cmd(String cmd) {
        String line = null;
		try {
            Runtime rt = Runtime.getRuntime();
//        Process pr = rt.exec("cmd /c dir");
//        Process pr = rt.exec("D:/APP/Evernote/Evernote.exe");//open evernote program
            Process pr = rt.exec(cmd) ;//open tim program
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(),"GBK"));

            while ((line = input.readLine())!=null){
            	line = line + "\r\n" + input.readLine();
            }
            int exitValue = pr.waitFor();
            System.out.println("Exited with error code "+exitValue);
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		return line;
	}
}
