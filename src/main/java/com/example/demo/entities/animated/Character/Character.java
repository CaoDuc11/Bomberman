package com.example.demo.entities.animated.Character;

import com.example.demo.entities.animated.AnimatedEntity;
import com.example.demo.entities.animated.Character.EneMove.NormalMove;
import com.example.demo.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Character extends AnimatedEntity {
    protected int direction = -1;
    public static boolean alive = true;
    protected boolean moving = false;

    protected int timeAfter = 40;

    public Character(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public void setxUnit() {
        this.xUnit = (this.x + Sprite.SCALED_SIZE/2) / Sprite.SCALED_SIZE;
    }

    public void setyUnit() {
        this.yUnit = (this.y + Sprite.SCALED_SIZE/2) / Sprite.SCALED_SIZE;
    }

    @Override
    public int getxUnit(){
        setxUnit();
        return this.xUnit;
    }

    @Override
    public int getyUnit(){
        setyUnit();
        return this.yUnit;
    }

    @Override
    public abstract void update();

    protected abstract void chooseSprite();

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
     * @param
     * @param
     * @return
     */

    protected int set(int n){
        int check = n / Sprite.SCALED_SIZE;
        if (n - check * Sprite.SCALED_SIZE >= Sprite.SCALED_SIZE * 0.75){
            return (check + 1) * Sprite.SCALED_SIZE;
        }
        if(n - check * Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE * 0.25){
            return check * Sprite.SCALED_SIZE;
        }

        return n;
    }
}
