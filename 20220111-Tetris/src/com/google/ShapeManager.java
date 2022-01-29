package com.google;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ShapeManager {
    private final ArrayList<Shape> shapeList = new ArrayList<>();

    public ShapeManager() {
        initShapes();
    }

    private final int[][][] shapes = new int[][][]{
            {{0,0},{0,0},{0,0},{0,0}},      // Empty
            {{0,0},{1,0},{1,1},{0,1}},      // Square
            {{0,-1},{0,0},{0,1},{0,2}},     // Line
            {{-1,-1},{0,-1},{0,0},{0,1}},   // Mirrored L
            {{1,-1},{0,-1},{0,0},{0,1}},    // L
            {{0,-1},{0,0},{1,0},{1,1}},     // Z
            {{0,-1},{0,0},{-1,0},{-1,1}},   // S
            {{-1,0},{0,0},{1,0},{0,1}}      // Mirrored T
    };

    public void initShapes(){
        Shape shape1 = new Shape(getShapeCoords(0), "Empty", Color.BLACK );
        Shape shape2 = new Shape(getShapeCoords(1), "Square", Color.YELLOW );
        Shape shape3 = new Shape(getShapeCoords(2), "Line", Color.BLUE );
        Shape shape4 = new Shape(getShapeCoords(3), "Mirrored L", Color.RED );
        Shape shape5 = new Shape(getShapeCoords(4), "L", Color.PINK);
        Shape shape6 = new Shape(getShapeCoords(5), "Z", Color.GREEN);
        Shape shape7 = new Shape(getShapeCoords(6), "S", Color.CYAN);
        Shape shape8 = new Shape(getShapeCoords(7), "Mirrored T", Color.CYAN );
        shapeList.addAll(Arrays.asList(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8));
    }

    public int[][] getShapeCoords(int index){
        int[][] x = new int[4][2];
        for (int i = 0; i < 4; i++){
            System.arraycopy(shapes[index][i], 0, x[i], 0, 2);
        }
        return new int[][] {{x[0][0],x[0][1],},{x[1][0],x[1][1],},{x[2][0],x[2][1],},{x[3][0],x[3][1],}};
    }




    public Shape rotateLeft(Shape shape){
        if (shape.getDesc().equals("Square")){
            return shapeList.get(0);
        }

        Shape turned = new Shape();
        turned.setShape(shape.getShape());
        turned.setColor(shape.getColor());
        turned.setDesc(shape.getDesc());

        for (int i = 0; i < 4; i++) {
            turned.setX(i, shape.y(i, shape));
            turned.setY(i, -shape.x(i, shape));
        }
        return turned;
    }
}

