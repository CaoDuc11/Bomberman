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

    protected Sprite sprite;

    protected boolean removed = false;

    protected LinkedList<Entity> _entity = new LinkedList<>();
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit,Sprite sprite) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.sprite = sprite;
    }

    public void render(GraphicsContext gc) {

        gc.drawImage(sprite.getFxImage(), x, y);
    }

    public boolean isRemoved(){
        return removed;
    }
    public Sprite getSprite(){
        return sprite;
    }
    public abstract void update();
}