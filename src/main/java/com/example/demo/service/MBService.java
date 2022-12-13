package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MessageBoardsDao;
import com.example.demo.model.MessageBoard;

@Service
public class MBService {
    @Autowired
    MessageBoardsDao messageBoardsDao;
    public int addMessageBoardsDao(MessageBoard MessageBoards) {
        return messageBoardsDao.addMB(MessageBoards);
    }
    public int remove(Integer id) {
        return messageBoardsDao.remove(id);
    }
    public int recovery(Integer id) {
        return messageBoardsDao.recovery(id);
    }
    public List<MessageBoard> getAllMessageBoardsDao() {
        return messageBoardsDao.getAllMessageBoard();
    }
}
