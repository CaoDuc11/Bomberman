package com.example.demo.entities.freeze.items;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

import java.awt.font.GlyphMetrics;

public class FlameItem extends Item{
    public FlameItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber){
            Game.addBombRadius(0.25);
            System.out.println(Game.getBombRadius());
            remove();
        }
        return true;
    }

    @Override
    public void update() {

    }
}
