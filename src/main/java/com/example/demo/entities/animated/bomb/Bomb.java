package com.example.demo.entities.animated.bomb;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class Bomb extends Entity {
    protected double _timeToExplode = 120; //2 seconds - thoi gian phat no
    public int _timeAfter = 20;// thoi gian de no
    protected boolean _exploded = false;

    private Sprite sprite_;

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit,yUnit,sprite.getFxImage());
    }


    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {
        System.out.println(x);
        if(_timeToExplode > 0)
            _timeToExplode--;
        else {
            if(!_exploded)
                explode();
            else
                updateFlames();

            if(_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }
        AnimatedEntity.animate();
    }
    public void chooseSprite(){
        if(_timeToExplode <= 150){
             sprite_ = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,AnimatedEntity.animate,50);
        } if (_timeToExplode > 150 && _timeToExplode < 180){
            sprite_=Sprite.movingSprite(Sprite.bomb_exploded,Sprite.bomb_exploded1,Sprite.bomb_exploded2,AnimatedEntity.animate,120 );
        }
    }
    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        Image img_ = sprite_.getFxImage();
        gc.drawImage(img_, x, y);
    }

    public void updateFlames() {

    }

    public void remove() {

    }
    public void explode() {
    }
}
