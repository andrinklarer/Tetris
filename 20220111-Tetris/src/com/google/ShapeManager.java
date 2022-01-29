package com.google;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ShapeManager {
    private final ArrayList<Shape> shapeList = new ArrayList<>();

    /**
     * This is the constructor, which fills all the list with shapes
     */
    public ShapeManager() {
        initShapes();
    }

    /**
     * This methode fills the list with the shapes
     */
    public void initShapes() {
        shapeList.add(new Shape(DefaultValues.getColorIBlock(), new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}, true));
        shapeList.add(new Shape(DefaultValues.getColorOBlock(), new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}}, false));
        shapeList.add(new Shape(DefaultValues.getColorJBlock(), new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorLBlock(), new int[][]{{-1, 1}, {0, -1}, {0, 0}, {0, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorZBlock(), new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorSBlock(), new int[][]{{0, -1}, {0, 0}, {-1, 0}, {-1, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorTBlock(), new int[][]{{-1, 0}, {0, 0}, {0, -1}, {0, 1}}, true));
    }

    /**
     * This method picks a random shape out of the list
     *
     * @return is the shape picked from the list
     */
    public Shape getShape() {
        Random random = new Random();
        return shapeList.get(random.nextInt(shapeList.size()));
    }
}

