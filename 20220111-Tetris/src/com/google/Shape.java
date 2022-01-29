package com.google;

import java.awt.*;

public class Shape {
    Color color;
    int[][] shape;
    String desc;


    public Shape(int[][] shape, String desc, Color color) {
        this.shape = shape;
        this.desc = desc;
        this.color = color;
    }

    public Shape() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setX(int index, int x) {

        this.shape[index][0] = x;
    }

    public void setY(int index, int y) {

        this.shape[index][1] = y;
    }

    int x(int index, Shape shape) {

        return shape.getShape()[index][0];
    }

    int y(int index, Shape shape) {

        return shape.getShape()[index][1];
    }
}
