package com.example.demo.entities.freeze;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite.getFxImage());
    }

    @Override
    public boolean collide(Entity e) {
        return false;//khong cho di qua
    }
    @Override
    public void update() {

    }
}
