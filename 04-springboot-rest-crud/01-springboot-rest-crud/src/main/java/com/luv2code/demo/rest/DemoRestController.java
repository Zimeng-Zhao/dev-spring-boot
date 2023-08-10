package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//this is some REST support,and we set up mapping for it
@RestController
@RequestMapping("/test")
public class DemoRestController {
    //add code for "/hello" endpoint
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello,World!";
    }
}
