package com.example.demo.entities.freeze.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;

import javafx.scene.image.Image;
import java.awt.*;

public class TileCanBeDestroy extends Entity {

    private final int MAX_ANIMATE = 7500;

    private int _animate = 0;

    protected boolean _destroyed = false;

    protected int _timeDisappear = 20;

    protected Image image = Sprite.grass.getFxImage();

    protected boolean _removed = false;

    public TileCanBeDestroy(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public void remove() {
        _removed = true;
    }
    @Override
    public void update() {
        if(_destroyed) {
            if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
            if(_timeDisappear > 0)
                _timeDisappear--;
            else
                remove();
        }
    }

    public void destroy(){
        _destroyed = true;
    }

    public void addImage(Image image){
        this.image = image;
    }

    protected Sprite movingSpire(Sprite x1, Sprite x2, Sprite x3){
        int calc = _animate % 60;

        if(calc < 20){
            return x1;
        }
        if(calc < 40){
            return x2;
        }
        return x3;
    }

}
