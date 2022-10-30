package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.Game;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.EneMove.AILowMove;
import com.example.demo.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {

    private Random randomSpeed = new Random();
    public Oneal(int x, int y, Sprite sprite, double speed, int point){
        super(x,y,sprite,speed,point);
        enemyMove = new AILowMove(this);
        direction = enemyMove.move();
    }
    @Override
    public void chooseSprite() {
        if (this.x % Sprite.SCALED_SIZE == 0 && this.y % Sprite.SCALED_SIZE == 0) {
            this._speed = randomSpeed.nextInt(3);
        }
        if(alive){
            switch (direction) {
                case 1:
                case 2:
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60);
                    break;
                case 3:
                case 4:
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60);
                    break;
            }
        }
        else {
            if(timeAfter < 20) sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate,120);
            else sprite = Sprite.oneal_dead;
        }

    }
}
