package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.graphics.Sprite;

public class Kondoria extends Enemy{
    public Kondoria(int x, int y, Sprite sprite, double speed, int point) {
        super(x, y, sprite, speed, point);
        this.direction = 0;
    }

    @Override
    public void update(){
        animate();
        if(!alive){
            if(timeAfter > 0)timeAfter--;
            else remove();
        }
        else{
            goToBomber();
        }
        chooseSprite();
        this.setImg(sprite.getFxImage());
    }
    private void goToBomber(){
        Bomber b = Game.getBomber();
        if(b != null){
            if(b.getxUnit() != this.getxUnit() || b.getyUnit() != this.getyUnit()){
                if(b.getX() > this.getX()) {
                    this.x += _speed;
                    this.direction = 2;
                }
                if(b.getY() > this.getY()){
                    this.y += _speed;
                    this.direction = 3;
                }
                if(b.getX() < this.getX()){
                    this.x -= _speed;
                    this.direction = 4;
                }
                if(b.getY() < this.getY()){
                    this.y -= _speed;
                    this.direction = 1;
                }
                if(b.getX() == this.getX() && b.getY() == this.getY()) b.kill();
            }
        }

    }
    @Override
    public void chooseSprite() {
        if(alive){
            switch(direction) {
                case 0:
                case 1:
                case 2:
                    this.sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 120);
                    break;
                case 3:
                case 4:
                    this.sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 120);
                    break;
            }
        }
        else {
            if(timeAfter < 20) sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate,120);
            else sprite = Sprite.kondoria_dead;
        }

    }
}
