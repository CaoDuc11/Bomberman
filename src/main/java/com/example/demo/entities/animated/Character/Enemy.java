package com.example.demo.entities.animated.Character;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public abstract class Enemy extends Character{

    protected double steps;

    protected int _points;

    protected final double MAX_STEPS;
    protected double _speed;

    protected Sprite sprite;

    protected final  double rest;
    public static Random random = new Random();

    public boolean check,check1,check2,check3,check4;
    public Enemy(int x, int y, Sprite sprite,double speed, int point){
        super(x,y,sprite);
        _speed=speed;
        _points=point;
        MAX_STEPS = Game.TITLE_SIZE / _speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        steps = MAX_STEPS;
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
        if(direction == 1) ye++;
        if(direction == 2) xe--;
        if(direction == 3) xe++;
        if(canMove(xe,ye)){
            steps -= 1+rest;
            move(xe*_speed, ye* _speed);
            moving=true;
        }else {
            steps=0;
            moving=false;
        }
//        if(x%32==0 && y%32==0){
//             check =false; check1=false; check2=false; check3=false; check4=false;
//            while (!canMove(x,y)){
//                direction=Enemy.randomMove();
//            }
//            switch (direction){
//                case 1:
//                    check1=true;
//                    break;
//                case 2:
//                    check2=true;
//                    break;
//                case 3:
//                    check3=true;
//                    break;
//                case 4:
//                    check4=true;
//                    break;
//            }
//        }
//        if(check1) y-=1;
//        if(check2) y+=1;
//        if(check3) x-=1;
//        if(check4) x+=1;
    }

    @Override
    protected void move(double xe, double ye) {
        if (xe > 0) direction = 1;
        if (xe < 0) direction = 3;
        if (ye > 0) direction = 2;
        if (ye < 0) direction = 0;
        if (xe != 0 || ye != 0) {
            if (canMove(xe, 0)) {
                this.x += xe;
            }
            if (canMove(0, ye)) {
                this.y += ye;
            }
        }
    }
//    if(!alive) return;
//    y+=ye;
//    x+=xe;

    @Override
    public void kill() {
        if(!alive) return;
        alive = false;
    }

    @Override
    protected void afterKill() {

    }

    protected boolean canMove(double x, double y) {
        double checkX = this.x + x ;
        double checkY = this.y + y ;

        if (this.direction == 0) {
            double xA = (checkX + 3)  / Sprite.SCALED_SIZE ;
            double xB = (checkX + 8 +  Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE ;
            double yA = (checkY + 16 )/ Sprite.SCALED_SIZE;
            double yB = (checkY + 16 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) xA, (int) (Math.max(yA,yB) - 1));
            Entity b = Game.getEntityAt((int) xB, (int) (Math.max(yA,yB) - 1));
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 1) {
            double xA = (checkX - 8)/ Sprite.SCALED_SIZE;
            double xB = (checkX - 9 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            double yA = (checkY + 5)/ Sprite.SCALED_SIZE;
            double yB = (checkY + 14 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) (xA + 1), (int) yA);
            Entity b = Game.getEntityAt((int) (xA + 1), (int) yB);
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 2) {
            double xA = (checkX + 3)/ Sprite.SCALED_SIZE;
            double xB = (checkX + 7 + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            double yA =(checkY -1 ) / Sprite.SCALED_SIZE;
            double yB = (checkY - 1  + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            Entity a = Game.getEntityAt((int) xA, (int) (yA + 1));
            Entity b = Game.getEntityAt((int) xB, (int) (yA + 1));
            if (!a.collide(this) || !b.collide(this)) {
                return false;
            }
        }
        if (this.direction == 3) {
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
    public abstract void chooseSprite();
}
