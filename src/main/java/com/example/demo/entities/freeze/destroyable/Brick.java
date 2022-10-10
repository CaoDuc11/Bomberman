package com.example.demo.entities.freeze.destroyable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends TileCanBeDestroy {

    public Brick(int x, int y, Image img){
        super(x,y,img);
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
