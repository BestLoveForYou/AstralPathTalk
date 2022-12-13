package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;

@Service
public class UserService {
    @Autowired
    UserDao userd;
    public int addUser(User user) {
        return userd.addUser(user);
    }
    public int updateMoney(Integer id,Integer money) {
        return userd.updateMoney(id,money);
    }
    public int updateLevel(Integer id,Integer level) {
        return userd.updateLevel(id,level);
    }
    public int updateDate(Integer id,String date) {
        return userd.updateDate(id,date);
    }
    public int updateNote(Integer id,Integer num) {
        return userd.updateNote(id, num);
    }
    public int updateName(Integer id,String username) {
        return userd.updateName(id, username);
    }
    public int updatePass(Integer id,String password) {
        return userd.updatePass(id, password);
    }
    public int remove(Integer id) {
        return userd.remove(id);
    }
    public int recovery(Integer id) {
        return userd.recovery(id);
    }
    public User getUserById(Integer id) {
        return userd.getUserById(id);
    }
    public User checkUser(String email) {
        return userd.checkUserLogin(email);
    }
    public List<User> getAllUser() {
        return userd.getAllUser();
    }
}
