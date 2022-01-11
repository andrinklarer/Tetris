package com.google;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.BlockingQueue;

public class Display extends JFrame implements KeyListener {
    private BlockingQueue<Integer> userInput;

    public Display(BlockingQueue<Integer> userInput) {
        setDefaultValues();
        this.userInput = userInput;
    }

    public void setDefaultValues() {
        this.setTitle("Tetris - Andrin, Jannik");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocusInWindow();
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            userInput.put(e.getKeyCode());
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
