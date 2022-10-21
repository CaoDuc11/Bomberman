package com.example.demo.entities.freeze.items;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public class BoomItem extends Item{


    public BoomItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber){
            Game.addBombRate(0.25);
            remove();
        }
        return true;
    }

    @Override
    public void update() {
    }
}
