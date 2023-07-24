package com.example.springboottools.controller;

import com.example.springboottools.entity.User;
import com.taptap.ratelimiter.annotation.RateLimit;
import com.taptap.ratelimiter.annotation.RateLimitKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rate/limit")
public class RateLimitController {

    //    在最基础的设置下，限流的 key 默认是通过方法名称拼出来的，规则如下：key = RateLimiter_ + 类名 + 方法名

    //    自定义限流 key 方式一： @RateLimitKey
    @GetMapping("/get1")
    @RateLimit(rate = 5, rateInterval = "10s")
    public String get(@RateLimitKey String name) {
        return "get" + name;
    }
//{
//	"code": 429,
//	"msg": "Too Many Requests"
//}

    //    自定义限流 key 方式二： 指定keys
    @GetMapping("/get2")
    @RateLimit(rate = 5, rateInterval = "10s", keys = {"#name"})
    public String get2(User user) {
        return "hello" + user;
    }

    //    自定义限流 key 方式三： 自定义 key 获取函数
    @GetMapping("/get3")
    @RateLimit(rate = 5, rateInterval = "10s", customKeyFunction = "keyFunction")
    public String get3(String name) {
        return "get" + name;
    }

    public String keyFunction(String name) {
        return "keyFunction" + name;
    }


}
