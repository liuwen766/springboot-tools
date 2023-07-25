package com.example.springboottools.entity.redis;

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
