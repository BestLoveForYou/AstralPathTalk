package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.JbDao;
import com.example.demo.model.Jb;

@Service
public class JbService {
	@Autowired
    JbDao commectd;
    public int addJb(Jb commect) {
        return commectd.addJb(commect);
    }
    public int upda(Integer id,Integer res) {
    	return commectd.upda(id, res);
    }
    public int remove(Integer id) {
        return commectd.remove(id);
    }
    public int recovery(Integer id) {
        return commectd.recovery(id);
    }
    public List<Jb> getWaitDo() {
        return commectd.getWaitDo();
    }
    public List<Jb> getJb() {
        return commectd.getAll();
    }
}
