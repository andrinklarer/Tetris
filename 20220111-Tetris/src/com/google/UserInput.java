package com.google;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UserInput extends Thread {
    private BlockingQueue<Integer> userInput;
    private BlockingQueue<Integer> nextMove;

    public UserInput(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
        userInput = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                int input = userInput.take();
                // move left    = 'A', move down    = 'S', move right   = 'D',
                // rotate left  = 'Q', rotate right = 'E', drop         = ' '
                switch ((char) input) {
                    case 'A', 'S', 'D', 'Q', 'E', ' ' ->
                            nextMove.put(input);
                    default -> {}
                }
            } catch (InterruptedException ignored) {}
        }
    }

    public BlockingQueue<Integer> getUserInput() {
        return userInput;
    }

    public void setUserInput(BlockingQueue<Integer> userInput) {
        this.userInput = userInput;
    }
}
