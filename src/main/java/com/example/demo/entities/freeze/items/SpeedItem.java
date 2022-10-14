package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import javafx.scene.image.Image;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public boolean collide(Entity e) {
        //if(e instanceof Bomber)
        {remove();}
        return false;
    }
}
