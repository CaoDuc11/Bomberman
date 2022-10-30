package com.example.demo.entities.animated.Character.EneMove;

import com.example.demo.Game;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;

public class AIHighMove extends EnemyMove{
    Enemy _e;
    public AIHighMove(Enemy e){
        _e = e;
    }
    private int[] moveX = {0, 1, 0, -1};
    private int[] moveY = {-1, 0, 1, 0};
    @Override
    public int move() {
        Bomber b = Game.getBomber();
        if(b != null){
            boolean [][] visited = new boolean[Game.HEIGHT][Game.WIDTH];

        }
        return 0;
    }
}
