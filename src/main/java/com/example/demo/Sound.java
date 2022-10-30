package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {

    public Sound() {
    }
    public static MediaPlayer track10 = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/4 - Track 4.mp3").toURI().toString()));
    public static MediaPlayer background = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/background.wav").toURI().toString()));
    public static MediaPlayer bomb = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/nes_bomb.wav").toURI().toString()));
    public static MediaPlayer death = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/nes_death.wav").toURI().toString()));
    public static MediaPlayer destroy = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/nes_destroy.wav").toURI().toString()));
    public static MediaPlayer doorhit = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/nes_doorhit.wav").toURI().toString()));
    public static MediaPlayer explosion = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/nes_explosion.wav").toURI().toString()));
    public static MediaPlayer item = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/nes_item.wav").toURI().toString()));
    public static MediaPlayer nextlevel = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/next_level.wav").toURI().toString()));
    public static MediaPlayer running_1 = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/running_1.wav").toURI().toString()));
    public static MediaPlayer running_2 = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/running_2.wav").toURI().toString()));
    public static MediaPlayer collideTile = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/title_screen.wav").toURI().toString()));
    public static MediaPlayer win = new MediaPlayer(new Media(new File("Bomberman/src/main/resources/sound/win_title.wav").toURI().toString()));



}