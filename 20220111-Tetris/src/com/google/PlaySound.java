package com.google;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlaySound {
    /**
     * This is the constructor, which starts the music file if exist
     */
    public PlaySound() {
        String filename = "Tetris-Theme-Song.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("There was an error with the sound file");
        }
    }
}
