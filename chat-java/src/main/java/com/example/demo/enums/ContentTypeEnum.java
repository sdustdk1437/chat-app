package com.example.demo.enums;

public enum ContentTypeEnum {

    IMAGE_JPG("image/JPG",".JPG"), //jpg格式
    IMAGE_BMP("application/x-bmp",".BMP"), //bmp格式
    IMAGE_JPEG("image/JPEG",".JPEG"), //jpeg格式
    IMAGE_PNG("image/PNG",".PNG"), //jpg格式
    IMAGE_DXF("application/x-dxf",".DXF"), //dxf格式
    IMAGE_TIFF("image/TIFF",".TIFF"), //tiff格式
    IMAGE_PCX("application/x-pcx",".PCX"), //pcx格式
    APK("application/x-silverlight-app",".APK"), //apk格式
    NIL("application/octet-stream",null); //空

    private String contentType;

    private String suffix;

    ContentTypeEnum(String contentType,String suffix) {
        this.contentType = contentType;
        this.suffix = suffix;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static ContentTypeEnum getApplicationType(String contentType) {
        if (contentType == null) {
            return null;
        }

        for (ContentTypeEnum e : ContentTypeEnum.values()) {
            if (e.getSuffix().equals(contentType.toUpperCase())) {
                return e;
            }
        }

        return NIL;
    }
}
