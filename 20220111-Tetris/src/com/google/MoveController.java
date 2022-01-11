package com.google;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MoveController extends Thread {
    private BlockingQueue<Integer> nextMove;

    public MoveController() {
        nextMove = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                int input = nextMove.take();
                switch ((char) input) {
                    case 'A' -> moveLeft();
                    case 'S' -> moveDown();
                    case 'D' -> moveRight();
                    case 'Q' -> rotateLeft();
                    case 'E' -> rotateRight();
                    case ' ' -> drop();
                    default -> {
                    }
                }
                System.out.println(input + " ");
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void moveLeft() {

    }

    private void moveRight() {

    }

    private void moveDown() {

    }

    private void drop() {

    }

    private void rotateLeft() {

    }

    private void rotateRight() {

    }


    public BlockingQueue<Integer> getNextMove() {
        return nextMove;
    }

    public void setNextMove(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
    }
}
