package com.example.hello.world;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloWorldController {

    @GetMapping("/api/1.0/")
    public String helloWorld(){
        return "Hello World From SpringBoot";
    }
}
