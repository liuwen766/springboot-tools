package com.example.springboottools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringbootToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootToolsApplication.class, args);
    }

}
