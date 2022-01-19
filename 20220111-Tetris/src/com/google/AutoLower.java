package com.google;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoLower extends Thread {
    private BlockingQueue<Integer> nextMove;
    private AtomicInteger time;
    private final int START_TIME = 1000;

    public AutoLower(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
        time = new AtomicInteger(START_TIME);
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            try {
                Thread.sleep(time.get());
                nextMove.put((int) 's');
            } catch (InterruptedException ignored) {
            }
        }
    }


    public AtomicInteger getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time.set(time);
    }
}
