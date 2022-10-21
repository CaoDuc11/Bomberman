package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.entities.animated.Character.Balloon;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.bomb.Bomb;
import com.example.demo.entities.freeze.Grass;
import com.example.demo.entities.freeze.Wall;
import com.example.demo.entities.freeze.destroyable.Brick;
import javafx.animation.AnimationTimer;;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import com.example.demo.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Game extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static final int TITLE_SIZE = 16;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();



    public static int SCALE = 3;

    public static final String TITLE = "BombermanGame";

    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 2.0;//toc do bomber

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
    public void start(Stage stage) throws FileNotFoundException {
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

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right);

        entities.add(bomberman);
        AnimationTimer timer = new AnimationTimer() {
            long lastTick = 0;
            @Override
            public void handle(long l) {
                if (lastTick == 0) {
                    lastTick = l;
                    Bomber.getControl(scene);
                    render();
                    update();
                    return;
                }

                if (l - lastTick > 1000000000 / 1000) {
                    lastTick = l;
                    Bomber.getControl(scene);
                    render();
                    update();
                    return;
                }

            }
        };
        timer.start();


    }

    public void createMap() throws FileNotFoundException {
         List<String> str = new ArrayList<>();

        try {
            FileReader fr = new FileReader("Bomberman/src/main/resources/levels/Level1.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = "";

            while (true) {
                line = br.readLine();
                if (line == null) break;
                str.add(line);
            }
            Entity obj;
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    switch (str.get(i + 1).charAt(j)) {
                        case '#':
                            obj = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(obj);
                            break;
                        case ' ':
                            obj = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(obj);
                            break;
                        case '*':
//                            obj = new Grass(j, i, Sprite.grass.getFxImage());
//                            stillObjects.add(obj);
                            obj = new Brick(j, i, Sprite.brick);
                            entities.add(obj);
                            stillObjects.add(obj);
                            break;
                        /*
                            case 'x':
                            obj = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(obj);
                            obj = new Portal(j, i, Sprite.portal.getFxImage());
                            stillObjects.add(obj);
                            obj = new Brick(j, i, Sprite.brick.getFxImage());
                            entities.add(obj);
                            break;

                         */
                              case '1' :
                            obj = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(obj);
                            obj = new Balloon(j ,i, Sprite.balloom_right1.getFxImage(),40);
                            entities.add(obj);
                            break;
                            /*
                        case '2' :
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            obj = new Oneal(j, i, Sprite.oneal_right1);
                            entities.add(obj);
                            break;


                        case 'f' :
                            obj = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(obj);
                            obj = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            entities.add(obj);
                            obj = new Brick(j, i, Sprite.brick);
                            entities.add(obj);
                            break;
                        case 'b' :
                            obj = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(obj);
                            obj = new BoomItem(j, i, Sprite.powerup_bombs.getFxImage());
                            entities.add(obj);
                            // obj = new Brick(j, i, Sprite.brick);
                            //entities.add(obj);
                            break;
                        case 's' :
                            obj = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(obj);
                            obj = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            entities.add(obj);
                             obj = new Brick(j, i, Sprite.brick);
                            entities.add(obj);
                            break;*/
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Can't load file");
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        Bomber._bombs.forEach(Bomb::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        Bomber._bombs.forEach(g -> g.render(gc));
    }

    public static Entity getEntityAt(int x, int y) {
        return stillObjects.get( x + y * WIDTH);
    }
}
