package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Character;
import com.example.demo.entities.animated.Character.EneMove.EnemyMove;
import com.example.demo.entities.animated.Character.EneMove.NormalMove;
import com.example.demo.entities.animated.bomb.Flame;
import com.example.demo.graphics.Sprite;


public abstract class Enemy extends Character {


    protected int _points;

    protected int checkMove;

    protected double _speed;

    protected Sprite sprite;


    protected EnemyMove enemyMove;

    public Enemy(int x, int y, Sprite sprite, double speed, int point) {
        super(x, y, sprite);
        _speed = speed;
        _points = point;
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Flame) this.kill();
        if (e instanceof Bomber) ((Bomber) e).kill();
        return true;
    }

    @Override
    public void update() {
        animate();
        if(!alive){
           if(timeAfter > 0)timeAfter--;
           else remove();
        }
        else{
            calculateMove();
        }
        chooseSprite();
        this.setImg(sprite.getFxImage());
    }


    @Override
    protected void calculateMove() {
         if (this.x % Sprite.SCALED_SIZE == 0 && this.y % Sprite.SCALED_SIZE == 0) {
            this.direction = enemyMove.move();
            if (canMove(direction)){
                moving = true;
                checkMove = this.direction;
            }
            else moving = false;
        }
        if (moving){
            switch (checkMove){
                case 1:
                    move(0, 0 - _speed);
                    break;
                case 2:
                    move(_speed, 0);
                    break;
                case 3 :
                    move(0, _speed);
                    break;
                case 4 :
                    move( 0 - _speed, 0);
                    break;
            }
        }
    }

    @Override
    protected void move(double numX, double numY) {
        this.x += numX;
        this.y += numY;
    }

    @Override
    public void kill() {
        if (!alive) return;
        alive = false;
    }

    @Override
    protected void afterKill() {

    }

    public boolean canMove(int direction) {
        int i = this.getxUnit();
        int j = this.getyUnit();
        switch (direction) {
            case 1:
                j--;
                break;
            case 2:
                i++;
                break;
            case 3:
                j++;
                break;
            case 4:
                i--;
                break;
        }
        Entity e = Game.getEntity(i, j, this);
        return e.collide(this);
    }

    public abstract void chooseSprite();
}
