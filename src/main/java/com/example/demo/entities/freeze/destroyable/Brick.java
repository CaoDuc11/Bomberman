package com.example.demo.entities.freeze.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.entities.animated.bomb.Flame;
import com.example.demo.graphics.Sprite;

import javafx.scene.image.Image;

public class Brick extends AnimatedEntity {

    /*private final int MAX_ANIMATE = 7500;
    private int _animate = 0;*/

    protected int _timeToDisapear = 20;


    protected boolean _destroy = false;

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }


    @Override
    public void update() {
        this.sprite = Sprite.brick;
        if(_destroy){
            if(_timeToDisapear > 0){
                _timeToDisapear--;
            }
            else {
                remove();
            }
            if(_timeToDisapear > 0 && _destroy) {
                this.sprite = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,animate,120);
            }
            this.setImg(sprite.getFxImage());
        }
    }


    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi va chạm với Flame
        if(e instanceof Flame) destroy();
        return false;
    }

    private void destroy() {
        _destroy = true;
    }


}
