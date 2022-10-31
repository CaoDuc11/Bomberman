package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.entities.animated.Character.EneMove.AIHighMove;
import com.example.demo.entities.animated.Character.EneMove.AIMediumMove;
import com.example.demo.entities.animated.Character.EneMove.NormalMove;
import com.example.demo.graphics.Sprite;

public class Doll extends Enemy {


    public Doll(int x, int y, Sprite sprite, double speed, int point) {
        super(x, y, sprite, speed, point);
        enemyMove = new NormalMove();
        direction = enemyMove.move();
    }

    public void setEneMove() {
        this.enemyMove = new AIHighMove(this);
    }

    @Override
    public void chooseSprite() {
        if (alive) {
            switch (direction) {
                case 0:
                case 1:
                case 2:
                    this.sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 120);
                    break;
                case 3:
                case 4:
                    this.sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 120);
                    break;
            }
        } else {
            if (timeAfter < 20) {
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 120);
            } else sprite = Sprite.doll_dead;
        }
    }
}

