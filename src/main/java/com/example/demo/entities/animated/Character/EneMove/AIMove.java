package com.example.demo.entities.animated.Character.EneMove;

import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;

import java.util.concurrent.ThreadLocalRandom;

public class AIMove extends EnemyMove {
    Bomber _bomber;
    Enemy _e;

    public AIMove(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
    }

    @Override
    public int move() {
        // TODO: cài đặt thuật toán tìm đường đi
        if(_bomber == null)
            return ThreadLocalRandom.current().nextInt(1,5);

        int vertical = ThreadLocalRandom.current().nextInt(0,1);

        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();

        } else {
            int h = calculateColDirection();

            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }
    }
    protected int calculateColDirection() {
        if(_bomber.getXTile() < _e.getXTile())
            return 4;
        else if(_bomber.getXTile() > _e.getXTile())
            return 2;

        return -1;
    }

    protected int calculateRowDirection() {
        if(_bomber.getYTile() < _e.getYTile())
            return 1;
        else if(_bomber.getYTile() > _e.getYTile())
            return 3;
        return -1;
    }

}
