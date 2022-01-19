package com.google;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.BlockingQueue;

public class InputController extends Thread implements KeyListener {
    private BlockingQueue<Integer> nextMove;

    public InputController(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            switch ((char) e.getKeyCode()) {
                // move left    = 'A', move down    = 'S', move right   = 'D',
                // rotate left  = 'Q', rotate right = 'E', drop         = ' '
                case 'A', 'S', 'D', 'Q', 'E','W', ' ' -> nextMove.put(e.getKeyCode());
                default -> {
                }
            }
        } catch (InterruptedException ignored) {
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
