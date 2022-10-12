package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import javafx.scene.image.Image;

public class Item extends Entity {
    public Item (int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {

    }
}
