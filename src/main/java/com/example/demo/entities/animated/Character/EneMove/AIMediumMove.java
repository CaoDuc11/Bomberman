package com.example.demo.entities.animated.Character.EneMove;

import com.example.demo.Game;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;

import java.util.Random;

public class AIMediumMove extends EnemyMove {
    private Enemy _e;
    private Random random = new Random();

    public AIMediumMove(Enemy e) {
        _e = e;
    }

    @Override
    public int move() {
        int check = random.nextInt(2);
        if (check == 0) {
            int MoveY = checkCollums();
            if (MoveY != 0) return MoveY;
            else return checkLines();
        } else {
            int MoveX = checkLines();
            if (MoveX != 0) return MoveX;
            else return checkCollums();
        }
    }

    private int checkCollums() {
        Bomber b = Game.getBomber();
        if (b != null) {
            if (b.getyUnit() < _e.getyUnit()) {
                return 1;
            }
            if (b.getyUnit() > _e.getyUnit()) {
                return 3;
            }
        }
        return 0;
    }

    private int checkLines() {
        Bomber b = Game.getBomber();
        if (b != null) {
            if (b.getxUnit() > _e.getxUnit()) {
                return 2;
            }
            if (b.getxUnit() < _e.getxUnit()) {
                return 4;
            }
        }
        return 0;
    }
}
