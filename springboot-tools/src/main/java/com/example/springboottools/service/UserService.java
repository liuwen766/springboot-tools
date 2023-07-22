package com.example.springboottools.service;

import com.example.springboottools.entity.User;

import java.util.List;

public interface UserService     {

    public void addUser(User user);

    public List<User> list();
}
