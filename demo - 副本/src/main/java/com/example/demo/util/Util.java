package com.example.demo.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Util {
    public static final String API_KEY = "0d7bd7188d85437fb3b37f0b26ba8438";
    public static final String API_URL = "http://openapi.tuling123.com/openapi/api/v2";

    private String setParameter(String msg){
        try {
            return API_URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return null;
    }

    private String getString(String json){
        try {
            JSONObject object = new JSONObject(Boolean.parseBoolean(json));
            return object.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMessage(String msg){
        return getString(getHTML(setParameter(msg)));
    }

    private String getHTML(String url) {
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            //创建URL对象
            URL u = new URL(url);
            //打开连接
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            //从连接中拿到InputStream并由BufferedReader进行读取
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            //循环每次加入一行HTML内容 直到最后一行
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //结束时候关闭释放资源
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }



}
