package com.example.demo.entities.animated.Character;

import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public class Balloon extends Enemy{
    public Balloon(int x, int y, Sprite sprite,double speed, int point){
        super(x,y,sprite,speed,point);
    }

    @Override
    public void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60);
                break;
            case 2:
            case 3:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60);
                break;
        }
    }

}
