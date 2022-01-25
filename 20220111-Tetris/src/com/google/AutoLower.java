package com.google;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoLower extends Thread {
    private BlockingQueue<Integer> nextMove;
    private AtomicInteger time;
    private final int START_TIME = 1000;

    /**
     * This is the constructor, which sets the default values
     *
     * @param nextMove is the queue where the key press is added to
     */
    public AutoLower(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
        time = new AtomicInteger(START_TIME);
    }

    /**
     * While the tetris is running, this method adds a key in specific intervals to the list of inputs
     * to automatically lower the current piece. The intervals change when more rows are cleared.
     */
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
