package com.example.demo.entities.freeze.destroyable;

import com.example.demo.entities.Entity;
import com.example.demo.entities.Entity;
import com.example.demo.graphics.Sprite;

import javafx.scene.image.Image;

public class Brick extends Entity {

    private final int MAX_ANIMATE = 7500;
    private int _animate = 0;
    protected boolean _destroyed = false;
    protected int _timeToDisapear = 20;
    protected Sprite _belowSprite = Sprite.grass;

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite.getFxImage());
    }


    @Override
    public void update() {
        if(_destroyed) {
            if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
            if(_timeToDisapear > 0)
                _timeToDisapear--;
            else
                remove();
        }
    }

    public void destroy() {
        _destroyed = true;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi va chạm với Flame
        return false;
    }

    public void addBelowSprite(Sprite sprite) {
        _belowSprite = sprite;
    }

    protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
        int calc = _animate % 30;

        if(calc < 10) {
            return normal;
        }

        if(calc < 20) {
            return x1;
        }

        return x2;
    }

}
