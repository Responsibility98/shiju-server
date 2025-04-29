package org.example.controller;

import org.example.entity.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @PostMapping ("/login")
    public String log(@RequestBody LoginRequest loginRequest){

        return "shiju";
    }
}
