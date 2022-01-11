package com.google;

import java.util.concurrent.BlockingQueue;

public class AutoLower extends Thread {
    private BlockingQueue<Integer> nextMove;

    public AutoLower(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            try {
                Thread.sleep(2000);
                nextMove.put((int) 'S');
            } catch (InterruptedException ignored) {
            }
        }
    }
}
