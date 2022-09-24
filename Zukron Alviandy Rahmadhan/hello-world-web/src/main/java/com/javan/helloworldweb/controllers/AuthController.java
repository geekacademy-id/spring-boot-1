package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public Response authenticate() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();

        payload.put("message1", "Why you decode this token ?");
        payload.put("message2", "What are you looking for ?");

        String token = jwtUtil.generateToken(payload);

        data.put("token", token);

        return new Response(HttpStatus.OK, "Here your token", data);
    }
}
