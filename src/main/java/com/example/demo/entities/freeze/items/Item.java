package com.example.demo.entities.freeze.items;

import com.example.demo.entities.Entity;
import javafx.scene.image.Image;

public abstract class Item extends Entity {
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
