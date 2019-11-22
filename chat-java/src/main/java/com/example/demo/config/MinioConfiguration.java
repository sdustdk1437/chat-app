package com.example.demo.config;

import com.example.demo.enums.ContentTypeEnum;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinioConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MinioConfiguration.class.getName());

    @Value("${minio.endpointUpload}")
    private String endpointUpload;

    @Value("${minio.accessKey}")
    private String accessKey;

    //Secret key是你账户的密码。
    @Value("${minio.secretKey}")
    private String secretKey;

    //链接有效时间
    @Value("${minio.expires}")
    private int expires;

    //上传链接有效时间
    @Value("${minio.putExpires}")
    private int putExpires;

    /**
     * 获取minio连接
     *
     * @param point
     * @return
     */
    private MinioClient getMinioClient(String point) {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(point, accessKey, secretKey);
            if (minioClient != null) {
                logger.info("获取Minio链接成功！");
            }
        } catch (InvalidEndpointException | NullPointerException | InvalidPortException e) {
            logger.error("minio连接失败");
            e.printStackTrace();
        }
        return minioClient;
    }

    /**
     * 获取上传文件的url
     *
     * @param bucketName
     * @param objName
     * @return
     */
    public String getUploadUrl(String bucketName, String objName) {
        ifBucketExists(bucketName);
        String url = null;
        MinioClient client = getMinioClient(endpointUpload);

        try {
            url = client.presignedPutObject(bucketName, objName, putExpires);
            logger.debug("获取链接成功！");
        } catch (MinioException e) {
            logger.error("存储服务器异常。");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            logger.error("找不到相应的签名算法。");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            logger.error("XmlPullParserException");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            logger.error("不合法的access key或者secret key。");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("连接异常。",e.getMessage());
            e.printStackTrace();
        }

        return url;
    }

    public void ifBucketExists(String bucketName) {
        MinioClient minioClient = getMinioClient(endpointUpload);
        try {
            boolean flag = minioClient.bucketExists(bucketName);
            if (flag) {
                logger.info("bucket already exists");
            } else {
                try {
                    minioClient.makeBucket(bucketName);
                } catch (RegionConflictException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以流的形式上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param stream     流
     * @return 是否成功
     */
    public Boolean putObject(String bucketName, String objectName, InputStream stream, ContentTypeEnum contentType) {
        try {
            MinioClient client = getMinioClient(endpointUpload);
            ifBucketExists(bucketName);
            // 创建对象
            client.putObject(bucketName, objectName, stream, stream.available(), contentType.getContentType());

            stream.close();
            logger.debug("object is uploaded successfully");
            return true;
        } catch (MinioException e) {
            logger.error("存储服务器异常:");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            logger.error("找不到相应的签名算法。");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            logger.error("XmlPullParserException");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            logger.error("不合法的access key或者secret key。");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("连接异常。{}",e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 以路径的形式上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param fileName   文件路径
     * @return 是否成功
     */
    public Boolean putObject(String bucketName, String objectName, String fileName, ContentTypeEnum contentType) {
        MinioClient client = getMinioClient(endpointUpload);
        try {
            client.putObject(bucketName, objectName, fileName, contentType.getContentType());
            logger.debug("island.jpg is uploaded successfully");
            return true;
        } catch (MinioException e) {
            logger.error("存储服务器异常。");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            logger.error("找不到相应的签名算法。");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            logger.error("XmlPullParserException");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            logger.error("不合法的access key或者secret key。");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("连接异常。");
            e.printStackTrace();
        }
        return false;
    }
}
