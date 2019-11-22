package com.example.demo;

public class Man implements Runnable {
    private TestThread testThread = new TestThread();
    @Override
    public void run() {
        int m = 100;
        int i = 0;
        while (i<5){
            testThread.saveMoney(m);
            i++;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
