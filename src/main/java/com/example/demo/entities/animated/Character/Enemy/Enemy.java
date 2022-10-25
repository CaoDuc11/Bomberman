package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Character;
import com.example.demo.entities.animated.Character.EneMove.EnemyMove;
import com.example.demo.entities.animated.Character.EneMove.NormalMove;
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
        if (e instanceof Bomber) {
            ((Bomber) e).kill();
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        calculateMove();

    }


    @Override
    protected void calculateMove() {
        if (this.x % Sprite.SCALED_SIZE == 0 && this.y % Sprite.SCALED_SIZE == 0) {
            checkMove = 0;
            while (!canMove(this.direction)) {
                this.direction = enemyMove.move();
            }
            checkMove = this.direction;
        }
        switch (checkMove) {
            case 1:
                move(0, 0 - _speed);
                break;
            case 2:
                move(_speed, 0);
                break;
            case 3:
                move(0, _speed);
                break;
            case 4:
                move(0 - _speed, 0);
                break;
        }
        chooseSprite();
        this.setImg(sprite.getFxImage());
    }

    @Override
    protected void move(double numX, double numY) {
        this.x += numX;
        this.y += numY;
    }
//    if(!alive) return;
//    y+=ye;
//    x+=xe;

    @Override
    public void kill() {
        if (!alive) return;
        alive = false;
    }

    @Override
    protected void afterKill() {

    }

    protected boolean canMove(int direction) {
        this.setxUnit();
        this.setyUnit();
        int i = this.xUnit;
        int j = this.yUnit;
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
