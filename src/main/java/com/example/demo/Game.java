package com.example.demo;

import com.example.demo.entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import com.example.demo.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;




public class Game extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static int SCALE = 3;

    public static final String TITLE = "BombermanGame";

    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 3.0;//toc do bomber

    public static final int TIME = 200;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;



    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);



        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right);
        Bomber.getControl(scene);
        entities.add(bomberman);
    }

    public void createMap() {
        List<String> str = new ArrayList<>();
        try {
            FileReader fr = new FileReader("D:\\CaoDuc11\\Bomberman\\src\\main\\resources\\levels\\Level1.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = "";

            while (true) {
                line = br.readLine();
                if (line == null) break;
                str.add(line);
            }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity obj;
                switch (str.get(i + 1).charAt(j)) {
                    case '#':
                        obj = new Wall(j, i, Sprite.wall);
                        stillObjects.add(obj);
                        break;
                    case ' ':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        break;
                    case '*':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        obj = new Brick(j, i, Sprite.brick);
                        entities.add(obj);
                        break;
                    case 'p':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        obj = new Bomber(1, 1, Sprite.player_right);
                        entities.add(obj);
                        break;
                    case 'x':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        obj = new Portal(j, i, Sprite.portal);
                        stillObjects.add(obj);
                        obj = new Brick(j, i, Sprite.brick);
                        entities.add(obj);
                        break;

                       /*
                       case '1':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        obj = new Balloon(j, i, Sprite.balloom_right1);
                        entities.add(obj);
                        break;
                    case '2':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        obj = new Oneal(j, i, Sprite.oneal_right1);
                        entities.add(obj);
                        break;
                    case 'f':
                        obj = new Grass(j, i, Sprite.grass);
                        stillObjects.add(obj);
                        obj = new Flame_Item(j, i, Sprite.powerup_flames);
                        entities.add(obj);
                        obj = new Brick(j, i, Sprite.brick);
                        entities.add(obj);

                     */

                }
            }
        }
      } catch (Exception e){
            System.out.println("Can't load file");
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

