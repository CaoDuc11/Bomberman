package com.example.demo.entities.freeze;

import com.example.demo.entities.Entity;
import javafx.scene.image.Image;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }

    @Override
    public void update() {

    }
}
