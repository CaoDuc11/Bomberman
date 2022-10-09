package com.example.demo.entities;

import com.example.demo.graphics.Sprite;

import java.util.LinkedList;

public class LayerEntity extends Entity{
    protected LinkedList<Entity> _entity = new LinkedList<>();

    public LayerEntity(int x, int y, Sprite sprite, Entity ... entity){
       super(x,y,sprite);

       for(int i=0; i < entity.length; i++){
           _entity.add(entity[i]);

           if(i > 1) {
               if(entity[i] instanceof TileCanBeDestroy)
                   ((TileCanBeDestroy)entity[i]).addSprite(entity[i-1].getSprite());
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
