package com.example.spiderdemo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {

    public static void writeString(String fileName,String content){
        FileWriter fileWriter = null;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file);
                fileWriter.write(content);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }


        }
    }
}
