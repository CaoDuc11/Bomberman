package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import javafx.scene.image.Image;

public class BoomItem extends Item{
    public BoomItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber){
            Item.addBoomItem(1);
            remove();
        }
        return false;
    }
}
