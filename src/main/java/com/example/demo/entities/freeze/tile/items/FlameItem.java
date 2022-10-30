package com.example.demo.entities.freeze.tile.items;

import com.example.demo.Game;
import com.example.demo.Sound;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber){
            if(checkCollide(e.getX(), e.getY())) {
                Sound.item.stop();
                Sound.item.play();
                Game.addBombRadius(0.5);
                remove();
            }
        }
        return true;
    }

    @Override
    public void update() {

    }
}
