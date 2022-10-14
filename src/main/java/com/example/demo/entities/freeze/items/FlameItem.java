package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import javafx.scene.image.Image;

public class FlameItem extends Item{
    public FlameItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public boolean collide(Entity e) {
        //if(a instanceof Bomber)
        {remove();}
        return false;
    }
}
