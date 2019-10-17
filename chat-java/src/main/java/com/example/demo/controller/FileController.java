package com.example.demo.controller;

import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    public Map uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
