package com.example.demo.entities.freeze.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;


import javafx.scene.image.Image;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class LayerEntity extends Entity {
    public ArrayList<Entity> _entity = new ArrayList<>();

    public LayerEntity(int x, int y, Entity ... entities){
        super(x, y, entities[0].getImg());

        for(int i=0; i < entities.length; i++){
           _entity.add(entities[i]);
       }

    }

    public Entity getTopEntity(){
        return _entity.get(0);
    }

    public void Removed(){
        Entity top = getTopEntity();
        if(top.isRemoved()){
            _entity.remove(0);
        }
    }

    @Override
    public boolean collide(Entity e) {
        return getTopEntity().collide(e);
    }



    @Override
    public void update() {
        Removed();
        this.setImg(this.getTopEntity().getImg());
        getTopEntity().update();

    }
}
