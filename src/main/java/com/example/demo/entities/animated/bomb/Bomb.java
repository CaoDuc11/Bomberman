package com.example.demo.entities.animated.bomb;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;



public class Bomb extends AnimatedEntity {
    protected double _timeToExplode = 120; //2 seconds - thoi gian phat no
    public int _timeAfter = 20;// thoi gian de no
    protected boolean _exploded = false;

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }


    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {
        if(_timeToExplode > 0)
            _timeToExplode--;
        else {
            if(!_exploded)
                explode();
            else
                updateFlames();

            if(_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }

        animate();
    }

    public void updateFlames() {

    }

    public void remove() {

    }
    public void explode() {
    }
}
