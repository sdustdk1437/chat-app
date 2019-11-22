package com.example.spiderdemo.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyWebDriver {
    private final static String CHROME_DRIVER_PATH = "/GitProject/spiderdemo/src/main/java/com/example/spiderdemo/chromedriver";

    public static WebDriver createWebDriver(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        return new ChromeDriver();
    }
}
