package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Item extends Entity {
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean checkCollide(int x, int y) {
        return ( this.x - 2 <=  x && this.y - 2 <= y && this.x + 8 >= x && this.y + 8 >= y);
    }
}

