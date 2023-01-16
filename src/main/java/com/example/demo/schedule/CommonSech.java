package com.example.demo.schedule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;
import com.example.demo.model.Text;
import com.example.demo.service.TextService;

@Component
public class CommonSech {
	@Autowired
	TextService textService;
	@Scheduled(cron = "0 * * * * ?")
	public void cron() {
		System.out.println("[Schedule]RUN - Automatic weight assignment");
		List<Text> list1 = textService.getAll();
		Map<Integer, Integer> map = new TreeMap<>();
		for(int x = 0 ;x < list1.size();x ++) {
			Text t = list1.get(x);
			map.put(t.getId(), t.getLook());
		}
		List<Map.Entry<Integer,Integer>> entryList2 = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(entryList2, new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Entry<Integer,Integer> me1, Entry<Integer,Integer> me2) {
                return me1.getValue().compareTo(me2.getValue()); // 升序排序
                //return me2.getValue().compareTo(me1.getValue()); // 降序排序
            }
        });
        for(int x = 0 ;x < entryList2.size();x ++) {
        	int id = entryList2.get(x).getKey();
        	textService.setQuanzhong(id, x);
		}
	}
	@Scheduled(cron = "0 * * * * ?")
	public void logwrite() {
		System.out.println("[Schedule]RUN - Logging...");

        Logger logger = Logger.getLogger(CommonSech.class);
        Logger logger1 = Logger.getLogger(CommonSech.class);
        BasicConfigurator.configure();
        HTMLLayout layout = new HTMLLayout();
        HTMLLayout layout1 = new HTMLLayout();
        // SimpleLayout layout = new SimpleLayout();
        try {
        	Date date = new Date();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datef = sdf.format(date);
            FileAppender appender = new FileAppender(layout, "." + File.separator  + "log" + File.separator  +datef + File.separator  + "user.html", false);
            FileAppender appender1 = new FileAppender(layout1, "." + File.separator  + "log" + File.separator  + datef+ File.separator +"text.html", false);
            logger.addAppender(appender);
            //设置日志输出级别为info，这将覆盖配置文件中设置的级别，只有日志级别高于WARN的日志才输出
            logger.setLevel(org.apache.log4j.Level.ALL);
            logger1.addAppender(appender1);
            //设置日志输出级别为info，这将覆盖配置文件中设置的级别，只有日志级别高于WARN的日志才输出
            logger1.setLevel(org.apache.log4j.Level.ALL);
            if(!DemoApplication.infoMap.isEmpty()) {
            	Iterator<Entry<String, String>> entries = DemoApplication.infoMap.entrySet().iterator();
            	while(entries.hasNext()){
            	    Entry<String, String> entry = entries.next();
            	    String key = entry.getKey();
            	    String value = entry.getValue();
            	    if (key.equals("info")) {
            	    	logger.info(value);
            	    } else if (key.equals("warn")) {
            	    	logger.warn(value);
            	    } else if (key.equals("error")) {
            	    	logger.error(value);
            	    }
            	}
        	    DemoApplication.infoMap.clear();
            }
            if(!DemoApplication.textMap.isEmpty()) {
            	Iterator<Entry<String, String>> entries = DemoApplication.textMap.entrySet().iterator();
            	while(entries.hasNext()){
            	    Entry<String, String> entry = entries.next();
            	    String key = entry.getKey();
            	    String value = entry.getValue();
            	    if (key.equals("info")) {
            	    	logger1.info(value);
            	    } else if (key.equals("warn")) {
            	    	logger1.warn(value);
            	    } else if (key.equals("error")) {
            	    	logger1.error(value);
            	    }
            	}
        	    DemoApplication.textMap.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
		}
        
	}
}
