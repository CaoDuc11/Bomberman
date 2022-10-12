package com.example.demo.entities.freeze.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;


import javafx.scene.image.Image;
import java.awt.*;
import java.util.LinkedList;

public class LayerEntity extends Entity {
    protected LinkedList<Entity> _entity = new LinkedList<>();

    public LayerEntity(int x, int y, Entity ... entity){
        super(x, y,entity[entity.length - 1].getImg());

        for(int i=0; i < entity.length; i++){
           _entity.add(entity[i]);
       }

    }

    public Entity getTopEntity(){
         return _entity.getLast();
    }

    public void Removed(){
        Entity top = getTopEntity();

        if(top.isRemoved()){
            _entity.removeLast();
        }
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {
        Removed();;
        getTopEntity().update();
    }
}
