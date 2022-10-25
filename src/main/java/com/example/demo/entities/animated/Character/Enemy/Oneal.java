package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.Game;
import com.example.demo.entities.animated.Character.EneMove.AIMove;
import com.example.demo.entities.animated.Character.Enemy.Enemy;
import com.example.demo.graphics.Sprite;

public class Oneal extends Enemy {

    public Oneal(int x, int y, Sprite sprite, double speed, int point){
        super(x,y,sprite,speed,point);
        enemyMove = new AIMove(Game.getBomber(),this);
        direction = enemyMove.move();
    }
    @Override
    public void chooseSprite() {
        switch (direction) {
            case 1:
            case 2:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60);
                } else {
                    sprite = Sprite.oneal_left1;
                }
                break;
            case 3:
            case 4:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60);
                } else {
                    sprite = Sprite.oneal_left1;
                }
                break;
        }
    }
}
