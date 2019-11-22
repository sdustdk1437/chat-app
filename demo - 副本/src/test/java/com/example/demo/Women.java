package com.example.demo;

public class Women implements Runnable {
    private TestThread testThread = new TestThread();
    @Override
    public void run() {
        int m = 100;
        int i = 0;
        while (i<5){
            testThread.drawMoney(m);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            i++;
        }
    }
}
