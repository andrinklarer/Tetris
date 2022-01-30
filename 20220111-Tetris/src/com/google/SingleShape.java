package com.google;

import javax.swing.*;
import java.awt.*;

public class SingleShape extends JPanel {
    private Shape shape;
    private final int FIELD_SIZE = 5;

    /**
     * This is the constructor, which sets the default values of the display and shape to display
     *
     * @param shape is the shape that's set
     */
    public SingleShape(Shape shape) {
        this.shape = shape;
        this.setBackground(DefaultValues.getColorDefault());
        this.setPreferredSize(new Dimension(FIELD_SIZE * DefaultValues.getUnitSize(),
                FIELD_SIZE * DefaultValues.getUnitSize()));
        this.setSize(FIELD_SIZE * DefaultValues.getUnitSize(),
                FIELD_SIZE * DefaultValues.getUnitSize());
        this.setVisible(true);
        this.requestFocusInWindow();
    }

    /**
     * This is constructor used if the component does not have a shape from the start.
     * This constructor calls the other this set the default values.
     */
    public SingleShape() {
        this(null);
    }

    /**
     * This method paints the component with the new grid
     *
     * @param g is the graphic of the JPanel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * This method sets the grid values to the field and adds lines to make the field more visible
     *
     * @param g is the graphic of the JPanel
     */
    public void draw(Graphics g) {
        if (shape != null) {
            for (int i = 0; i < shape.getShape().length; i++) {
                g.setColor(shape.getColor());
                g.fillRect((shape.getShape()[i][1] + (int) (FIELD_SIZE / 2)) * this.getWidth() / FIELD_SIZE,
                        (shape.getShape()[i][0] + (int) (FIELD_SIZE / 2)) * this.getHeight() / FIELD_SIZE,
                        this.getWidth() / FIELD_SIZE, this.getHeight() / FIELD_SIZE);
            }
        }

        for (int i = 0; i < this.getHeight() / FIELD_SIZE; i++) {
            g.setColor(new Color(0xFFFFFF));
            g.drawLine(i * this.getWidth() / FIELD_SIZE, 0,
                    i * this.getWidth() / FIELD_SIZE, this.getHeight());
            g.drawLine(0, i * this.getHeight() / FIELD_SIZE,
                    this.getWidth(), i * this.getHeight() / FIELD_SIZE);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        paintComponent(this.getGraphics());
    }
}
