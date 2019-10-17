package com.example.demo;

import com.example.demo.util.Util;

import java.util.Scanner;

public class Clerk {
    private int product = -1;

    //这个方法有生产者调用
    public synchronized void setProduct(int product){
        if (this.product != -1){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.product = product;
        System.out.printf("生产者设定(%d)%n",this.product);
        notify();
    }


    public synchronized int getProduct(){
        if (this.product == -1){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        int p = this.product;
        System.out.printf("消费者取走(%d)%n",this.product);
        this.product = -1;
        notify();
        return p;
    }

  /*  public static void main(String[] args){
        Clerk clerk = new Clerk();
        new Thread(new ProducerInt(clerk)).start();
        new Thread(new ConsumerInt(clerk)).start();
    }*/

    public static void main(String[] args){
        Util util = new Util();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            //直接输出机器人的回复
            System.err.println("Ta对你说->"+util.getMessage(scanner.nextLine()));
        }
    }
}
