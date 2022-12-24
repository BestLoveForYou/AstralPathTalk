package com.example.demo.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
}
