package com.example.demo.entities.animated.Character.EneMove;

import com.example.demo.Game;
import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class AIHighMove extends EnemyMove {
    Enemy _e;

    public AIHighMove(Enemy e) {
        _e = e;
    }

    private int[] moveX = {0, 1, 0, -1};
    private int[] moveY = {-1, 0, 1, 0};

    int[][] distance = new int[Game.HEIGHT][Game.WIDTH];
    boolean[][] visit = new boolean[Game.HEIGHT][Game.WIDTH];


    public void BFS(int x, int y) {
        for (int i = 0; i < Game.HEIGHT; i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                visit[i][j] = false;
            }
        }
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<Integer, Integer>(y, x));
        visit[y][x] = true;
        distance[y][x] = 0;
        while (!q.isEmpty()) {
            Pair<Integer, Integer> top = q.peek();
            q.remove();
            for (int k = 0; k < 4; k++) {
                int i1 = top.getValue() + moveX[k];
                int j1 = top.getKey() + moveY[k];
                if (i1 >= 0 && i1 <= Game.WIDTH - 1 && j1 >= 0 && j1 <= Game.HEIGHT - 1 && visit[j1][i1] == false) {
                    Entity e = Game.getEntityAt(i1, j1);
                    if (e.collide(this._e)) {
                        distance[j1][i1] = distance[top.getKey()][top.getValue()] + 1;
                        q.add(new Pair<Integer, Integer>(j1, i1));
                        visit[j1][i1] = true;
                    }
                }
            }
        }
    }

    @Override
    public int move() {
        Bomber b = Game.getBomber();
        if (b != null) {
            int sx = b.getxUnit();
            int sy = b.getyUnit();
            BFS(sx, sy);
            for (int i = 0; i < 4; i++) {
                int u = this._e.getxUnit() + moveX[i];
                int v = this._e.getyUnit() + moveY[i];
                if (u >= 0 && v >= 0 && u < Game.WIDTH && v < Game.HEIGHT) {
                    Entity e = Game.getEntityAt(u, v);
                    if (!e.collide(this._e)) {
                        continue;
                    }
                    if (distance[v][u] == distance[this._e.getyUnit()][this._e.getxUnit()] - 1) {

                        return i + 1;
                    }
                }
            }
        }
        return 0;
    }

}
