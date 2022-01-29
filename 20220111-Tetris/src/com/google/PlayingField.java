package com.google;

import javax.swing.*;
import java.awt.*;

public class PlayingField extends JPanel {
    private FieldElement[][] playingField;
    private int PLAYING_FIELD_DISPLAY_HEIGHT = 20;
    private int PLAYING_FIELD_DISPLAY_WIDTH = 10;

    /**
     * This is the constructor, which sets the default display options and playing field
     *
     * @param playingField is the playing field, which gets copied
     */
    public PlayingField(FieldElement[][] playingField) {
        this.playingField = playingField;

        this.setBackground(DefaultValues.getColorDefault());
        this.setPreferredSize(new Dimension(PLAYING_FIELD_DISPLAY_WIDTH * DefaultValues.getUnitSize(),
                PLAYING_FIELD_DISPLAY_HEIGHT * DefaultValues.getUnitSize()));
        this.setSize(PLAYING_FIELD_DISPLAY_WIDTH * DefaultValues.getUnitSize(),
                PLAYING_FIELD_DISPLAY_HEIGHT * DefaultValues.getUnitSize());
        this.setVisible(true);
        this.requestFocusInWindow();
    }

    /**
     * This method paints the component with the new grid
     *
     * @param g is the graphic of the JPanel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * This method sets the grid values to the field and adds lines to make the field more visible
     *
     * @param g is the graphic of the JPanel
     */
    public void draw(Graphics g) {
        for (int y = 0; y < playingField.length - 3; y++) {
            for (int x = 0; x < playingField[0].length; x++) {
                g.setColor(playingField[y + 3][x].getColor());
                g.fillRect(x * (this.getWidth()) / PLAYING_FIELD_DISPLAY_WIDTH,
                        y * this.getHeight() / PLAYING_FIELD_DISPLAY_HEIGHT,
                        this.getWidth() / PLAYING_FIELD_DISPLAY_WIDTH,
                        this.getHeight() / PLAYING_FIELD_DISPLAY_HEIGHT);
            }
        }
        for (int i = 0; i < this.getWidth() / PLAYING_FIELD_DISPLAY_WIDTH; i++) {
            g.setColor(new Color(0xFFFFFF));
            g.drawLine(i * this.getWidth() / PLAYING_FIELD_DISPLAY_WIDTH, 0,
                    i * this.getWidth() / PLAYING_FIELD_DISPLAY_WIDTH, this.getHeight());
        }

        for (int i = 0; i < this.getHeight() / PLAYING_FIELD_DISPLAY_HEIGHT; i++) {
            g.setColor(new Color(0xFFFFFF));
            g.drawLine(0, i * this.getHeight() / PLAYING_FIELD_DISPLAY_HEIGHT,
                    this.getWidth(), i * this.getHeight() / PLAYING_FIELD_DISPLAY_HEIGHT);
        }
    }

    /**
     * Sets the set playingField to the new / updated playingField and rerenders the component
     *
     * @param playingField is the new / updated field
     */
    public void updateField(FieldElement[][] playingField) {
        this.playingField = playingField;
        paintComponent(this.getGraphics());
    }

}
