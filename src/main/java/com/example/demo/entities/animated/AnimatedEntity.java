package com.example.demo.entities.animated;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    protected void animate() {
        if(animate < MAX_ANIMATE) animate++; else animate = 0;
    }
}
