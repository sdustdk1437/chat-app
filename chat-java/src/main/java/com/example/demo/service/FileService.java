package com.example.demo.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {

    public Map uploadFile(MultipartFile zipFile) {
        Map<String,String> result = new HashMap<>();
        String targetFilePath = "D:\\test\\uploadTest";
        String oldName = zipFile.getOriginalFilename();
        String imgType = oldName.substring(oldName.lastIndexOf("."));
        String name = UUID.randomUUID().toString().replace("-", "")+imgType;
        String path = targetFilePath + File.separator + name;
        File targetFile = new File(path);

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
            result.put("status","true");
            result.put("path","http://10.30.23.135"+path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
