package com.example.demo.entities.freeze.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;


import javafx.scene.image.Image;
import java.awt.*;
import java.util.LinkedList;

public class LayerEntity extends Entity {
    protected LinkedList<Entity> _entity = new LinkedList<>();

    public LayerEntity(int x, int y, Image img, Entity ... entity){
       super(x,y,img);

       for(int i=0; i < entity.length; i++){
           _entity.add(entity[i]);

           if(i > 1) {
               if(entity[i] instanceof TileCanBeDestroy)
                   ((TileCanBeDestroy)entity[i]).addImage(entity[i-1].getImg());
           }
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
    public void update() {
        Removed();;
        getTopEntity().update();
    }
}
