package com.example.demo.entities.freeze.tile;

import com.example.demo.entities.Entity;
import javafx.scene.image.Image;

public abstract class Tile extends Entity {
    public Tile(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void update() {

    }
}
