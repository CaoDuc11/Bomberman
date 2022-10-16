package com.example.demo.entities.animated.Character;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Character;
import com.example.demo.entities.animated.bomb.Bomb;
import com.example.demo.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomber extends Character {

    private static boolean up = false, down = false, right = false, left = false, space = false;
    private Sprite sprite;

    public static List<Bomb> _bombs = new ArrayList<>();
    private int timeDelaySetBomb = -1;

    public Bomber(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }


    public void move(double numX, double numY) {

        if(numX > 0) direction = 2;


        if(numX < 0) direction = 4;
        if(numY > 0) direction = 3;
        if(numY < 0) direction = 1;
        ChooseSprite();
        if(numX != 0 || numY != 0) {
            if(canMove(numX, 0)) {
                this.x += numX;
            }
            if(canMove(0, numY)) {
                this.y += numY;
            }
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
        double checkX = this.x + x ;
        double checkY = this.y + y ;

        if (this.direction == 1) {
            double xA = (checkX + 8)  / Sprite.SCALED_SIZE ;
            double xB = (checkX + 8 +  Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE ;
            double yA = (checkY + 16 )/ Sprite.SCALED_SIZE;
            double yB = (checkY + 16 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) xA, (int) (Math.max(yA,yB) - 1));
            Entity b = Game.getEntityAt((int) xB, (int) (Math.max(yA,yB) - 1));
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 2) {
            double xA = (checkX - 8)/ Sprite.SCALED_SIZE;
            double xB = (checkX - 9 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            double yA = (checkY + 5)/ Sprite.SCALED_SIZE;
            double yB = (checkY + 12 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) (xA + 1), (int) yA);
            Entity b = Game.getEntityAt((int) (xA + 1), (int) yB);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 3) {
            double xA = (checkX + 7)/ Sprite.SCALED_SIZE;
            double xB = (checkX + 7 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            double yA =(checkY -1 ) / Sprite.SCALED_SIZE;
            double yB = (checkY - 1  + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) xA, (int) (yA + 1));
            Entity b = Game.getEntityAt((int) xB, (int) (yA + 1));
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 4) {
            double xA = (checkX + 16) / Sprite.SCALED_SIZE;
            double xB = (checkX + 16+ Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            double yA = (checkY + 3)/ Sprite.SCALED_SIZE;
            double yB = (checkY + 12 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) (Math.max(xA, xB) - 1), (int) yA);
            Entity b = Game.getEntityAt((int) (Math.max(xA, xB) - 1), (int) yB);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }

        return true;

    }
    public void ChooseSprite() {
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
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {
        animate();
        calculateMove();
        if(timeDelaySetBomb < -7500) timeDelaySetBomb = 0;
        else timeDelaySetBomb--;
        isSetBomb();

//        System.out.println(_bombs.size());
    }

    private void isSetBomb(){
        if(space && Game.getBombRate() > 0 && timeDelaySetBomb < 0){
             int xT = x / 32;
             int yT = y / 32;
          Bomb b = new Bomb(xT, yT, Sprite.bomb);
          addBomb(b);
          Game.addBombRate(-1);

          timeDelaySetBomb = 20;
          space=false;
        }
    }

    public void addBomb(Bomb b){
        _bombs.add(b);
    }
    public void setBomb(int x, int y){
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
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
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
//                    case SPACE:
//                        space = false;
//                        break;
                    default:
                        break;
                }
            }
        });
    }
}
