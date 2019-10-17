package com.example.demo;

public class ConsumerInt implements Runnable {

    private Clerk clerk;
    public ConsumerInt(Clerk clerk){
        this.clerk = clerk;
    }
    public void run(){
        System.out.println("消费者开始消费整数。。。。。。。");
        for (int i=1;i<=10;i++){

            try {
                Thread.sleep((int)Math.random()*300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            clerk.getProduct();//从店员取走整数
        }
    }
}
