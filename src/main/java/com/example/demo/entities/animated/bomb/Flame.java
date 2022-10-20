package com.example.demo.entities.animated.bomb;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public class Flame extends AnimatedEntity {

    public int type;

    public Flame(int xUnit, int yUnit, Sprite sprite, int type) {
        super(xUnit, yUnit, sprite);
        this.type = type;
    }


    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi FlameSegment va chạm với Character
        if(e instanceof Bomber) ((Bomber) e).kill();
        return true;
    }

    public void ChooseSprite(){
        switch(type){
            case 1:
                this.sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last,Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, 120);
                break;
            case 2:
                this.sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, 120);
                break;
            case 3:
                this.sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate, 120);
                break;
            case 4:
                this.sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last,Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 120);
                break;
            case 5:
                this.sprite = Sprite.movingSprite(Sprite.explosion_vertical,Sprite.explosion_vertical1,Sprite.explosion_vertical2, animate,120);
                break;
            case 6:
                this.sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 120);
                break;
        }

    }
    @Override
    public void update() {
        ChooseSprite();
        this.setImg(this.sprite.getFxImage());
    }
}
