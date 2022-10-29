package com.example.demo.entities.freeze.tile;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class Portal extends Tile{

    private Image below;

    public static boolean isStepOn = false;
    public Portal(int xUnit, int yUnit, Sprite sprite){
        super(xUnit, yUnit, sprite.getFxImage());
        this.below = Sprite.grass.getFxImage();
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Bomber) {
            for (int i = 0; i < Game.entities.size(); i++) {
                if (Game.entities.get(i) instanceof Enemy) {
                    return false;
                }
            }
                isStepOn = true;
                return true;
            }
        return false;
    }
    @Override
    public void render(GraphicsContext gc){
        gc.drawImage(below,x, y);
        gc.drawImage(img, x, y);
    }

}
