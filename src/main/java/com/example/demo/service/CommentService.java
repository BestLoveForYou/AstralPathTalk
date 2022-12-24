package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentDao;
import com.example.demo.model.Comment;

@Service
public class CommentService {
	@Autowired
    CommentDao commectd;
    public int addComment(Comment commect) {
        return commectd.addComment(commect);
    }
    public int remove(Integer id) {
        return commectd.remove(id);
    }
    public int recovery(Integer id) {
        return commectd.recovery(id);
    }
    public List<Comment> getComment(Integer textid) {
        return commectd.getAll(textid);
    }
}
