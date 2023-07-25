package com.example.springboottools.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRedis {


    /**
     * user id.
     */
    private Long id;

    /**
     * username.
     */
    private String userName;
}
