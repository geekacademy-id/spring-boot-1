package com.ichmal.trainingspringboot.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworldController {
    @GetMapping("/")
    public String index(){
        return "Hello World";
    }
}
