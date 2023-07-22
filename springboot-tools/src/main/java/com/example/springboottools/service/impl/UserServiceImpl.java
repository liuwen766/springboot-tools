package com.example.springboottools.service.impl;

import com.example.springboottools.dao.UserH2Repository;
import com.example.springboottools.dao.UserRepository;
import com.example.springboottools.entity.User;
import com.example.springboottools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


//    ArrayList作为数据库
//    @Autowired
//    private UserRepository userDao;

//    H2内存数据库
    @Autowired
    private UserH2Repository userDao;
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }
}
