package com.example.demo.entities.animated.Character;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public abstract class Enemy extends Character{

    protected double steps;

    protected final double MAX_STEPS = Game.TITLE_SIZE / 10;
    protected double _speed;

    protected Sprite sprite;

    protected final  double rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
    public static Random random = new Random();
    public Enemy(int x, int y, Image img,double speed){
        super(x,y,img);
        _speed=speed;
    }
    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber){
            ((Bomber) e).kill();
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        animate();
        if(!alive) {
            afterKill();
            return;
        }

        if(alive)
            calculateMove();
    }

    @Override
    public void render(GraphicsContext gc) {
        if(alive){
            chooseSprite();
            Image img = sprite.getFxImage();
            gc.drawImage(img,x,y);
        }
    }

    public static int randomMove(){
        return random.nextInt(4);
    }

    @Override
    protected void calculateMove() {
        int xe=0; int ye=0;
        if(steps<=0) {
            direction = Enemy.randomMove();
            steps = MAX_STEPS;
        }
        if(direction == 0) ye--;
        if(direction == 2) ye++;
        if(direction == 3) xe--;
        if(direction == 1) xe++;
        if(canMove(xe,ye)){
            steps -= 1+rest;
            move(xe*_speed, ye* _speed);
            moving=true;
        }else {
            steps=0;
            moving=false;
        }
    }

    @Override
    protected void move(double xe, double ye) {
        if(xe > 0) direction = 2;


        if(xe < 0) direction = 4;
        if(ye > 0) direction = 3;
        if(ye < 0) direction = 1;
        chooseSprite();
        if(xe != 0 || ye != 0) {
            if(canMove(xe, 0)) {
                this.x += xe;
            }
            if(canMove(0, ye)) {
                this.y += ye;
            }
        }
    }

    @Override
    public void kill() {
        if(!alive) return;
        alive = false;
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
            /*if (tileMap[xVal1][yVal - 1] == 1 || tileMap[xVal2][yVal - 1] == 1 ||
                    tileMap[xVal1][yVal - 1] == 2 || tileMap[xVal2][yVal - 1] == 2) return true;*/
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
            /*if ((tileMap[xVal - 1][yVal1] == 1 || tileMap[xVal - 1][yVal2] == 1) ||
                    (tileMap[xVal - 1][yVal1] == 2 || tileMap[xVal - 1][yVal2] == 2)) return true;*/
        }
        /*Entity a = Game.getEntityAt( xt, yt);
        if (!a.collide(this)) {
            return false;
        }*/
        return true;
    }
    public abstract void chooseSprite();
}
