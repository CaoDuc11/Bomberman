package com.example.demo.entities.freeze.items;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.graphics.Sprite;

public class BoomItem extends Item {


    public BoomItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber){
            if(checkCollide(e.getX(), e.getY())) {
                Game.addBombRate(0.5);
                remove();
            }
        }
        return true;
    }

    @Override
    public void update() {
    }
}
