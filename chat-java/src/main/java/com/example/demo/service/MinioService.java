package com.example.demo.service;

import com.example.demo.config.MinioConfiguration;
import com.example.demo.enums.ContentTypeEnum;
import com.example.demo.enums.MinioEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Objects;

@Service
public class MinioService {
    private static final Logger logger = LoggerFactory.getLogger(MinioService.class.getName());

    @Value("${minio.bucketName.imageBucketName}")
    private String imageBucketName;

    @Resource
    private MinioConfiguration minioConfiguration;

    /**
     * 获取上传对象地址
     *
     * @param tag
     * @param objName
     * @return
     */
    public String getUploadUrl(String tag, String objName) {
        String bucketName = getMinioBucketName(tag);
        if (bucketName != null) {
            return minioConfiguration.getUploadUrl(bucketName, objName);
        }
        return null;
    }

    /**
     * 根据tag上传文件
     * @param tag
     * @param objName
     * @param stream
     * @param contentTypeEnum
     * @return
     */
    public Boolean uploadObjectByTag(String tag, String objName, InputStream stream, ContentTypeEnum contentTypeEnum) {
        String bucketName = getMinioBucketName(tag);

        if (bucketName != null) {
            return minioConfiguration.putObject(bucketName, objName, stream, contentTypeEnum);
        }
        return false;
    }

    public String getMinioBucketName(String tag) {
        switch (Objects.requireNonNull(MinioEnum.getEnum(tag))) {
            case IMAGE: {
                return imageBucketName;
            }
            default: {
                logger.info("参数错误没有匹配项");
                return null;
            }
        }
    }
}
