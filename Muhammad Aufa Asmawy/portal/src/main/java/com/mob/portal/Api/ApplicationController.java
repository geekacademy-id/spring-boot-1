package com.mob.portal.Api;

import com.mob.portal.Helper.ResponseFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping
public class ApplicationController {

    @GetMapping("/api/")
    public Object index(){
        ArrayList<String> data = new ArrayList<>();
        data.add("Muhammad");
        data.add("Aufa");
        data.add("Asmawy");
        return new ResponseFormatter(200, "Success", data);
    }
}
