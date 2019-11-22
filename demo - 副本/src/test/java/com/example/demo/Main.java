package com.example.demo;

import com.example.demo.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Main {

@Test
    public void main(){
        Util util = new Util();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            //直接输出机器人的回复
            System.err.println("Ta对你说->"+util.getMessage(scanner.nextLine()));
        }
    }
}
