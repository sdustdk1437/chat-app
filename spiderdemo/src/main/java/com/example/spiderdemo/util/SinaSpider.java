package com.example.spiderdemo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

public class SinaSpider implements Spider {
    private final static String TARGET_URL_PREFIX = "http://roll.finance.sina.com.cn/finance/gjs/hjzx/index_";

    private final static String FILE_NAME = "/GitProject/spiderdemo/src/main/java/com/example/spiderdemo/SinaGoldNews.csv";
    @Override
    public void run() throws InterruptedException {
        StringBuilder allNewses =new StringBuilder();
        WebDriver driver = MyWebDriver.createWebDriver();
        WebDriver newDriver = MyWebDriver.createWebDriver();

        for (int i = 1;i < 10;i++){
            driver.get(TARGET_URL_PREFIX + "index_" + i + ".shtml");
            Document document = Jsoup.parse(driver.getPageSource());

            int aTagCount = 0;//作为终点判断

            for (Element list : document.getElementsByClass("list_009")){
                for (Element a :list.getElementsByTag("a")){
                    aTagCount++;
                    try {
                        String href = a.attr("href");
                        newDriver.get(href);
                        Document newDocument = Jsoup.parse(newDriver.getPageSource());
                        Element timeSourceElement = newDocument.getElementsByClass("time-source").get(0);
                        String[] timeAndSource = timeSourceElement.text().split(" ");

                        String time = timeAndSource[0].substring(0, timeAndSource[0].indexOf("日")).replace('年', '-').replace('月', '-');
                        String source = timeAndSource[1];
                        String title = newDocument.getElementById("artibodyTitle").text();
                        String content = newDocument.getElementById("artibody").text();

                        allNewses.append(time + "," + href + "," + title + "," + content + "," + source + ",0\n");
                        System.out.println(href + "," + title);
                    }catch (Exception e){
                        continue;
                    }

                }

            }
            if (aTagCount == 0){
                    break;
            }
        }

        Thread.sleep(3000);
        driver.quit();
        newDriver.quit();

        MyFileWriter.writeString(FILE_NAME, allNewses.toString());
    }

    public static void main(String[] args) {
        try {
            new SinaSpider().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
