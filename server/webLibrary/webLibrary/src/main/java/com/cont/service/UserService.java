package com.cont.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cont.mapper.UserMapper;
import com.cont.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public boolean enroll(String username, String password, String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User user = this.userMapper.selectOne(queryWrapper);
        if (user == null){
            User user1 = new User();
            user1.setUserName(username);
            user1.setPassword(password);
            user1.setName(name);
            
            user1.setEmail(email);
            int insert = this.userMapper.insert(user1);
            return true;
        }else {
            return false;
        }



    }

    public boolean login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username).eq("password", password);
        User user = this.userMapper.selectOne(queryWrapper);
        if (user != null) {
            return true;
        } else {
            return false;
        }

    }

    public List<User> getUserList() {
//
        List<User> users = this.userMapper.selectList(null);
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }

    public boolean userAdd(String username, String password, String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User user1 = this.userMapper.selectOne(queryWrapper);
        if (user1 == null){
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
          this.userMapper.insert(user);
          return true;
        }else {
            return false;
        }


    }

    public boolean userUpdate(String username, String password, String name, String email) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);

        int update = this.userMapper.update(user, queryWrapper);
        if (update == 1) {
            return true;
        }
        return false;
    }

    public boolean userDelete(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        int delete = this.userMapper.delete(queryWrapper);
        if (delete == 1) {
            return true;
        }
        return false;
    }


}
