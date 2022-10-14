package com.example.demo.entities.animated.Character;

import com.example.demo.entities.animated.AnimatedEntity;
import javafx.scene.image.Image;

public abstract class Character extends AnimatedEntity {
    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    protected int direction = 0;
    protected boolean alive = true;
    protected boolean moving = false;
    public int timeAfter = 40;


    @Override
    public abstract void update();


    /**
     * Tính toán hướng đi
     */
    protected abstract void calculateMove();

    protected abstract void move(double x, double y);

    /**
     * Được gọi khi đối tượng bị tiêu diệt
     */
    public abstract void kill();

    /**
     * Xử lý hiệu ứng bị tiêu diệt
     */
    protected abstract void afterKill();

    /**
     * Kiểm tra xem đối tượng có di chuyển tới vị trí đã tính toán hay không
     * @param x
     * @param y
     * @return
     */
    protected abstract boolean canMove(double x, double y);
}