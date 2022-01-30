package com.google;

import java.awt.*;

public class DefaultValues {
    private final static Color COLOR_DEFAULT = new Color(0x000);
    private final static Color COLOR_I_BLOCK = new Color(0x00f0f0);
    private final static Color COLOR_J_BLOCK = new Color(0x0000f0);
    private final static Color COLOR_L_BLOCK = new Color(0xf0a000);
    private final static Color COLOR_O_BLOCK = new Color(0xf0f000);
    private final static Color COLOR_S_BLOCK = new Color(0x00f000);
    private final static Color COLOR_T_BLOCK = new Color(0xa000f0);
    private final static Color COLOR_Z_BLOCK = new Color(0xf00000);

    private final static Integer VALUE_DEFAULT = 0;
    private final static Integer VALUE_FILLED = 1;
    private final static Integer VALUE_MOVING_PIECE = 2;
    private final static Integer VALUE_ROTATION_PIECE = 3;

    private final static Integer SPEED_VERY_EASY = 1000;
    private final static Integer SPEED_EASY = 750;
    private final static Integer SPEED_MEDIUM = 500;
    private final static Integer SPEED_HARD = 300;
    private final static Integer SPEED_VERY_HARD = 150;

    private final static Integer UNIT_SIZE = 25;

    public static Color getColorDefault() {
        return COLOR_DEFAULT;
    }

    public static Color getColorIBlock() {
        return COLOR_I_BLOCK;
    }

    public static Color getColorJBlock() {
        return COLOR_J_BLOCK;
    }

    public static Color getColorLBlock() {
        return COLOR_L_BLOCK;
    }

    public static Color getColorOBlock() {
        return COLOR_O_BLOCK;
    }

    public static Color getColorSBlock() {
        return COLOR_S_BLOCK;
    }

    public static Color getColorTBlock() {
        return COLOR_T_BLOCK;
    }

    public static Color getColorZBlock() {
        return COLOR_Z_BLOCK;
    }

    public static Integer getValueDefault() {
        return VALUE_DEFAULT;
    }

    public static Integer getValueFilled() {
        return VALUE_FILLED;
    }

    public static Integer getValueMovingPiece() {
        return VALUE_MOVING_PIECE;
    }

    public static Integer getValueRotationPiece() {
        return VALUE_ROTATION_PIECE;
    }

    public static Integer getSpeedVeryEasy() {
        return SPEED_VERY_EASY;
    }

    public static Integer getSpeedEasy() {
        return SPEED_EASY;
    }

    public static Integer getSpeedMedium() {
        return SPEED_MEDIUM;
    }

    public static Integer getSpeedHard() {
        return SPEED_HARD;
    }

    public static Integer getSpeedVeryHard() {
        return SPEED_VERY_HARD;
    }

    public static Integer getUnitSize() {
        return UNIT_SIZE;
    }
}
