package com.google;

import java.awt.*;

public class DefaultValues {
    private static Color colorDefault = new Color(0x000);
    private static Color colorIBlock = new Color(0x00f0f0);
    private static Color colorJBlock = new Color(0x0000f0);
    private static Color colorLBlock = new Color(0xf0a000);
    private static Color colorOBlock = new Color(0xf0f000);
    private static Color colorSBlock = new Color(0x00f000);
    private static Color colorTBlock = new Color(0xa000f0);
    private static Color colorZBlock = new Color(0xf00000);

    private static Integer valueDefault = 0;
    private static Integer valueFilled = 1;
    private static Integer valueMovingPiece = 2;
    private static Integer valueRotationPiece = 3;

    private static Integer speedVeryEasy = 1000;
    private static Integer speedEasy = 750;
    private static Integer speedMedium = 500;
    private static Integer speedHard = 300;
    private static Integer speedVeryHard = 150;

    private static Integer unitSize = 25;

    public static Color getColorDefault() {
        return colorDefault;
    }

    public static Color getColorIBlock() {
        return colorIBlock;
    }

    public static Color getColorJBlock() {
        return colorJBlock;
    }

    public static Color getColorLBlock() {
        return colorLBlock;
    }

    public static Color getColorOBlock() {
        return colorOBlock;
    }

    public static Color getColorSBlock() {
        return colorSBlock;
    }

    public static Color getColorTBlock() {
        return colorTBlock;
    }

    public static Color getColorZBlock() {
        return colorZBlock;
    }

    public static Integer getValueDefault() {
        return valueDefault;
    }

    public static Integer getValueFilled() {
        return valueFilled;
    }

    public static Integer getValueMovingPiece() {
        return valueMovingPiece;
    }

    public static Integer getValueRotationPiece() {
        return valueRotationPiece;
    }

    public static Integer getSpeedVeryEasy() {
        return speedVeryEasy;
    }

    public static Integer getSpeedEasy() {
        return speedEasy;
    }

    public static Integer getSpeedMedium() {
        return speedMedium;
    }

    public static Integer getSpeedHard() {
        return speedHard;
    }

    public static Integer getSpeedVeryHard() {
        return speedVeryHard;
    }

    public static Integer getUnitSize() {
        return unitSize;
    }
}
