package com.google;

import java.awt.event.MouseListener;

public class Manager {
    private MoveController moveController;
    private UserInput userInput;
    private AutoLower autoLower;
    private Display display;

    public Manager() {
        moveController = new MoveController();
        moveController.start();

        userInput = new UserInput(moveController.getNextMove());
        userInput.start();

        autoLower = new AutoLower(moveController.getNextMove());
        autoLower.start();

        display = new Display(userInput.getUserInput());
    }
}
