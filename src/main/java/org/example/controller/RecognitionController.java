package org.example.controller;


import org.example.base.Result;

import org.example.service.Recognition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class RecognitionController {

    @Autowired
    private Recognition recognition;

    @GetMapping(value = "/test")
    public String upload(){
        return "shiju";
    }
    @PostMapping("/uploadImage")
    public Result uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        return recognition.RecognizeImage(multipartFile);
    }
}

