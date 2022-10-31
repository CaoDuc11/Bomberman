package com.example.demo.entities.freeze.tile;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;

public class Wall extends Tile {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite.getFxImage());
    }

    @Override
    public boolean collide(Entity e) {
        return false;//khong cho di qua
    }
}
