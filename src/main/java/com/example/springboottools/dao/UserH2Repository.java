package com.example.springboottools.dao;

import com.example.springboottools.entity.h2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserH2Repository extends JpaRepository<User, Integer> {
}
