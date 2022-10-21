package com.example.demo.entities.animated.bomb;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public class Flame extends Entity {
    private Sprite sprite;
    public Flame(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }


    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {

    }
}
