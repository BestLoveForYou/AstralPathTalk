package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FanDao;
import com.example.demo.model.Fan;

@Service
public class FanService {
	 @Autowired
	 FanDao fand;
	 public int addFan(Fan user) {
		 return fand.addFan(user);
	 }
	 
	 public int remove(Integer id) {
		 return fand.remove(id);
	 }
	 
	 public List<Fan> getGuanzhuById(Integer id) {
		 return fand.getGuanzhuById(id);
	 }
	 public List<Fan> getFanById(Integer id) {
		return fand.getFanById(id);
	 }
}
