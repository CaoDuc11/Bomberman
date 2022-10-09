package com.example.demo.entities;

import com.example.demo.Game;
import com.example.demo.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import javafx.event.Event;
import javafx.scene.Scene;

public class Bomber extends Character {

    public static boolean up, down, right, left, space;

    public Bomber(int x, int y, Sprite sprite) {
        super( x, y, sprite);
    }

    public void move(double numX, double numY) {

        if(numX > 0) direction = 2;
        if(numX < 0) direction = 4;
        if(numY > 0) direction = 3;
        if(numY < 0) direction = 1;
        if(numX != 0 || numY != 0){
            super.x += numX;
            super.y += numY;
        }

    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    protected boolean canMove(double x, double y) {
        return false;
    }

    public void ChooseSprite() {
        switch (direction) {
            case 1:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up,Sprite.player_up_1, Sprite.player_up_2, animate, 10);
                }
                break;
            case 2:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right,Sprite.player_right_1, Sprite.player_right_2, animate, 10);
                }
                break;
            case 3:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down,Sprite.player_down_1, Sprite.player_down_2, animate, 10);
                }
                break;
            case 4:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left,Sprite.player_left_1, Sprite.player_left_2, animate, 10);
                }
                break;
            default:
                sprite = Sprite.player_right;
                break;
        }
    }
    @Override
    public void update() {
        animate();
        ChooseSprite();
        calculateMove();
    }

    @Override
    protected void calculateMove() {
        int numX = 0;
        int numY = 0;
        if(up) numY--;
        if(down) numY++;
        if(left)  numX--;
        if(right) numX++;
        if(numX != 0 || numY != 0){
            move(numX * Game.getBomberSpeed() , numY * Game.getBomberSpeed());
            moving = true;
        }
        else { moving = false; }
    }

    public static void getControl(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case LEFT : {
                        left = true;
                    }
                    break;
                    case RIGHT: {

                        right = true;
                    }
                    break;
                    case UP: {
                        up = true;
                    }
                    break;
                    case DOWN: {
                        down = true;
                    }
                    break;
                    case SPACE: {
                        space = true;
                    }
                    break;
                    default:
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case LEFT: {
                        left = false;
                    }
                    break;
                    case RIGHT: {
                        right = false;
                    }
                    break;
                    case UP: {
                        up = false;
                    }
                    break;
                    case DOWN: {
                        down = false;
                    }
                    break;
                    case SPACE: {
                        space = false;
                    }
                    break;
                    default:
                        break;
                }
            }
        });
    }
}
