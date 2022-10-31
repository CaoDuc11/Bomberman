package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Enemy;
import com.example.demo.entities.animated.Character.Enemy.Kondoria;
import com.example.demo.entities.animated.bomb.Bomb;
import com.example.demo.entities.animated.bomb.Flame;
;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Game extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static final int TITLE_SIZE = 16;
    public GraphicsContext gc;
    public Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    public static List<Entity> extraEntity = new ArrayList<>();


    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 2.0;//toc do bomber

    public static final int TIME = 200;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

    protected static double bombRate = BOMBRATE;
    protected static double bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static double getBombRate() {
        return bombRate;
    }

    public static double getBombRadius() {
        return bombRadius;
    }

    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public static void addBombRadius(double i) {
        bombRadius += i;
    }

    public static void addBombRate(double i) {
        bombRate += i;
    }


    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        Sound.background.play();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void update() {
        stillObjects.forEach(Entity::update);
        entities.forEach(Entity::update);
        Bomber._bombs.forEach(Bomb::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        Bomber._bombs.forEach(g -> g.render(gc));
        for (int i = 0; i < Bomber._bombs.size(); i++) {
            Bomber._bombs.get(i).render(gc);
            for (int j = 0; j < Bomber._bombs.get(i).flames.size(); j++) {
                Bomber._bombs.get(i).flames.get(j).render(gc);
            }
        }
    }

    public static Entity getEntity(int xUnit, int yUnit, Entity d) {
        Entity e = null;
        if (!(d instanceof Bomber)) {
            Bomber b = getBomber();
            if (b != null) {
                if (b.getxUnit() == xUnit && b.getyUnit() == yUnit) {
                    e = getBomber();
                    if (e != null) return e;
                }
            }
        }
        if (!(d instanceof Flame)) {
            for (int i = 0; i < Bomber._bombs.size(); i++) {
                e = Bomber._bombs.get(i).flameAt(xUnit, yUnit);

            }
        }
        e = Bomber.bombAt(xUnit, yUnit);
        if (e != null) return e;
        if (!(d instanceof Enemy)) {
            e = getEnemy(xUnit, yUnit);
            if (e != null) {
                if (e instanceof Kondoria) {
                    Entity u = getEntityAt(xUnit, yUnit);
                    u.collide(d);
                }
                return e;
            }
        }
        e = getEntityAt(xUnit, yUnit);
        return e;
    }

    public static Bomber getBomber() {
        if (entities.get(0) instanceof Bomber) return (Bomber) entities.get(0);
        return null;
    }

    public static Enemy getEnemy(int xUnit, int yUnit) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Enemy) {
                if (entities.get(i).getxUnit() == xUnit && entities.get(i).getyUnit() == yUnit)
                    return (Enemy) entities.get(i);
            }
        }
        return null;
    }

    public static Entity getEntityAt(int x, int y) {
        return stillObjects.get(x + y * WIDTH);
    }
}
