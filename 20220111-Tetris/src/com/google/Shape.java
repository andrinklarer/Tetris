package com.google;

import java.awt.*;

public class Shape {
    private Color color;
    private int[][] shape;
    private boolean hasRotationPoint;

    /**
     * This is the constructor, which sets the values of the shape
     *
     * @param color            is the shape color
     * @param shape            are the coordinates of the shape
     * @param hasRotationPoint is a boolean to determine if the shape can rotate
     */
    public Shape(Color color, int[][] shape, boolean hasRotationPoint) {
        this.color = color;
        this.shape = shape;
        this.hasRotationPoint = hasRotationPoint;
    }

    public boolean isHasRotationPoint() {
        return hasRotationPoint;
    }

    public void setHasRotationPoint(boolean hasRotationPoint) {
        this.hasRotationPoint = hasRotationPoint;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }
}
