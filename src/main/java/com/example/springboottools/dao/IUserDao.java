package com.example.springboottools.dao;

import com.example.springboottools.entity.mysql.IUser;
import org.springframework.stereotype.Repository;


/**
 * @author pdai
 */
@Repository
public interface IUserDao extends IBaseDao<IUser, Long> {

}
