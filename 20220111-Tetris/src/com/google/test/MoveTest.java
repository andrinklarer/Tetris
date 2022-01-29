package com.google.test;

import com.google.DefaultValues;
import com.google.Display;
import com.google.FieldElement;
import com.google.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTest {
    Display display;

    @BeforeEach
    void setUp() {
        display = new Display(true);
    }

    /**
     * This test checks if the current (falling) piece can move down or not
     */
    @Test
    void testMoveDown() {
        FieldElement[][] before = display.fieldClone();
        display.moveDown();
        FieldElement[][] after = display.fieldClone();
        boolean testPassed = true;

        for (int y = 0; y < before.length; y++) {
            for (int x = 0; x < before[0].length; x++) {
                //checks if the piece is the current piece and if so, it checks if the piece has moved down,
                // if it has not moved the test has failed
                if ((before[y][x].getValue() == DefaultValues.getValueRotationPiece() ||
                        before[y][x].getValue() == DefaultValues.getValueMovingPiece()) &&
                        !(after[y + 1][x].getValue() == before[y][x].getValue())) {
                    testPassed = false;
                    break;
                }
            }
            if (!testPassed) break;
        }
        assertTrue(testPassed);
    }

    /**
     * This test checks if the current (falling) piece can move to the left or not
     */
    @Test
    void testMoveLeft() {
        FieldElement[][] before = display.fieldClone();
        display.moveLeft();
        FieldElement[][] after = display.fieldClone();
        boolean testPassed = true;

        for (int y = 0; y < before.length; y++) {
            for (int x = 0; x < before[0].length; x++) {
                //checks if the piece is the current piece and if so, it checks if the piece has moved to the left,
                // if it has not moved the test has failed
                if ((before[y][x].getValue() == DefaultValues.getValueRotationPiece() ||
                        before[y][x].getValue() == DefaultValues.getValueMovingPiece()) &&
                        !(after[y][x - 1].getValue() == before[y][x].getValue())) {
                    testPassed = false;
                    break;
                }
            }
            if (!testPassed) break;
        }
        assertTrue(testPassed);
    }

    /**
     * This test checks if the current (falling) piece can move to the right or not
     */
    @Test
    void testMoveRight() {
        FieldElement[][] before = display.fieldClone();
        display.moveRight();
        FieldElement[][] after = display.fieldClone();
        boolean testPassed = true;

        for (int y = 0; y < before.length; y++) {
            for (int x = 0; x < before[0].length; x++) {
                //checks if the piece is the current piece and if so, it checks if the piece has moved to the right,
                // if it has not moved the test has failed
                if ((before[y][x].getValue() == DefaultValues.getValueRotationPiece() ||
                        before[y][x].getValue() == DefaultValues.getValueMovingPiece()) &&
                        !(after[y][x + 1].getValue() == before[y][x].getValue())) {
                    testPassed = false;
                    break;
                }
            }
            if (!testPassed) break;
        }
        assertTrue(testPassed);
    }

    /**
     * This test checks if the current (falling) piece can be rotated to the left (counter clockwise).
     */
    @Test
    void testRotateToTheLeft() {
        display.setDefaultValues();
        FieldElement[][] before = display.fieldClone();
        before[5][5].setValue(DefaultValues.getValueRotationPiece());
        before[4][5].setValue(DefaultValues.getValueMovingPiece());
        before[5][4].setValue(DefaultValues.getValueMovingPiece());
        display.setGrid(before);
        display.rotate(true);
        FieldElement[][] after = display.fieldClone();

        assertTrue(after[6][5].getValue() == DefaultValues.getValueMovingPiece()
                && after[5][4].getValue() == DefaultValues.getValueMovingPiece());
    }

    /**
     * This test checks if the current (falling) piece can be rotated to the right (clockwise).
     */
    @Test
    void testRotateToTheRight() {
        display.setDefaultValues();
        FieldElement[][] before = display.fieldClone();
        before[5][5].setValue(DefaultValues.getValueRotationPiece());
        before[6][5].setValue(DefaultValues.getValueMovingPiece());
        before[5][4].setValue(DefaultValues.getValueMovingPiece());
        display.setGrid(before);
        display.rotate(false);
        FieldElement[][] after = display.fieldClone();

        assertTrue(after[4][5].getValue() == DefaultValues.getValueMovingPiece()
                && after[5][4].getValue() == DefaultValues.getValueMovingPiece());
    }

    /**
     * This test checks if the current (falling) piece can be stored (hold).
     */
    @Test
    void testHoldShape() {
        Shape shapeBefore = display.getCurrentShape();
        display.holdPiece();
        assertEquals(shapeBefore, display.getHoldShapeComponent().getShape());
    }

    /**
     * This test checks if the drop method is working properly
     */
    @Test
    void testDrop() {
        display.setDefaultValues();
        FieldElement[][] before = display.fieldClone();
        before[5][5].setValue(DefaultValues.getValueRotationPiece());
        display.setGrid(before);
        display.getNextShapes()[0].setShape(display.getShapeManager().getShape());

        display.drop();
        FieldElement[][] after = display.fieldClone();

        assertTrue(after[after.length -1][5].getValue() == DefaultValues.getValueFilled()
                && after[5][5].getValue() == DefaultValues.getValueDefault());
    }

}
