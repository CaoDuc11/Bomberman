package com.example.demo.entities;

import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Brick extends TileCanBeDestroy{

    public Brick(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
