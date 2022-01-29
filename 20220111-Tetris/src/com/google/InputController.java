package com.google;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.BlockingQueue;

public class InputController extends Thread implements KeyListener {
    private BlockingQueue<Integer> nextMove;

    /**
     * This is the constructor, which links the queue
     *
     * @param nextMove is the queue which is used to add the key values
     */
    public InputController(BlockingQueue<Integer> nextMove) {
        this.nextMove = nextMove;
    }

    /**
     * This method filters all the key presses, which are unnecessary
     * and adds the used key presses to the list of inputs
     *
     * @param e is the key, which got pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        try {
            switch ((char) e.getKeyCode()) {
                // move left    = 'A', move down    = 'S', move right   = 'D',
                // rotate left  = 'Q', rotate right = 'E', drop         = ' ',
                // hold piece   = 'W'
                case 'A', 'S', 'D', 'Q', 'E', 'W', ' ' -> nextMove.put(e.getKeyCode());
                default -> {
                }
            }
        } catch (InterruptedException ignored) {
        }
    }


    /**
     * This method is not used but has to be implemented
     *
     * @param e the key, which got typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method is not used but has to be implemented
     *
     * @param e the key, which got released
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
