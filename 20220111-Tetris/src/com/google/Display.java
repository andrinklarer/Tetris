package com.google;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Display extends JFrame {
    private BlockingQueue<Integer> nextMove;

    private InputController inputController;
    private AutoLower autoLower;
    private ShapeManager shapeManager;
    private PlayingField playingField;
    private Score score;
    private int clearedRows;

    private SingleShape[] nextShapes;
    private SingleShape holdShapeComponent;

    private Shape currentShape;
    private boolean canHoldShape;

    private FieldElement[][] grid;
    private int PLAYING_FIELD_HEIGHT = 23;
    private int PLAYING_FIELD_WIDTH = 10;

    private boolean isPlaying;
    private boolean update;

    /**
     * This is the constructor, which sets the default values and starts the input controller and auto lower
     */
    public Display() {
        grid = new FieldElement[PLAYING_FIELD_HEIGHT][PLAYING_FIELD_WIDTH];
        isPlaying = true;
        clearedRows = 0;
        canHoldShape = true;
        nextShapes = new SingleShape[3];
        nextMove = new LinkedBlockingQueue<>();

        inputController = new InputController(nextMove);
        inputController.start();

        autoLower = new AutoLower(nextMove);
        autoLower.start();

        shapeManager = new ShapeManager();

        manager();
    }

    /**
     * This method manages the values of the display, which have to be set before the game starts
     */
    public void manager() {
        setupPlayingField();
        setDefaultValues();
        loadNextPiece();
        play();
    }

    /**
     * This method fills the next piece list and calls the addNewPiece method
     */
    public void loadNextPiece() {
        for (SingleShape nextShape : nextShapes) nextShape.setShape(shapeManager.getShape());
        while (nextShapes[nextShapes.length - 1].getShape().equals(nextShapes[nextShapes.length - 2].getShape()))
            nextShapes[nextShapes.length - 1].setShape(shapeManager.getShape());

        addNewPiece();
    }

    /**
     * This method sets the default display options and values
     */
    public void setDefaultValues() {
        JPanel screen = new JPanel(new BorderLayout());

        GridLayout nextComponentLayout = new GridLayout(3, 1);
        nextComponentLayout.setVgap(10);

        JPanel holdComponent = new JPanel(nextComponentLayout);
        holdComponent.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        holdComponent.add(holdShapeComponent = new SingleShape());

        JPanel nextComponent = new JPanel(nextComponentLayout);
        nextComponent.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        nextComponent.add(nextShapes[0] = new SingleShape());
        nextComponent.add(nextShapes[1] = new SingleShape());
        nextComponent.add(nextShapes[2] = new SingleShape());

        screen.add(score = new Score(), BorderLayout.NORTH);
        screen.add(playingField = new PlayingField(grid), BorderLayout.CENTER);
        screen.add(nextComponent, BorderLayout.EAST);
        screen.add(holdComponent, BorderLayout.WEST);

        this.setTitle("Tetris - Andrin, Jannik");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocusInWindow();
        this.add(screen);
        addKeyListener(inputController);
    }

    /**
     * This method sets the grid elements to the default values
     */
    public void setupPlayingField() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new FieldElement(DefaultValues.getColorDefault(), DefaultValues.getValueDefault());
            }
        }
    }

    /**
     * This method manages the actions that are performed while the game is running
     */
    public void play() {
        while (isPlaying) {
            try {
                int input = nextMove.take();
                switch ((char) input) {
                    case 'A' -> moveLeft();
                    case 's' -> validateMoveDown();
                    case 'S' -> {
                        if (!hitFloor()) score.setScore(score.getScore().get() + 1);
                        validateMoveDown();
                    }
                    case 'D' -> moveRight();
                    case 'Q' -> rotate(true);
                    case 'E' -> rotate(false);
                    case 'W' -> holdPiece();
                    case ' ' -> drop();
                    default -> isPlaying = false;
                }
                if (update) {
                    playingField.updateField(grid);
                    update = false;
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * This method removes and stores the current (falling) piece and replaces it with the stored piece
     * or, if the stored piece isn't set, with the next piece
     */
    public void holdPiece() {
        if (canHoldShape) {
            removeCurrentPiece();
            Shape tmpShape = holdShapeComponent.getShape();
            holdShapeComponent.setShape(currentShape);
            if (tmpShape == null) {
                addNewPiece();
            } else {
                currentShape = tmpShape;
                loadShape();
            }
            canHoldShape = false;
        }
    }

    /**
     * This method clears the current (falling) piece and replaces it with default values
     */
    public void removeCurrentPiece() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x].getValue() == DefaultValues.getValueMovingPiece()
                        || grid[y][x].getValue() == DefaultValues.getValueRotationPiece()) {
                    grid[y][x].setColor(DefaultValues.getColorDefault());
                    grid[y][x].setValue(DefaultValues.getValueDefault());
                }
            }
        }
    }

    /**
     * This method moves the current (falling) piece down until it hits the floor
     */
    public void drop() {
        int points = 0;
        while (!hitFloor()) {
            validateMoveDown();
            points += 2;
        }
        validateMoveDown();
        score.setScore(score.getScore().get() + points);
    }

    /**
     * This method rotates the current (falling) piece to either side, depending on the parameter
     *
     * @param isLeftTurn is a boolean, which is used to determine if the rotation is to the left or right
     */
    public void rotate(boolean isLeftTurn) {
        ArrayList<int[]> movingPieces = new ArrayList<>();
        int[] rotationPosition = new int[]{-1};
        FieldElement[][] newField = fieldClone();

        //getValues
        for (int y = 0; y < newField.length; y++) {
            for (int x = 0; x < newField[0].length; x++) {
                if (newField[y][x].getValue() == DefaultValues.getValueRotationPiece()) {
                    rotationPosition = new int[]{y, x};
                } else if (newField[y][x].getValue() == DefaultValues.getValueMovingPiece()) {
                    movingPieces.add(new int[]{y, x});
                    newField[y][x].setColor(DefaultValues.getColorDefault());
                    newField[y][x].setValue(DefaultValues.getValueDefault());
                }
            }
        }

        if (rotationPosition[0] == -1) return;
        update = true;

        //calculate new position
        for (int[] piece : movingPieces) {
            int[] tmp = piece.clone();

            if (isLeftTurn)
                tmp = new int[]{rotationPosition[0] + -(tmp[1] - rotationPosition[1]),
                        rotationPosition[1] + (tmp[0] - rotationPosition[0])};
            else
                tmp = new int[]{rotationPosition[0] + (tmp[1] - rotationPosition[1]),
                        rotationPosition[1] + -(tmp[0] - rotationPosition[0])};

            if (tmp[0] < 0 || tmp[0] >= PLAYING_FIELD_HEIGHT || tmp[1] < 0 || tmp[1] >= PLAYING_FIELD_WIDTH ||
                    newField[tmp[0]][tmp[1]].getValue() == DefaultValues.getValueFilled()) {
                update = false;
                return;
            }

            piece[0] = tmp[0];
            piece[1] = tmp[1];
        }

        //set values
        for (int[] position : movingPieces) {
            newField[position[0]][position[1]].setColor(newField[rotationPosition[0]][rotationPosition[1]].getColor());
            newField[position[0]][position[1]].setValue(DefaultValues.getValueMovingPiece());
        }

        grid = newField;
    }

    /**
     * This method makes a copy by the values of the field
     *
     * @return is a copy of the current field
     */
    public FieldElement[][] fieldClone() {
        FieldElement[][] clone = new FieldElement[PLAYING_FIELD_HEIGHT][PLAYING_FIELD_WIDTH];

        for (int y = 0; y < clone.length; y++) {
            for (int x = 0; x < clone[0].length; x++) {
                clone[y][x] = new FieldElement(grid[y][x].getColor(), grid[y][x].getValue());
            }
        }

        return clone;
    }

    /**
     * This method checks if the current (falling) piece can move to the right and if so moves the piece to the right
     */
    public void moveRight() {
        FieldElement[][] newField = fieldClone();
        update = true;

        for (FieldElement[] newFieldRow : newField) {
            for (int x = newField[0].length - 1; x >= 0; x--) {
                if (newFieldRow[x].getValue() == DefaultValues.getValueMovingPiece()
                        || newFieldRow[x].getValue() == DefaultValues.getValueRotationPiece()) {
                    if (x + 1 == newField[0].length || newFieldRow[x + 1].getValue() == DefaultValues.getValueFilled()) {
                        update = false;
                        return;
                    } else {
                        newFieldRow[x + 1].setValue(newFieldRow[x].getValue());
                        newFieldRow[x + 1].setColor(newFieldRow[x].getColor());

                        newFieldRow[x].setValue(DefaultValues.getValueDefault());
                        newFieldRow[x].setColor(DefaultValues.getColorDefault());
                    }
                }
            }
        }

        grid = newField;
    }

    /**
     * This method checks if the current (falling) piece can move down, if not freezes it in position
     * and checks if the game is over. If the game isn't over a new piece will be added.
     * If the piece does not hit the floor, it will be move down
     */
    public void validateMoveDown() {
        if (hitFloor()) {
            freezePiece();
            clearRows();
            if (isGameOver()) {
                isPlaying = false;
            } else {
                addNewPiece();
            }
        } else {
            moveDown();
        }
    }

    /**
     * This method checks if the current (falling) piece can move to the left and if so moves the piece to the left
     */
    public void moveLeft() {
        FieldElement[][] newField = fieldClone();
        update = true;

        for (FieldElement[] newFieldRow : newField) {
            for (int x = 0; x < newField[0].length; x++) {
                if (newFieldRow[x].getValue() == DefaultValues.getValueMovingPiece()
                        || newFieldRow[x].getValue() == DefaultValues.getValueRotationPiece()) {
                    if (x - 1 < 0 || newFieldRow[x - 1].getValue() == DefaultValues.getValueFilled()) {
                        update = false;
                        break;
                    } else {
                        newFieldRow[x - 1].setValue(newFieldRow[x].getValue());
                        newFieldRow[x - 1].setColor(newFieldRow[x].getColor());

                        newFieldRow[x].setValue(DefaultValues.getValueDefault());
                        newFieldRow[x].setColor(DefaultValues.getColorDefault());
                    }
                }
            }
            if (!update) break;
        }
        if (update) grid = newField;
    }

    /**
     * This method moves the current (falling) piece down a block
     */
    public void moveDown() {
        for (int y = grid.length - 1; y >= 0; y--) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x].getValue() == DefaultValues.getValueMovingPiece()
                        || grid[y][x].getValue() == DefaultValues.getValueRotationPiece()) {
                    grid[y + 1][x].setValue(grid[y][x].getValue());
                    grid[y + 1][x].setColor(grid[y][x].getColor());

                    grid[y][x].setValue(DefaultValues.getValueDefault());
                    grid[y][x].setColor(DefaultValues.getColorDefault());
                }
            }
        }
        update = true;
    }

    /**
     * This method freezes the current (falling) piece in position
     */
    public void freezePiece() {
        for (FieldElement[] fieldRow : grid) {
            for (int x = 0; x < grid[0].length; x++) {
                if (fieldRow[x].getValue() == DefaultValues.getValueMovingPiece()
                        || fieldRow[x].getValue() == DefaultValues.getValueRotationPiece()) {
                    fieldRow[x].setValue(DefaultValues.getValueFilled());
                }
            }
        }
    }

    /**
     * This method checks if the current (falling) piece can move down
     *
     * @return if the current (falling) piece can move down return false,
     * if it hit a filled block of the floor returns true
     */
    public boolean hitFloor() {
        for (int y = grid.length - 1; y >= 0; y--) {
            for (int x = 0; x < grid[0].length; x++) {
                if ((grid[y][x].getValue() == DefaultValues.getValueMovingPiece()
                        || grid[y][x].getValue() == DefaultValues.getValueRotationPiece()) &&
                        (y + 1 == grid.length || grid[y + 1][x].getValue() == DefaultValues.getValueFilled())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method checks if the game is over
     *
     * @return if a filled piece is above a specific level returns true else false
     */
    public boolean isGameOver() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x].getValue() == DefaultValues.getValueFilled()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks if the rows are filled and if so clears it
     */
    public void clearRows() {
        int counter = 0;
        boolean rowIsCleared = true;
        for (int y = grid.length - 1; y >= 0; y--) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x].getValue() != DefaultValues.getValueFilled()) {
                    rowIsCleared = false;
                }
            }
            if (rowIsCleared) {
                counter++;
                moveRowsDown(y);
                y++;
            } else rowIsCleared = true;
        }
        clearedRows += counter;
        updateScore(counter);
        updateDelay();
    }

    /**
     * This method shortens the delay when a certain level is reached
     */
    public void updateDelay() {
        if (clearedRows >= 0 && clearedRows < 100) {
            if (autoLower.getTime().get() != DefaultValues.getSpeedVeryEasy()) {
                autoLower.setTime(DefaultValues.getSpeedVeryEasy());
            }
        } else if (clearedRows >= 100 && clearedRows < 120) {
            if (autoLower.getTime().get() != DefaultValues.getSpeedEasy()) {
                autoLower.setTime(DefaultValues.getSpeedEasy());
            }
        } else if (clearedRows >= 120 && clearedRows < 150) {
            if (autoLower.getTime().get() != DefaultValues.getSpeedMedium()) {
                autoLower.setTime(DefaultValues.getSpeedMedium());
            }
        } else if (clearedRows >= 150 && clearedRows < 200) {
            if (autoLower.getTime().get() != DefaultValues.getSpeedHard()) {
                autoLower.setTime(DefaultValues.getSpeedHard());
            }
        } else if (clearedRows >= 200) {
            if (autoLower.getTime().get() != DefaultValues.getSpeedVeryHard()) {
                autoLower.setTime(DefaultValues.getSpeedVeryHard());
            }
        }

    }

    /**
     * This method increases the score of the player, depending on the level of the game
     *
     * @param counter is the amount of rows cleared
     */
    public void updateScore(int counter) {
        int points = 0;
        switch (counter) {
            case 1 -> points = (((int) clearedRows / 10) + 1) * 40;
            case 2 -> points = (((int) clearedRows / 10) + 1) * 100;
            case 3 -> points = (((int) clearedRows / 10) + 1) * 300;
            case 4 -> points = (((int) clearedRows / 10) + 1) * 1200;
            default -> {
                return;
            }
        }
        score.setScore(score.getScore().get() + points);
        score.setLevel(clearedRows / 10 + 1);
    }

    /**
     * This method moves all rows above one row down
     *
     * @param currentRow the row which got cleared
     */
    public void moveRowsDown(int currentRow) {
        for (int y = currentRow; y > 0; y--) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[y][x].setValue(grid[y - 1][x].getValue());
                grid[y][x].setColor(grid[y - 1][x].getColor());
            }
        }
    }

    /**
     * Adds the piece on top of the next piece row to the field and adds shifts the other pieces up.
     * The last piece of the list gets generated new.
     */
    public void addNewPiece() {
        currentShape = nextShapes[0].getShape();
        canHoldShape = true;

        for (int i = 0; i < nextShapes.length - 1; i++) {
            nextShapes[i].setShape(nextShapes[i + 1].getShape());
        }
        nextShapes[nextShapes.length - 1].setShape(shapeManager.getShape());
        while (nextShapes[nextShapes.length - 1].getShape().equals(nextShapes[nextShapes.length - 2].getShape())) {
            nextShapes[nextShapes.length - 1].setShape(shapeManager.getShape());
        }
        loadShape();
    }

    /**
     * This method sets the current (falling) piece to the grid
     * and moves piece down to be visible for the user if possible
     */
    public void loadShape() {
        int[] startPosition = new int[]{1, 4};

        for (int i = 0; i < currentShape.getShape().length; i++) {
            grid[currentShape.getShape()[i][0] + startPosition[0]][currentShape.getShape()[i][1] + startPosition[1]]
                    .setValue(DefaultValues.getValueMovingPiece());
            grid[currentShape.getShape()[i][0] + startPosition[0]][currentShape.getShape()[i][1] + startPosition[1]]
                    .setColor(currentShape.getColor());
        }

        if (currentShape.isHasRotationPoint())
            grid[startPosition[0]][startPosition[1]].setValue(DefaultValues.getValueRotationPiece());

        for (int i = 0; i < 2; i++) {
            if (!hitFloor()) validateMoveDown();
        }
    }
}
