package com.google;

import javax.swing.*;
import java.awt.*;

public class SingleShape extends JPanel {
    private Shape shape;
    private final int FIELD_SIZE = 5;

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

    public SingleShape() {
        this(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (shape != null) {
            for (int i = 0; i < shape.getShape().length; i++) {
                g.setColor(shape.getColor());
                g.fillRect((shape.getShape()[i][1] + (int) (FIELD_SIZE/2)) * this.getWidth() / FIELD_SIZE,
                        (shape.getShape()[i][0] + (int) (FIELD_SIZE/2)) * this.getHeight() / FIELD_SIZE,
                        this.getWidth() / FIELD_SIZE,this.getHeight() / FIELD_SIZE);
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
