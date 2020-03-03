package com.example.helloworld0302.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wl
 */
@RestController
@RequestMapping("/test")
public class HelloWorldController {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World";
    }

}
