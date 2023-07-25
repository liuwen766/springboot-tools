package com.example.springboottools.dao;

import com.example.springboottools.entity.IUser;
import org.springframework.stereotype.Repository;


/**
 * @author pdai
 */
@Repository
public interface IUserDao extends IBaseDao<IUser, Long> {

}
