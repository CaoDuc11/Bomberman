package com.example.demo.entities.animated.Character.Enemy;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.EneMove.AILowMove;
import com.example.demo.entities.animated.Character.EneMove.NormalMove;
import com.example.demo.graphics.Sprite;

public class Minvo extends Enemy{
    public Minvo(int x, int y, Sprite sprite, double speed, int point) {
        super(x, y, sprite, speed, point);
        enemyMove = new AILowMove(this);
        direction = enemyMove.move();
    }
    @Override
    public void remove(){
        Entity obj1 = new Balloon(this.getxUnit(),this.getyUnit(),Sprite.balloom_dead,1,100);
        Entity obj2 = new Balloon(this.getxUnit(),this.getyUnit(),Sprite.balloom_dead,1,100);
        Game.extraEntity.add(obj1);
        Game.extraEntity.add(obj2);
        remove = true;
    }

    @Override
    public void chooseSprite() {
        if(alive){
            switch(direction) {
                case 1:
                case 2:
                    this.sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 120);
                    break;
                case 3:
                case 4:
                    this.sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 120);
                    break;
            }
        }
        else {
            if(timeAfter < 20){
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate,120);
            }
            else sprite = Sprite.minvo_dead;
        }
    }
}
