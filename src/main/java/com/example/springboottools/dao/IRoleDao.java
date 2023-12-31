package com.example.springboottools.dao;

import com.example.springboottools.entity.mysql.Role;
import org.springframework.stereotype.Repository;


/**
 * @author pdai
 */
@Repository
public interface IRoleDao extends IBaseDao<Role, Long> {

}
