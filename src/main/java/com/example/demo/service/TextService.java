package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TextDao;
import com.example.demo.model.Text;

@Service
public class TextService {
    @Autowired
    TextDao textd;
    public int addText(Text text) {
        return textd.addText(text);
    }
    public int remove(Integer id) {
        return textd.remove(id);
    }
    public int recovery(Integer id) {
        return textd.recovery(id);
    }
    public Text getTextById(Integer id) {
    	Text text = textd.getTextById(id);
    	textd.setLook(id, text.getLook() + 1);
        return text;
    }
    public int setQuanzhong(int id,int quanzhong) {
    	return textd.setQuanzhong(id, quanzhong);
    }
    public List<Text> getAllText(String author) {
        return textd.getAlltext(author);
    }
    public List<Text> getHotText() {
        return textd.getHottext();
    }
    public List<Text> getAll() {
        return textd.getAll();
    }
    public List<Text> selecttextBytitle(String title,int start,int end) {
        return textd.selecttextBytitle(title,start,end);
    }
}
