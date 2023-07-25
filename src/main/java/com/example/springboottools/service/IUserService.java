package com.example.springboottools.service;

import com.example.springboottools.entity.IUser;
import com.example.springboottools.entity.query.UserQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


/**
 * @author pdai
 */
public interface IUserService extends IBaseService<IUser, Long> {

    /**
     * find by page.
     *
     * @param userQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<IUser> findPage(UserQueryBean userQueryBean, PageRequest pageRequest);

}
