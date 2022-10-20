package com.example.demo.entities.animated.bomb;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.freeze.Wall;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class Bomb extends AnimatedEntity {
    protected int _timeToExplode = 120; //2 seconds - thoi gian phat no
    public int _timeAfter = 20;// thoi gian de no
    private boolean _exploded = false;
    private boolean _allowedToPassThru = true;
    private int radius;

    public List<Flame> flames = new ArrayList<>();


    public Bomb(int xUnit, int yUnit, Sprite sprite, int radius) {
        super(xUnit, yUnit, sprite);
        this.radius = radius;
    }


    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            timeExplode();
        }
        return true;
    }

    public void timeExplode() {
        _timeToExplode = 0;
    }

    @Override
    public void update() {
        chooseSprite();
        if (_timeToExplode > 0) {
            _timeToExplode--;
        } else if (_timeAfter > 0) {
            _timeAfter--;
        } else remove();
        this.setImg(sprite.getFxImage());
        /*else {
            if(!_exploded)
                explode();
            else
                updateFlames();

            if(_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }*/
    }

    public void exploded() {
        _allowedToPassThru = true;
        createFlame();
        int i = 0;
        while (i < flames.size()) {
            if (flames.get(i).getX() < 0 || flames.get(i).getY() < 0) flames.remove(i);
            Entity e = Game.getEntity(flames.get(i).getX() , flames.get(i).getY(), flames.get(i));
            boolean check = e.collide(flames.get(i));
            if (!check) {
                flames.remove(i);
            } else i++;
        }
        for (int j = 0; j < flames.size(); j++) {
            flames.get(j).update();
        }
    }

    public void chooseSprite() {
        if (_timeToExplode > 0) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 60);
        } else if (_timeAfter > 0) {
            exploded();
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, AnimatedEntity.animate, 120);
        }
    }

    public void createFlame() {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        Flame flameUp = new Flame(xUnit, yUnit - this.radius, Sprite.explosion_vertical_top_last, 1);
        flames.add(flameUp);
        Flame flameLeft = new Flame(xUnit - this.radius, yUnit, Sprite.explosion_horizontal_left_last, 2);
        flames.add(flameLeft);
        Flame flameRight = new Flame(xUnit + this.radius, yUnit, Sprite.explosion_horizontal_right_last, 3);
        flames.add(flameRight);
        Flame flameDown = new Flame(xUnit, yUnit + this.radius, Sprite.explosion_vertical_down_last, 4);
        flames.add(flameDown);

        if (this.radius > 1) {
            Flame f;
            Entity e;
            for (int i = 1; i < this.radius; i++) {
                f = new Flame(xUnit, yUnit - i, Sprite.explosion_vertical, 5);
                flames.add(f);
                e = Game.getEntityAt(xUnit, yUnit - i);
                if (!e.collide(f)) {
                    flames.remove(flameUp);
                    break;
                }
            }

            for (int i = 1; i < this.radius; i++) {
                f = new Flame(xUnit + i, yUnit, Sprite.explosion_horizontal, 6);
                flames.add(f);
                e = Game.getEntityAt(xUnit + i, yUnit);
                if (!e.collide(f)) {
                    flames.remove(flameRight);
                    break;
                }
            }

            for (int i = 1; i < this.radius; i++) {
                f = new Flame(xUnit - i, yUnit, Sprite.explosion_horizontal, 6);
                flames.add(f);
                e = Game.getEntityAt(xUnit - i, yUnit);
                if (!e.collide(f)) {
                    flames.remove(flameLeft);
                    break;
                }
            }

            for (int i = 1; i < this.radius; i++) {
                f = new Flame(xUnit, yUnit + i, Sprite.explosion_vertical, 5);
                flames.add(f);
                e = Game.getEntityAt(xUnit, yUnit + i);
                if (!e.collide(f)) {
                    flames.remove(flameDown);
                    break;
                }
            }
        }
    }

    public Flame flameAt(int x, int y){
        for (int i = 0; i < flames.size(); i++) {
            if(flames.get(i).getX() == x && flames.get(i).getY() == y)
                return flames.get(i);
        }
        return null;
    }
}

