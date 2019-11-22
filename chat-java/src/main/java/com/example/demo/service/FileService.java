package com.example.demo.service;

import com.example.demo.entity.FileInfo;
import com.example.demo.enums.ContentTypeEnum;
import com.example.demo.repository.FileRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class.getName());
    @Resource
    FileRepository fileRepository;

    @Resource
    MinioService minioService;

    public Map uploadFileByStream(MultipartFile zipFile, String tag) {
        Map<String, String> result = new HashMap<>();
        String fileName = zipFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String objName = UUID.randomUUID().toString() + suffixName;
        ContentTypeEnum contentType = ContentTypeEnum.getApplicationType(suffixName);

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(zipFile.getBytes());
            logger.info("上传文件大小为:" + String.valueOf(zipFile.getSize()/1024) + "KB");
            Boolean flag = minioService.uploadObjectByTag(tag, objName, inputStream, contentType);
            if (flag) {
                result.put("msg", "上传成功");
                result.put("code", "0");
            } else {
                result.put("msg", "上传失败");
                result.put("code", "1");
            }
        } catch (IOException e) {
            logger.error("上传失败");
            e.printStackTrace();
        }
        return result;
    }
}
