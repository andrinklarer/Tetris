package com.google;

import java.util.concurrent.BlockingQueue;

public class AutoLower extends Thread {
    private BlockingQueue<Integer> bq;
    private Integer value = 1;

    public AutoLower(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            try {
                Thread.sleep(1000);
                bq.put(value);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
