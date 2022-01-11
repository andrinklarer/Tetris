package com.google;

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
        Shape shape1 = new Shape(getShape(0), "Empty" );
        Shape shape2 = new Shape(getShape(1), "Square" );
        Shape shape3 = new Shape(getShape(2), "Line" );
        Shape shape4 = new Shape(getShape(3), "Mirrored L" );
        Shape shape5 = new Shape(getShape(4), "L" );
        Shape shape6 = new Shape(getShape(5), "Z" );
        Shape shape7 = new Shape(getShape(6), "S" );
        Shape shape8 = new Shape(getShape(7), "Mirrored T" );
        shapeList.addAll(Arrays.asList(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8));
    }

    public int[][] getShape(int index){
        int[][] x = new int[4][2];
        for (int i = 0; i < 4; i++){
            System.arraycopy(shapes[index][i], 0, x[i], 0, 2);
        }
        return new int[][] {{x[0][0],x[0][0],},{x[1][0],x[1][1],},{x[2][0],x[2][1],},{x[3][0],x[3][1],}};
    }
}
