package org.example.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeployController {

    @GetMapping(value = "/test")
    public String upload(){
        return "shiju";
    }

}

