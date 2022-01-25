package com.google;

import java.awt.*;

public class FieldElement {
    private Color color;
    private int value;

    /**
     * This is the constructor which creates a new Element
     *
     * @param color is the color of the element
     * @param value is the value of the element
     */
    public FieldElement(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
