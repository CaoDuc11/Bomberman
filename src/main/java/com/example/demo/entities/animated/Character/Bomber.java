package com.example.demo.entities.animated.Character;

import com.example.demo.Game;
import com.example.demo.entities.animated.Character.Character;
import com.example.demo.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.scene.Scene;

public class Bomber extends Character {

    private static boolean up = false, down = false, right = false, left = false, space = false;
    private Sprite sprite;

    public Bomber(int x, int y, Sprite sprite) {
        super(x, y, sprite.getFxImage());

    }

    public void move(double numX, double numY) {

        if(numX > 0) direction = 2;


        if(numX < 0) direction = 4;
        if(numY > 0) direction = 3;
        if(numY < 0) direction = 1;
        ChooseSprite();
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
        System.out.println(1);
        switch (direction) {
            case 1:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up,Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right,Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down,Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                }
                break;
            case 4:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left,Sprite.player_left_1, Sprite.player_left_2, animate, 20);
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
            ChooseSprite();
            this.setImg(sprite.getFxImage());
            moving = true;
        }
        else { moving = false; }
    }

    public static void getControl(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Press " + keyEvent.getCode());
                switch (keyEvent.getCode()) {
                    case LEFT :
                        left = true;
                        up = false;
                        right = false;
                        down = false;
                        break;

                    case RIGHT:
                        right = true;
                        left = false;
                        up = false;
                        down = false;
                        break;

                    case UP:
                        up = true;
                        left = false;
                        right = false;
                        down = false;
                        break;

                    case DOWN:
                        down = true;
                        left = false;
                        up = false;
                        right = false;
                        break;

                    case SPACE:
                        space = true;
                        break;

                    default:
                        break;
                }
                System.out.println("******* " + keyEvent.getCode());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Release " + keyEvent.getCode());
                switch (keyEvent.getCode()) {
                    case LEFT:
                        left = false;
                        break;
                    case RIGHT:
                        right = false;
                        break;
                    case UP:
                        up = false;
                        break;
                    case DOWN:
                        down = false;
                        break;
                    case SPACE:
                        space = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
