package com.example.demo.service;

import com.example.demo.entity.FileInfo;
import com.example.demo.repository.FileRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {
    @Resource
    FileRepository fileRepository;

    public Map uploadFile(MultipartFile zipFile) {
        Map<String, String> result = new HashMap<>();
        String filePath = "D:\\test\\uploadTest";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String fileName = null;
        if (zipFile != null && zipFile.getSize() > 0) {
            fileName = UUID.randomUUID().toString() + zipFile.getOriginalFilename();
            try {
                inputStream = zipFile.getInputStream();
                fileOutputStream = new FileOutputStream(new File(filePath + "/" + fileName));
                IOUtils.copy(inputStream, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setBeginFileName(zipFile.getOriginalFilename());
        fileInfo.setFileSize(zipFile.getSize());
        fileInfo.setFileType(zipFile.getContentType());
        fileInfo.setLastFileName(fileName);
        fileRepository.save(fileInfo);
        result.put("msg", "上传成功");
        result.put("code", "0");
        result.put("path", "http://localhost:8080/images/" + fileName);
        return result;
    }
}
