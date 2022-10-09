package com.example.demo.entities;

import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    public AnimatedEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }
    protected int animate = 0;
    protected final int MAX_ANIMATE = 1500;

    protected void animate() {
        if(animate < MAX_ANIMATE) animate++; else animate = 0;
    }
}
