package com.example.demo.entities.animated.Character.EneMove;

import com.example.demo.Game;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;

import java.util.concurrent.ThreadLocalRandom;

public class AILowMove extends EnemyMove {
    Enemy _e;
    public AILowMove( Enemy e) {
        _e = e;
    }

    @Override
    public int move() {
        int checkMove = 0;
        Bomber b =  Game.getBomber();
        if(b != null){
            if(b.getxUnit() == _e.getxUnit()){
                if( _e.getyUnit() - b.getyUnit() > 0 ){
                    checkMove = 1;
                }
                if( b.getyUnit() - _e.getyUnit() > 0 ){
                    checkMove = 3;
                }
            }
            if(b.getyUnit() == _e.getyUnit()){
                if(_e.getxUnit() - b.getxUnit() > 0 ){
                    checkMove = 4;
                }
                if(b.getxUnit() - _e.getxUnit() > 0 ){
                    checkMove = 2;
                }

            }
        }

        if(_e.canMove(checkMove) && checkMove != 0 ) return checkMove;
        return ThreadLocalRandom.current().nextInt(1,5);
    }

}
