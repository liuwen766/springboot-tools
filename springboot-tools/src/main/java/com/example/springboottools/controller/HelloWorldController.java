package com.example.springboottools.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    /**
     * hello world.
     *
     * @return hello
     */
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }


    @GetMapping("health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>("health check ok", HttpStatus.OK);
    }

}
