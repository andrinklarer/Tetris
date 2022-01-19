package com.google;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Score extends JPanel {
    private AtomicInteger score;
    private AtomicInteger level;
    private JLabel scoreLabel;
    private JLabel levelLabel;

    public Score() {
        this.setLayout(new GridLayout(1,3));

        score = new AtomicInteger(0);
        level = new AtomicInteger(1);

        this.add(new JLabel());
        this.add(scoreLabel = new JLabel("score: " + score.get()));
        this.add(levelLabel = new JLabel("level: " + level.get()));

        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        levelLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public AtomicInteger getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
        levelLabel.setText("level: " + this.level.get());
    }

    public AtomicInteger getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
        scoreLabel.setText("score: " + this.score.get());
    }
}
