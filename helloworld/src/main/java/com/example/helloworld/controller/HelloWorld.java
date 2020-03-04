package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: weilong
 * @Date: 2020/3/4 10:07
 */

@RestController
@RequestMapping("test")
public class HelloWorld {
    @GetMapping("hello")
    public String sayHello(){
        return "hello world !";
    }
}
