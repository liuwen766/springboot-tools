package com.example.springboottools.controller;

import com.example.springboottools.entity.User;
import com.example.springboottools.entity.UserRedis;
import com.example.springboottools.service.IRedisService;
import com.example.springboottools.util.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/redis")
public class UserRedisController {
    // 注意：这里@Autowired是报错的，因为@Autowired按照类名注入的
    @Resource
    private RedisTemplate<String, UserRedis> redisTemplate;

//    RedisTemplate中的操作和方法众多，为了程序保持方法使用的一致性，屏蔽一些无关的方法以及对使用的方法进一步封装。
    @Autowired
    private IRedisService<UserRedis> redisService;

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Add")
    @PostMapping("add")
    public ResponseResult<UserRedis> add(UserRedis user) {
        redisTemplate.opsForValue().set(String.valueOf(user.getId()), user);
        return ResponseResult.success(redisTemplate.opsForValue().get(String.valueOf(user.getId())));
    }

    /**
     * @return user list
     */
    @ApiOperation("Find")
    @GetMapping("find/{userId}")
    public ResponseResult<UserRedis> edit(@PathVariable("userId") String userId) {
        return ResponseResult.success(redisTemplate.opsForValue().get(userId));
    }

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Add")
    @PostMapping("add2")
    public ResponseResult<UserRedis> add2(UserRedis user) {
        redisService.set(String.valueOf(user.getId()), user);
        return ResponseResult.success(redisService.get(String.valueOf(user.getId())));
    }

    /**
     * @return user list
     */
    @ApiOperation("Find")
    @GetMapping("find2/{userId}")
    public ResponseResult<UserRedis> edit2(@PathVariable("userId") String userId) {
        return ResponseResult.success(redisService.get(userId));
    }



}
