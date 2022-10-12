package com.example.demo.entities.freeze;

import com.example.demo.entities.Entity;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        return false;//khong cho di qua
    }
    @Override
    public void update() {

    }
}
