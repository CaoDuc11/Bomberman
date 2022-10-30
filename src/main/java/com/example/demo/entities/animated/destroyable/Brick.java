package com.example.demo.entities.animated.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.entities.animated.bomb.Flame;
import com.example.demo.graphics.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends AnimatedEntity {

    protected int _timeToDisapear = 20;


    protected boolean _destroy = false;

    protected Image below;
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }


    @Override
    public void update() {
        if(_destroy){
            animate();
            if(_timeToDisapear > 0){
                _timeToDisapear--;
            }
            else {
                remove();
            }
            if(_timeToDisapear > 0) {
                this.sprite = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,animate,30);
            }
            this.setImg(sprite.getFxImage());
        }
        else this.sprite = Sprite.brick;
    }

    public void addBelow(Image image){
        below = image;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi va chạm với Flame
        if(e instanceof Flame) destroy();
        return false;
    }

    @Override
    public void render(GraphicsContext gc){
        gc.drawImage(below,x, y);
        gc.drawImage(img, x, y);
    }
    private void destroy() {
        _destroy = true;
    }
}
