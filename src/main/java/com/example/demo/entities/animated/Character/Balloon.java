package com.example.demo.entities.animated.Character;

import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public class Balloon extends Enemy{
    public Balloon(int x, int y, Sprite sprite,double speed, int point){
        super(x,y,sprite,speed,point);
        enemyMove = new NormalMove();
        direction = enemyMove.move();
    }

    @Override
    public void chooseSprite() {
        switch(direction) {
            case 1:
            case 2:
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 120);
                break;
            case 3:
            case 4:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 120);
                break;
        }
    }

}
