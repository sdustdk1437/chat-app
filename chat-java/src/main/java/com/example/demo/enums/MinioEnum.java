package com.example.demo.enums;

public enum MinioEnum {
    IMAGE("image1"),
    NULL("nil");

    private String name;

    MinioEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static MinioEnum getEnum(String name) {
        for (MinioEnum e : MinioEnum.values()) {
            if (e.getName() == name) {
                return e;
            }
        }
        return NULL;
    }
}
