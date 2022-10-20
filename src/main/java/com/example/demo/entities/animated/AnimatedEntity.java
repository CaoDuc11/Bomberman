package com.example.demo.entities.animated;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    public AnimatedEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite.getFxImage());
    }
    public static int animate = 0;
    public static final int MAX_ANIMATE = 7500;

    protected Sprite sprite;

    public static void animate() {
        if(animate < MAX_ANIMATE) animate++; else animate = 0;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
