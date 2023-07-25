package com.example.springboottools.service;

import com.example.springboottools.entity.h2.User;

import java.util.List;

public interface UserService     {

    public void addUser(User user);

    public List<User> list();
}
