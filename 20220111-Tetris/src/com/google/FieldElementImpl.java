package com.google;

import com.sun.jdi.Value;

import java.awt.*;

public class FieldElementImpl implements FieldElement {
    Color color = null;
    int value = 0;

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
