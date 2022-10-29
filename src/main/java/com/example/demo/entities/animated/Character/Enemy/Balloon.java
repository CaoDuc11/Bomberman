package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.entities.animated.Character.EneMove.NormalMove;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Sprite sprite,double speed, int point){
        super(x,y,sprite,speed,point);
        enemyMove = new NormalMove();
        direction = enemyMove.move();
    }

    @Override
    public void chooseSprite() {
        if(alive){
            switch(direction) {
                case 1:
                case 2:
                    this.sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 120);
                    break;
                case 3:
                case 4:
                    this.sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 120);
                    break;
            }
        }
        else {
            if(timeAfter < 20) sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate,120);
            else sprite = Sprite.balloom_dead;
        }

    }
}
