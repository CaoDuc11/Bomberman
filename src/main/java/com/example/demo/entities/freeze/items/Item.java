package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import javafx.scene.image.Image;

public class Item extends Entity {
    public static final int boomAmount = 1;
    public static int BOOMAMOUNT = boomAmount;
    public Item (int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public static void addBoomItem(int i){
         BOOMAMOUNT+=i;
    }
    @Override
    public void update() {

    }
}
