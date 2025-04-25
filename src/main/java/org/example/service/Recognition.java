package org.example.service;

import org.example.base.Result;
import org.springframework.web.multipart.MultipartFile;

public interface Recognition {
    Result RecognizeImage(MultipartFile multipartFile);
}
