package com.example.demo.entities.animated.Character;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Enemy.Enemy;
import com.example.demo.entities.animated.bomb.Bomb;
import com.example.demo.entities.animated.bomb.Flame;
import com.example.demo.entities.freeze.LayerEntity;
import com.example.demo.entities.freeze.tile.Portal;
import com.example.demo.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private static boolean up = false, down = false, right = false, left = false, space = false;

    private Sprite sprite;
    public static List<Bomb> _bombs = new ArrayList<>();
    private int timeDelaySetBomb = -1;

    public Bomber(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void move(double numX, double numY) {

        if (numY < 0) direction = 1;
        if (numX > 0) direction = 2;
        if (numY > 0) direction = 3;
        if (numX < 0) direction = 4;
        if(numX != 0) this.y = set(this.y);
        if (canMove(numX, 0)) {
            this.x += numX;
        }
        if(numY != 0)  this.x = set(this.x);
        if (canMove(0, numY)) {
            this.y += numY;

        }

    }

    @Override
    protected void afterKill() {

    }

    protected boolean canMove(double x, double y) {
        double checkX = this.x + x;
        double checkY = this.y + y;
        double xA = (checkX + 1)  / Sprite.SCALED_SIZE ;
        double xB = (checkX  +  Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE ;
        double yA = (checkY + 1 )/ Sprite.SCALED_SIZE;
        double yB = (checkY + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE;

        if (this.direction == 1) {
            Entity a = Game.getEntity((int) xA, (int) yA, this);
            Entity b = Game.getEntity((int) xB, (int) yA, this);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 2) {
            Entity a = Game.getEntity((int) xB, (int) yA, this);
            Entity b = Game.getEntity((int) xB, (int) yB, this);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 3) {
            Entity a = Game.getEntity((int) xA, (int) yB, this);
            Entity b = Game.getEntity((int) xB, (int) yB, this);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 4) {
            Entity a = Game.getEntity((int) xA, (int) yA, this);
            Entity b = Game.getEntity((int) xA, (int) yB, this);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
          }
      }
        return true;

    }


    @Override
    public void kill() {
        if (!this.alive) return;
        this.alive = false;
    }

    public void chooseSprite() {
        if(alive){
            switch (direction) {
                case 1:
                    sprite = Sprite.player_up;
                    if (moving) {
                        sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    }
                    break;
                case 2:
                    sprite = Sprite.player_right;
                    if (moving) {
                        sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    }
                    break;
                case 3:
                    sprite = Sprite.player_down;
                    if (moving) {
                        sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    }
                    break;
                case 4:
                    sprite = Sprite.player_left;
                    if (moving) {
                        sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    }
                    break;
                default:
                    sprite = Sprite.player_right;
                    break;
            }
        }
        else{
            if(timeAfter < 20) sprite = sprite = Sprite.movingSprite(Sprite.player_dead1,Sprite.player_dead2,Sprite.player_dead3,animate,120);

        }

    }

    @Override
    public boolean collide(Entity e) {
//        if(e instanceof Portal){
//            Portal.isStepOn = true;
//            return true;
//        }
        if(e instanceof Flame){
            this.kill();
            return true;
        }
        if(e instanceof Enemy) {
            this.kill();
            return true;
        }
        if(e instanceof LayerEntity) return e.collide(this);
        return false;
    }

    @Override
    public void update() {
        clearBomb();
        animate();
        if(!alive){
            if(timeAfter > 0) timeAfter--;
            else remove();
        }
        else{
            calculateMove();
            if (timeDelaySetBomb < -7500) timeDelaySetBomb = 0;
            else timeDelaySetBomb--;
            isSetBomb();
        }
        chooseSprite();
        this.setImg(sprite.getFxImage());
    }

    private void clearBomb() {
        int i = 0;
        while (i < _bombs.size()) {
            if (_bombs.get(i).isRemoved()) {
                _bombs.remove(i);
                Game.addBombRate(1);
            } else i++;
        }
    }

    private void isSetBomb() {
        if (space && Game.getBombRate() > 0 && timeDelaySetBomb < 0) {
            int xT = (int) ((x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);
            int yT = (int) ((y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);
            Bomb b = new Bomb(xT, yT, Sprite.bomb, (int) Game.getBombRadius());
            addBomb(b);
            Game.addBombRate(-1);

            timeDelaySetBomb = 20;
            space = false;
        }
    }

    public void addBomb(Bomb b) {
        _bombs.add(b);
    }

    public static Entity bombAt(int xUnit, int yUnit){
        for(int i = 0; i < _bombs.size(); i++){
            if(_bombs.get(i).getxUnit() == xUnit && _bombs.get(i).getyUnit() == yUnit){
                return _bombs.get(i);
            }
        }
        return null;
    }

    @Override
    protected void calculateMove() {
        int numX = 0;
        int numY = 0;
        if (up) numY--;
        if (down) numY++;
        if (left) numX--;
        if (right) numX++;
        if (numX != 0 || numY != 0) {
            move(numX * Game.getBomberSpeed(), numY * Game.getBomberSpeed());
            moving = true;
        } else {
            moving = false;
        }
    }

    public static void getControl(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case LEFT:
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
