package com.example.springboottools.controller;

import com.example.springboottools.annotation.RedisLock;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class HelloWorldController {
    /**
     * hello world.
     *
     * @return hello
     */
    @ApiOperation("hello world 接口")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.trace("trace log @ {}", LocalDateTime.now());
        log.debug("debug log @ {}", LocalDateTime.now());
        log.info("info log @ {}", LocalDateTime.now());
        log.warn("warn log @ {}", LocalDateTime.now());
        log.error("error log @ {}", LocalDateTime.now());
        return new ResponseEntity<>("hello world.", HttpStatus.OK);
    }


    @ApiOperation("health健康检查")
    @GetMapping("health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>("health check ok!!! press Ctrl+F9 to trigger 热更新!!!", HttpStatus.OK);
    }

}
