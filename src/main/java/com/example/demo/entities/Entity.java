package com.example.demo.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import com.example.demo.graphics.Sprite;

import java.util.LinkedList;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected boolean removed = false;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {

        gc.drawImage(img, x, y);
    }

    public boolean isRemoved(){
        return removed;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    public boolean collide(int x, int y) {
        int left_a = this.x;
        int right_a = this.x + Sprite.SCALED_SIZE;
        int top_a = this.y;
        int bottom_a = this.y + Sprite.SCALED_SIZE;

        int left_b = x;
        int right_b = x + Sprite.SCALED_SIZE;
        int top_b = y;
        int bottom_b = y + Sprite.SCALED_SIZE;

        if (top_a == top_b && right_a == right_b && bottom_a == bottom_b)
        {
            return true;
        }
        return false;
    }

    public abstract boolean collide(Entity e);

    public abstract void update();
}