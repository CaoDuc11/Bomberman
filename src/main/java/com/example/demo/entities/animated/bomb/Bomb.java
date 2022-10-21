package com.example.demo.entities.animated.bomb;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class Bomb extends AnimatedEntity {
    protected int _timeToExplode = 120; //2 seconds - thoi gian phat no
    public int _timeAfter = 20;// thoi gian de no
    protected Sprite sprite;
    protected boolean _exploded = false;


    protected boolean _allowedToPassThru = true;

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit,yUnit,sprite.getFxImage());
    }



    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        if (_timeToExplode > 0){
            _timeToExplode--;
        }
        else _timeAfter--;
        /*else {
            if(!_exploded)
                explode();
            else
                updateFlames();

            if(_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }*/
    }
    public void chooseSprite(){
        System.out.println(1);
        if (_timeToExplode > 0) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 50);
        } else if (_timeAfter > 0) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, AnimatedEntity.animate, 120);
        }
        this.setImg(sprite.getFxImage());
    }
    /*@Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        Image img_ = sprite_.getFxImage();
        gc.drawImage(img_, x, y);
    }
*/
    public void updateFlames() {

    }

    public void remove() {

    }
    public void explode() {
    }
}
