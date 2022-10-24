package com.example.demo.entities.animated.Character;

import java.util.concurrent.ThreadLocalRandom;

public class NormalMove extends  EnemyMove{
    @Override
    public int move() {
        return ThreadLocalRandom.current().nextInt(1,5);
    }
}
