package com.google;

import java.util.ArrayList;
import java.util.Random;

public class ShapeManager {
    private final ArrayList<Shape> shapeList = new ArrayList<>();

    public ShapeManager() {
        initShapes();
    }

    public void initShapes() {
        shapeList.add(new Shape(DefaultValues.getColorIBlock(), new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}, true));
        shapeList.add(new Shape(DefaultValues.getColorOBlock(), new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}}, false));
        shapeList.add(new Shape(DefaultValues.getColorJBlock(), new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorLBlock(), new int[][]{{-1, 1}, {0, -1}, {0, 0}, {0, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorZBlock(), new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorSBlock(), new int[][]{{0, -1}, {0, 0}, {-1, 0}, {-1, 1}}, true));
        shapeList.add(new Shape(DefaultValues.getColorTBlock(), new int[][]{{-1, 0}, {0, 0}, {0, -1}, {0, 1}}, true));
    }

    public Shape getShape() {
        Random random = new Random();
        return shapeList.get(random.nextInt(shapeList.size()));
    }
}
