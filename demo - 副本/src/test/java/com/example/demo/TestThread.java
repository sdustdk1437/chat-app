package com.example.demo;

public class TestThread {
    private static int money;

    public int getMoney(){
        return money;
    }

    public void saveMoney(int m){
        synchronized (this){
            System.out.println("存钱后的总金额为"+(money+=m));
        }
    }

    public void drawMoney(int m){
        synchronized (this){
            TestThread testThread = new TestThread();
            if (testThread.getMoney()<0){
                System.out.println("没得钱取个pi");
            }else {
                System.out.println("取钱后剩余的总金额："+(money-=m));
            }
        }
    }

    public static void main(String[] ags){
        Man m1 = new Man();
        Women w = new Women();
        Thread t1 = new Thread(m1);
        Thread t2 = new Thread(m1);
        Thread t3 = new Thread(m1);
        Thread t4 = new Thread(w);
        Thread t5 = new Thread(w);
        Thread t6 = new Thread(w);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
